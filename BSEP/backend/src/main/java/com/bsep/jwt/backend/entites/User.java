package com.bsep.jwt.backend.entites;

import com.bsep.jwt.backend.enums.PackageType;
import com.bsep.jwt.backend.enums.RegistrationStatus;
import com.bsep.jwt.backend.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = true)
    @Size(max = 100)
    private String firstName;

    @Column(name = "last_name", nullable = true)
    @Size(max = 100)
    private String lastName;

    @Column(name = "pib", nullable = true)
    @Size(max = 100)
    private String pib;

    @Column(name = "company_name", nullable = true)
    @Size(max = 100)
    private String companyName;

    @Column(nullable = false)
    @Size(max = 100)
    private String login;

    @Column(nullable = false)
    @Size(max = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PackageType packageType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegistrationStatus registrationStatus;

    @Column(nullable = false)
    @Size(max = 100)
    private String salt;

    @Column(name="address",nullable = false)
    @Size(max = 100)
    private String address;

    @Column(name="city", nullable = false)
    @Size(max = 100)
    private String city;

    @Column(name="country",nullable = false)
    @Size(max = 100)
    private String country;

    @Column(name = "phone_number", nullable = false)
    @Size(max = 20)
    private String phoneNumber;

    @Column(name = "is_legal", nullable = false)
    private boolean isLegal;

    @Column(nullable = true)
    @Size(max = 100)
    private String oneTimePass;
}
