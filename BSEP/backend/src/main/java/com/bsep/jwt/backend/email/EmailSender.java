package com.bsep.jwt.backend.email;

public interface EmailSender {
    void send(String to, String email);

}
