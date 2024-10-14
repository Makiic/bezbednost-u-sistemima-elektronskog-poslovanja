package com.example.bsepprojekat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BSEPProjekatApplication {

	public static void main(String[] args) {
		SpringApplication.run(BSEPProjekatApplication.class, args);
	}
}