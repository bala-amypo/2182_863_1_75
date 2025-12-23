package com.example.demo.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiskScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "visitor_id", nullable = false, unique = true)
    @JsonIgnoreProperties({"visitLogs"})
    private Visitor visitor;

    private Integer totalScore;

    @Column(nullable = false)
    private String riskLevel;

    private LocalDateTime evaluatedAt;

    @OneToOne
    @JoinColumn(name = "riskrule_id", nullable = false)
    private RiskRule riskRule;

    @PrePersist
    protected void prePersist() {
        if (visitor == null) {
            throw new RuntimeException("visitor required");
        }
        if (totalScore < 0) {
            throw new RuntimeException("totalScore cannot be negative");
        }
        if (riskLevel == null || riskLevel.isBlank()) {
            throw new RuntimeException("riskLevel required");
        }
        if (this.evaluatedAt == null) {
            this.evaluatedAt = LocalDateTime.now();
        }
    }

}
