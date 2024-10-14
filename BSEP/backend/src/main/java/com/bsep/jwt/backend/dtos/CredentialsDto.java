package com.bsep.jwt.backend.dtos;

public record CredentialsDto (String login, char[] password) { }