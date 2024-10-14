package com.example.bsepprojekat.model;

import com.example.bsepprojekat.dto.UserDTO;

public class CertificateRequest {
    private UserDTO subjectData;
    private UserDTO issuerData;

    public UserDTO getSubjectData() {
        return subjectData;
    }

    public void setSubjectData(UserDTO subjectData) {
        this.subjectData = subjectData;
    }

    public UserDTO getIssuerData() {
        return issuerData;
    }

    public void setIssuerData(UserDTO issuerData) {
        this.issuerData = issuerData;
    }
}
