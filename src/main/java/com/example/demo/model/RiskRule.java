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
public class RiskRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ruleName;

    @Column(nullable = false)
    private String ruleType;

    private Integer threshold;

    private Integer scoreImpact;

    private LocalDateTime createdAt;

    @PrePersist
    protected void prePersist() {
        if (ruleName == null || ruleName.isBlank()) {
            throw new RuntimeException("ruleName required");
        }
        if (ruleType == null || ruleType.isBlank()) {
            throw new RuntimeException("ruleType required");
        }
        if (threshold < 0) {
            throw new RuntimeException("threshold must be greater than 0");
        }
        if (scoreImpact == 0) {
            throw new RuntimeException("scoreImpact cannot be 0");
        }
        this.createdAt = LocalDateTime.now();
    }
}
