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
public class VisitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Visitor visitor;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String purpose;
    private String location;

    @PrePersist
    public void prePersist() {
        if (this.entryTime == null) {
            this.entryTime = LocalDateTime.now();
        }
    }
}
