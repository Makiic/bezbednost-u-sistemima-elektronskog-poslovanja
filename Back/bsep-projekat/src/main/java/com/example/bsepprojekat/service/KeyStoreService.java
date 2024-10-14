package com.example.bsepprojekat.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class KeyStoreService {

    public boolean doesKeyStoreExist(String certificateRole) {
        String name = certificateRole.toLowerCase();
        File file = new File("keystores/" + name + ".jks");
        return file.exists();
    }
}
