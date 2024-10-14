package com.example.bsepprojekat.model;

import com.example.bsepprojekat.enums.UserRole;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;


@Entity
@EqualsAndHashCode
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "userRole", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Column(name = "x500_CN", nullable = false)
    private String x500_CN;
    @Column(name = "x500_SURNAME", nullable = false)
    private String x500_SURNAME;
    @Column(name = "x500_GIVENNAME", nullable = false)
    private String x500_GIVENNAME;
    @Column(name = "x500_O", nullable = false)
    private String x500_O;
    @Column(name = "x500_OU", nullable = false)
    private String x500_OU;
    @Column(name = "x500_C", nullable = false)
    private String x500_C;
    @Column(name = "x500_E", nullable = false)
    private String x500_E;
    @Column(name = "x500_UID", nullable = false)
    private String x500_UID;
    @Column(name = "isSubject", nullable = false)
    private boolean isSubject;
    @Column(name = "isIssuer", nullable = false)
    private boolean isIssuer;
    @Column(name = "serialNumber", nullable = false)
    private String serialNumber;
    @Column(name = "startDate", nullable = false)
    private String startDate;
    @Column(name = "endDate", nullable = false)
    private String endDate;


    public User(){super();}

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public String getX500_CN() {
        return x500_CN;
    }

    public String getX500_SURNAME() {
        return x500_SURNAME;
    }

    public String getX500_GIVENNAME() {
        return x500_GIVENNAME;
    }

    public String getX500_O() {
        return x500_O;
    }

    public String getX500_OU() {
        return x500_OU;
    }

    public String getX500_C() {
        return x500_C;
    }

    public String getX500_E() {
        return x500_E;
    }

    public String getX500_UID() {
        return x500_UID;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getEndDate() {
        return endDate;
    }

    public boolean getIsSubject() {
        return isSubject;
    }

    public boolean getIsIssuer() {
        return isIssuer;
    }
}
