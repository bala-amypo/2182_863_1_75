package com.example.demo.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreAuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "visitor_id", nullable = false)
    private Visitor visitor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "riskrule_id", nullable = true)
    private RiskRule appliedRule;

    private Integer scoreChange;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false, updatable = false)
    private LocalDateTime loggedAt;

    @PrePersist
    protected void prePersist() {
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("reason required");
        }
        if (scoreChange < 0) {
            throw new IllegalArgumentException("scoreChange must be >= 0");
        }
        this.loggedAt = LocalDateTime.now();
    }
}
