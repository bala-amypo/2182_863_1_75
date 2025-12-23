package com.example.demo.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiskRule {

    private String ruleName;
    private int threshold;
    private int scoreImpact;
}
