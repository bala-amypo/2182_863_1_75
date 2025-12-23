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
public class VisitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "visitor_id", nullable = false)
    @JsonIgnoreProperties("visitLogs")
    private Visitor visitor;

    @Column(nullable = false, updatable = false)
    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    @Column(nullable = false)
    private String purpose;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (this.entryTime == null) {
            this.entryTime = LocalDateTime.now();
        }

        this.createdAt = LocalDateTime.now();

        if (purpose == null || purpose.isBlank()) {
            throw new IllegalArgumentException("purpose is required");
        }
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("location is required");
        }
    }

    @PreUpdate
    protected void preUpdate() {
        if (exitTime != null && !exitTime.isAfter(entryTime)) {
            throw new IllegalArgumentException("exitTime must be after entryTime");
        }
    }
}
