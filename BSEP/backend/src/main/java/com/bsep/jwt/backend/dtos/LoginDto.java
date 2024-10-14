package com.bsep.jwt.backend.dtos;

public record LoginDto (String login, char[] password, String otp) { }
