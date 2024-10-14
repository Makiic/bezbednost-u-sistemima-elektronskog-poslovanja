package com.example.bsepprojekat.service;

import com.example.bsepprojekat.data.IssuerData;
import com.example.bsepprojekat.data.SubjectData;
import com.example.bsepprojekat.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
public class CertificateService {

    public KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(2048, random);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException var3) {
            var3.printStackTrace();
        } catch (NoSuchProviderException var4) {
            var4.printStackTrace();
        }

        return null;
    }
    public IssuerData generateIssuerData(PrivateKey issuerKey, UserDTO user) {
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.CN, user.getX500_CN());
        builder.addRDN(BCStyle.SURNAME, user.getX500_SURNAME());
        builder.addRDN(BCStyle.GIVENNAME, user.getX500_GIVENNAME());
        builder.addRDN(BCStyle.O, user.getX500_O());
        builder.addRDN(BCStyle.OU, user.getX500_OU());
        builder.addRDN(BCStyle.C, user.getX500_C());
        builder.addRDN(BCStyle.E, user.getX500_E());
        builder.addRDN(BCStyle.UID, user.getX500_UID());
        return new IssuerData(issuerKey, builder.build());
    }
    public SubjectData generateSubjectData(UserDTO user) {
        try {
            KeyPair keyPairSubject = this.generateKeyPair();
            SimpleDateFormat iso8601Formater = new SimpleDateFormat("dd.MM.yyyy.");
            Date startDate = iso8601Formater.parse(user.getStartDate());
            Date endDate = iso8601Formater.parse(user.getEndDate());
            String sn = user.getSerialNumber();
            X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
            builder.addRDN(BCStyle.CN, user.getX500_CN());
            builder.addRDN(BCStyle.SURNAME, user.getX500_SURNAME());
            builder.addRDN(BCStyle.GIVENNAME, user.getX500_GIVENNAME());
            builder.addRDN(BCStyle.O, user.getX500_O());
            builder.addRDN(BCStyle.OU, user.getX500_OU());
            builder.addRDN(BCStyle.C, user.getX500_C());
            builder.addRDN(BCStyle.E, user.getX500_E());
            builder.addRDN(BCStyle.UID, user.getX500_UID());
            return new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);
        } catch (ParseException var7) {
            var7.printStackTrace();
            return null;
        }
    }

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public X509Certificate generateCertificate(SubjectData subjectData, IssuerData issuerData) {
        try {
            //Posto klasa za generisanje sertifiakta ne moze da primi direktno privatni kljuc pravi se builder za objekat
            //Ovaj objekat sadrzi privatni kljuc izdavaoca sertifikata i koristiti se za potpisivanje sertifikata
            //Parametar koji se prosledjuje je algoritam koji se koristi za potpisivanje sertifiakta
            JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");
            //Takodje se navodi koji provider se koristi, u ovom slucaju Bouncy Castle

            builder = builder.setProvider("BC");

            //Formira se objekat koji ce sadrzati privatni kljuc i koji ce se koristiti za potpisivanje sertifikata
            ContentSigner contentSigner = builder.build(issuerData.getPrivateKey());

            //Postavljaju se podaci za generisanje sertifiakta
            X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(issuerData.getX500name(),
                    new BigInteger(subjectData.getSerialNumber()),
                    subjectData.getStartDate(),
                    subjectData.getEndDate(),
                    subjectData.getX500name(),
                    subjectData.getPublicKey());
            //Generise se sertifikat
            X509CertificateHolder certHolder = certGen.build(contentSigner);

            //Builder generise sertifikat kao objekat klase X509CertificateHolder
            //Nakon toga je potrebno certHolder konvertovati u sertifikat, za sta se koristi certConverter
            JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
            certConverter = certConverter.setProvider("BC");

            //Konvertuje objekat u sertifikat
            return certConverter.getCertificate(certHolder);
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (OperatorCreationException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
