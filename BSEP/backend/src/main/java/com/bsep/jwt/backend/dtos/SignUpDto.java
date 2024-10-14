package com.bsep.jwt.backend.dtos;

public record SignUpDto (String firstName, String lastName, String login, char[] password, String pib, String companyName, String address, String city, String country, String phoneNumber, boolean isLegal, String packageType) { }
