package com.bsep.jwt.backend.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
@Entity
public class DeclinedRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "declinedAt")
    private LocalDateTime declinedAt;

    @Column(name = "reason")
    private String reason;

    public DeclinedRegistration(User user, LocalDateTime now, String reason) {
        this.user=user;
        this.declinedAt = now;
        this.reason=reason;
    }
}
