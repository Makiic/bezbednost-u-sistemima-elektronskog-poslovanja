package com.bsep.jwt.backend.repositories;

import com.bsep.jwt.backend.entites.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    User findById(Integer id);

    @Query("SELECT u FROM User u WHERE u.registrationStatus = 'PENDING'")
    List<User> findAllByRegistrationStatusPending();

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.registrationStatus = 'CONFIRMED' WHERE u.id = ?1")
    void updateUserRegistrationStatusToConfirmed(Integer userId);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.registrationStatus = 'ENABLED' WHERE u.id = ?1")
    void updateUserRegistrationStatusToEnabled(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.registrationStatus = 'DECLINED' WHERE u.id = ?1")
    void updateUserRegistrationStatusToDeclined(Integer userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = ?1")
    void deleteUserById(Integer userId);
}