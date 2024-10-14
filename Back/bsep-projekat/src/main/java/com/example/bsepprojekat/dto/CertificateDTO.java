package com.example.bsepprojekat.dto;

import com.example.bsepprojekat.enums.UserRole;

import java.security.cert.X509Certificate;

public class CertificateDTO {
    private String issuer;
    private String subject;
    private String startDate;
    private String endDate;
    private String signatureAlgorithm;
    public CertificateDTO() {

    }
    public CertificateDTO(X509Certificate cert){
            this(cert.getIssuerX500Principal().getName(),
                    cert.getSubjectX500Principal().getName(),
                    cert.getNotBefore().toString(),
                    cert.getNotAfter().toString(),
                    cert.getSigAlgName());
    }

    public CertificateDTO(String issuer, String subject, String startDate,
                   String endDate, String signatureAlgorithm) {
        this.issuer = issuer;
        this.subject = subject;
        this.signatureAlgorithm = signatureAlgorithm;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }
}
