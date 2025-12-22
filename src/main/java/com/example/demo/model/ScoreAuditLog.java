package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

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

    @ManyToOne
    private Visitor visitor;

    @ManyToOne
    private RiskRule appliedRule;

    private Integer scoreChange;
    private String reason;

    private LocalDateTime loggedAt;

    @PrePersist
    public void prePersist() {
        this.loggedAt = LocalDateTime.now();
    }
}
