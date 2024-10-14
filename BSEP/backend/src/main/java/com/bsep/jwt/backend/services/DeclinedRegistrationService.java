package com.bsep.jwt.backend.services;

import com.bsep.jwt.backend.entites.ConfirmationToken;
import com.bsep.jwt.backend.entites.DeclinedRegistration;
import com.bsep.jwt.backend.entites.User;
import com.bsep.jwt.backend.repositories.ConfirmationTokenRepository;
import com.bsep.jwt.backend.repositories.DeclinedRegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeclinedRegistrationService {
    private final DeclinedRegistrationRepository declinedRegistrationRepository;

    public void save(DeclinedRegistration registration){
        declinedRegistrationRepository.save(registration);
    }
    public DeclinedRegistration findByUser(User user) {return declinedRegistrationRepository.findByUser(user);}
    public void deleteById(Integer userId){
        declinedRegistrationRepository.deleteDeclinedRegistrationById(userId);
    }

}
