package com.bsep.jwt.backend.controllers;

import com.bsep.jwt.backend.dtos.UserDto;
import com.bsep.jwt.backend.entites.DeclinedRegistration;
import com.bsep.jwt.backend.entites.User;
import com.bsep.jwt.backend.services.DeclinedRegistrationService;
import com.bsep.jwt.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DeclinedRegistrationService declinedRegistrationService;

    @GetMapping(value = "/getAllPending")
    public ResponseEntity<List<UserDto>> findAllByIsIssuerTrue(){
        List<User> users = userService.findAllPendingUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<UserDto> issuerDTOs = users.stream()
                .map(UserDto::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(issuerDTOs, HttpStatus.OK);
    }
    @PutMapping("/{userId}/confirm-registration")
    public ResponseEntity<?> confirmUserRegistration(@PathVariable Integer userId) {
        userService.confirmRegistration(userId);
        return ResponseEntity.ok().build();
    }
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return userService.confirmToken(token);
    }
    @PostMapping("/confirm-rejection")
    public ResponseEntity<?> rejectRegistration(@RequestParam("userId") Integer userId, @RequestParam("reason") String reason) {
        User user = userService.findById(userId);
        DeclinedRegistration declinedRegistration = new DeclinedRegistration(user, LocalDateTime.now(),reason);
        //declinedRegistrationService.save(userId, reason);
        userService.updateUserRegistrationStatusToDeclined(userId);
        declinedRegistrationService.save(declinedRegistration);
        userService.sendRejectionEmail(declinedRegistration);
        return ResponseEntity.ok().build();
    }
}
