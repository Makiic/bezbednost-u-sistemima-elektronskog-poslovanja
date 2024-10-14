package com.example.bsepprojekat.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.bsepprojekat.data.IssuerData;
import com.example.bsepprojekat.data.SubjectData;
import com.example.bsepprojekat.dto.CertificateDTO;
import com.example.bsepprojekat.dto.UserDTO;
import com.example.bsepprojekat.model.CertificateRequest;
import com.example.bsepprojekat.model.User;
import com.example.bsepprojekat.service.CertificateService;
import com.example.bsepprojekat.service.KeyStoreService;
import com.example.bsepprojekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CertificateService certificateService;
    @Autowired
    private KeyStoreService keyStoreDataService;

    @GetMapping(value = "/getUserByUsername/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        User user = userService.findOneByEmail(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllByIsIssuerTrue")
    public ResponseEntity<List<UserDTO>> findAllByIsIssuerTrue(){
        List<User> issuers = userService.findAllByIsIssuerTrue();
        if (issuers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<UserDTO> issuerDTOs = issuers.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(issuerDTOs, HttpStatus.OK);
    }


    @GetMapping(value = "/getAllByIsSubjectTrue")
    public ResponseEntity<List<UserDTO>> findAllByIsSubjectTrue(){
        List<User> issuers = userService.findAllByIsSubjectTrue();
        if (issuers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<UserDTO> issuerDTOs = issuers.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(issuerDTOs, HttpStatus.OK);
    }
    @GetMapping(value = "/loadCertificate")
    public ResponseEntity<CertificateDTO> load() {
        System.out.println("USLO U METODU BEK");

        try {
            X509Certificate certificate = loadCertificate("cn=anja,surname=anja,givenname=anja,o=anja,ou=anja,c=anja,e=anja,uid=123", "123", "sifra");
            String name = certificate.getIssuerX500Principal().getName();

            CertificateDTO certDto = new CertificateDTO(certificate);
            return new ResponseEntity<>(certDto, HttpStatus.OK);
        } catch (CertificateException | KeyStoreException | IOException | NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/loadCertificates")
    public ResponseEntity<List<CertificateDTO>> loadCertificates() {
        System.out.println("USLO U METODU BEK");

        try {

            List<X509Certificate> certificates= loadCertificates("1","sifra");
            List<CertificateDTO> issuerDTOs = certificates.stream()
                    .map(CertificateDTO::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(issuerDTOs, HttpStatus.OK);
        } catch (CertificateException | KeyStoreException | IOException | NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/generateCertificate/{keyStorePassword}")
    public ResponseEntity<Void> generateCertificate(@RequestBody CertificateRequest request, @PathVariable("keyStorePassword") String keyStorePassword) throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
        // Call the service to generate the certificate
        KeyPair keyPairIssuer = certificateService.generateKeyPair();
        SubjectData sData = certificateService.generateSubjectData(request.getSubjectData());
        IssuerData iData = certificateService.generateIssuerData(keyPairIssuer.getPrivate(), request.getIssuerData());

        if (this.keyStoreDataService.doesKeyStoreExist(iData.getX500name().toString())) {
            try {
                System.out.println("Nasao fajl");
                KeyStore keyStore = KeyStore.getInstance("JKS", "SUN");
                keyStore.load(new FileInputStream("keystores/" + iData.getX500name().toString().toLowerCase() + ".jks"), keyStorePassword.toCharArray());
            } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
                System.out.println("Pogresna sifra odmah");
                throw new KeyStoreException();
            } catch (KeyStoreException e) {
                throw new RuntimeException(e);
            } catch (NoSuchProviderException e) {
                throw new RuntimeException(e);
            }
        }
        X509Certificate certificate = certificateService.generateCertificate(sData, iData);

        saveCertificate(iData.getX500name().toString(), "sifra", certificate.getSerialNumber().toString(), keyStorePassword, keyPairIssuer.getPrivate(), certificate);

        // Return a success response
        return ResponseEntity.ok().build();
    }

    public void saveCertificate(String role, String keyPassword, String alias, String keyStorePassword, PrivateKey privateKey, X509Certificate certificate) throws NoSuchProviderException, KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        String type = role.toString().toLowerCase();
        String file = ("src/main/java/com/example/bsepprojekat/keystores/" + type + ".jks");

        KeyStore keyStore = KeyStore.getInstance("JKS", "SUN");

        try {
            keyStore.load(new FileInputStream(file), keyStorePassword.toCharArray());
        } catch (FileNotFoundException e) {
            System.out.println("nisam ga nasao");
            createKeyStore(type, keyStorePassword, keyStore);
        } catch (IOException e) {
            System.out.println("Pogresna lozinka");
        } catch (NoSuchAlgorithmException | CertificateException e) {
            e.printStackTrace();
        }
        System.out.println("ovde pise koliko ima pre cuvanja " + keyStore.size());
        keyStore.setKeyEntry(alias, privateKey, keyPassword.toCharArray(), new Certificate[]{certificate}); //save cert
        System.out.println("ovde pise koliko ima posle cuvanja " + keyStore.size());
        try {
            keyStore.store(new FileOutputStream(file), keyStorePassword.toCharArray());
            System.out.println("Keystore stored successfully.");
            loadCertificate(role, "123","sifra");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to store keystore: " + e.getMessage());
        }

    }

    private void createKeyStore(String type, String keyStorePassword, KeyStore keyStore) {
        String file = ("keystores/" + type + ".jks");
        try {
            keyStore.load(null, keyStorePassword.toCharArray());
            keyStore.store(new FileOutputStream(file), keyStorePassword.toCharArray());
        } catch (KeyStoreException | IOException | CertificateException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    public X509Certificate loadCertificate(String role, String alias, String password) throws NoSuchProviderException, KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        //kreiramo instancu KeyStore
        KeyStore ks = KeyStore.getInstance("JKS", "SUN");
        //ucitavamo podatke
        String keyStoreFile = "src/main/java/com/example/bsepprojekat/keystores/" + role.toLowerCase() + ".jks";
        ks.load(new FileInputStream(keyStoreFile), password.toCharArray());

        System.out.println("velicina key stora" + ks.size());
        System.out.println("Ovde je dosao znaci ima fajla. i ovo je alias " + alias);
        if (ks.containsAlias(alias)) {
            System.out.println("Stampa ako postoji sa alijasom");
            return (X509Certificate) ks.getCertificate(alias);
        }
        return null;
    }

    public List<X509Certificate> loadCertificates(String alias, String password) throws NoSuchProviderException, KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
            // Kreiramo instancu KeyStore
            KeyStore ks = KeyStore.getInstance("JKS", "SUN");
            // Učitavamo podatke
            String keyStoreFolder = "src/main/java/com/example/bsepprojekat/keystores/";
            File folder = new File(keyStoreFolder);

            List<X509Certificate> certificates = new ArrayList<>();

            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jks"));

            if (files != null) {
                for (File file : files) {
                    ks.load(new FileInputStream(file), password.toCharArray());
                    /*System.out.println("Velicina keystore-a " + ks.size());
                    Enumeration<String> aliases = ks.aliases();
                    while (aliases.hasMoreElements()) {
                        String alias = aliases.nextElement();
                        System.out.println("Alias: " + alias);
                        Certificate cert = ks.getCertificate(alias);
                        if (cert instanceof X509Certificate) {
                            certificates.add((X509Certificate) cert);
                        }
                    }*/
                    Enumeration<String> aliases = ks.aliases();
                    while (aliases.hasMoreElements()) {
                        String currentAlias = aliases.nextElement();
                        if (ks.containsAlias(currentAlias)) {
                            System.out.println("Stampa ako postoji sa alijasom: " + currentAlias);
                            certificates.add((X509Certificate) ks.getCertificate(currentAlias));
                        }
                    }
                    /*if (ks.containsAlias(alias)) {
                        System.out.println("Stampa ako postoji sa alijasom");
                        return (X509Certificate) ks.getCertificate(alias);
                    }
                    certificates.add()*/
                }
            }

            return certificates;
        }
    }

