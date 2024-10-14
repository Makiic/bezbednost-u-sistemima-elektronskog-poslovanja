package com.bsep.jwt.backend.dtos;

import com.bsep.jwt.backend.entites.User;
import com.bsep.jwt.backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String packageType;
    private String pib;
    private String companyName;
    private String login;
    private String token;
    private Role role;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private boolean isLegal;

    public UserDto() {
    }

    public UserDto(User user) {
        this(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPackageType().toString(),
                user.getPib(),
                user.getCompanyName(),
                user.getLogin(),
                user.getRole(),
                user.getAddress(),
                user.getCity(),
                user.getCountry(),
                user.getPhoneNumber(),
                user.isLegal()
        );
    }

    public UserDto(Long id, String firstName, String lastName,
                   String packageType, String pib, String companyName,
                   String login, Role role, String address, String city,
                   String country, String phoneNumber, boolean isLegal) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.packageType = packageType;
        this.pib = pib;
        this.companyName = companyName;
        this.login = login;
        this.role = role;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.isLegal = isLegal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isLegal() {
        return isLegal;
    }

    public void setLegal(boolean legal) {
        isLegal = legal;
    }
}
