package com.example.bsepprojekat.service;

import com.example.bsepprojekat.model.User;
import com.example.bsepprojekat.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    public User findOneByEmail(String email){return userRepository.findUserByEmail(email);}


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User with email" + email +  "not found!"));
    }

    public List<User> findAllByIsIssuerTrue(){
        return userRepository.findAllByIsIssuerTrue();
    }

    public List<User> findAllByIsSubjectTrue(){
        return userRepository.findAllByIsSubjectTrue();
    }
}