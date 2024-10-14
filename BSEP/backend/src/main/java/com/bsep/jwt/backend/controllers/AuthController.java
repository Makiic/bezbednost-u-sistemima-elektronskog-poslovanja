package com.bsep.jwt.backend.controllers;

import com.bsep.jwt.backend.config.UserAuthenticationProvider;
import com.bsep.jwt.backend.dtos.CredentialsDto;
import com.bsep.jwt.backend.dtos.LoginDto;
import com.bsep.jwt.backend.dtos.SignUpDto;
import com.bsep.jwt.backend.dtos.UserDto;
import com.bsep.jwt.backend.entites.DeclinedRegistration;
import com.bsep.jwt.backend.entites.User;
import com.bsep.jwt.backend.services.DeclinedRegistrationService;
import com.bsep.jwt.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;
    private final DeclinedRegistrationService declinedRegistrationService;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);
        //userDto.setToken(userAuthenticationProvider.createToken(userDto));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/loginReal")
    public ResponseEntity<UserDto> loginReal(@RequestBody @Valid LoginDto loginDto) {
        UserDto userDto = userService.loginReal(loginDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
        // Provjeri postoji li korisnik
        Optional<User> optionalUser = userService.findByLogin2(user.login());
        if (optionalUser.isPresent()) {
            User u = optionalUser.get();
            // Ako postoji, dohvati korisnika iz baze
            User user1 = userService.findById(u.getId().intValue());
            // Pronađi odbijen registraciju za tog korisnika
            DeclinedRegistration declinedRegistration = declinedRegistrationService.findByUser(user1);
            if (declinedRegistration != null) {
                // Ako postoji odbijena registracija, provjeri vremenski okvir
                if (!declinedRegistration.getDeclinedAt().isBefore(LocalDateTime.now().minusDays(30))) {
                    // Ako je odbijen prije manje od 30 dana, vrati grešku
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                } else {
                    declinedRegistrationService.deleteById(declinedRegistration.getId().intValue());
                    userService.deleteById(user1.getId().intValue());
                    UserDto createdUser = userService.register(user);
                    createdUser.setToken(userAuthenticationProvider.createToken(createdUser));
                    return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
                }
            }
        }

        // Ako korisnik ne postoji ili je prošlo više od 30 dana od odbijanja, registriraj ga
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(createdUser));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

}
