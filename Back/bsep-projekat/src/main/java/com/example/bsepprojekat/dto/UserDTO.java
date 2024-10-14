package com.example.bsepprojekat.dto;

import com.example.bsepprojekat.enums.UserRole;
import com.example.bsepprojekat.model.User;

import java.util.Date;

public class UserDTO {
    private Integer id;
    private UserRole userRole;
    private String email;
    private String password;
    private String x500_CN;
    private String x500_SURNAME;
    private String x500_GIVENNAME;
    private String x500_O;
    private String x500_OU;
    private String x500_C;
    private String x500_E;
    private String x500_UID;
    private boolean isSubject;
    private boolean isIssuer;
    private String serialNumber;
    private String startDate;
    private String endDate;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this(
                user.getId(),
                user.getUserRole(),
                user.getPassword(),
                user.getEmail(),
                user.getX500_CN(),
                user.getX500_SURNAME(),
                user.getX500_GIVENNAME(),
                user.getX500_O(),
                user.getX500_OU(),
                user.getX500_C(),
                user.getX500_E(),
                user.getX500_UID(),
                user.getIsSubject(),
                user.getIsIssuer(),
                user.getSerialNumber(),
                user.getStartDate(),
                user.getEndDate()
        );
    }

    public UserDTO(Integer id, UserRole userRole, String password, String email,
                   String x500_CN, String x500_SURNAME, String x500_GIVENNAME, String x500_O,
                   String x500_OU, String x500_C, String x500_E, String x500_UID, boolean isSubject,
                   boolean isIssuer, String serialNumber, String startDate, String endDate) {
        this.id = id;
        this.userRole = userRole;
        this.email = email;
        this.password = password;
        this.x500_CN = x500_CN;
        this.x500_SURNAME = x500_SURNAME;
        this.x500_GIVENNAME = x500_GIVENNAME;
        this.x500_O = x500_O;
        this.x500_OU = x500_OU;
        this.x500_C = x500_C;
        this.x500_E = x500_E;
        this.x500_UID = x500_UID;
        this.isSubject = isSubject;
        this.isIssuer = isIssuer;
        this.serialNumber = serialNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getX500_CN() {
        return x500_CN;
    }

    public void setX500_CN(String x500_CN) {
        this.x500_CN = x500_CN;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public String getX500_SURNAME() {
        return x500_SURNAME;
    }

    public void setX500_SURNAME(String x500_SURNAME) {
        this.x500_SURNAME = x500_SURNAME;
    }

    public String getX500_GIVENNAME() {
        return x500_GIVENNAME;
    }

    public void setX500_GIVENNAME(String x500_GIVENNAME) {
        this.x500_GIVENNAME = x500_GIVENNAME;
    }

    public String getX500_O() {
        return x500_O;
    }

    public void setX500_O(String x500_O) {
        this.x500_O = x500_O;
    }

    public String getX500_OU() {
        return x500_OU;
    }

    public void setX500_OU(String x500_OU) {
        this.x500_OU = x500_OU;
    }

    public String getX500_C() {
        return x500_C;
    }

    public void setX500_C(String x500_C) {
        this.x500_C = x500_C;
    }

    public String getX500_E() {
        return x500_E;
    }

    public void setX500_E(String x500_E) {
        this.x500_E = x500_E;
    }

    public String getX500_UID() {
        return x500_UID;
    }

    public void setX500_UID(String x500_UID) {
        this.x500_UID = x500_UID;
    }

    public boolean isIssuer() {
        return isIssuer;
    }

    public void setIssuer(boolean issuer) {
        isIssuer = issuer;
    }

    public boolean isSubject() {
        return isSubject;
    }

    public void setSubject(boolean subject) {
        isSubject = subject;
    }
}
