package com.bsep.jwt.backend.repositories;

import com.bsep.jwt.backend.entites.ConfirmationToken;
import com.bsep.jwt.backend.entites.DeclinedRegistration;
import com.bsep.jwt.backend.entites.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DeclinedRegistrationRepository extends JpaRepository<DeclinedRegistration, Integer> {
    public DeclinedRegistration findByUser(User user);

    @Modifying
    @Transactional
    @Query("DELETE FROM DeclinedRegistration u WHERE u.id = ?1")
    void deleteDeclinedRegistrationById(Integer Id);
}
