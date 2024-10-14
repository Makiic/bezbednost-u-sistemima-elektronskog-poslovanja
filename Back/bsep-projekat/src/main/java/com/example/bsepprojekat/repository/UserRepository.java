package com.example.bsepprojekat.repository;
import com.example.bsepprojekat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
//@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<UserDetails> findByEmail(String email);
    public User findUserByEmail(String email);
    public List<User> findAllByIsIssuerTrue();
    public List<User> findAllByIsSubjectTrue();




}
