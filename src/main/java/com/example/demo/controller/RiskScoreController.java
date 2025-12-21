package com.example.demo.controller;

import com.example.demo.model.RiskScore;
import com.example.demo.service.RiskScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/risk-scores")
@Tag(name = "Risk Scores")
public class RiskScoreController {

    private final RiskScoreService riskScoreService;

    public RiskScoreController(RiskScoreService riskScoreService) {
        this.riskScoreService = riskScoreService;
    }

    @PostMapping("/evaluate/{visitorId}")
    public RiskScore evaluate(@PathVariable Long visitorId) {
        return riskScoreService.evaluateVisitor(visitorId);
    }

    @GetMapping("/{visitorId}")
    public RiskScore getScore(@PathVariable Long visitorId) {
        return riskScoreService.getScoreForVisitor(visitorId);
    }

    @GetMapping
    public List<RiskScore> getAllScores() {
        return riskScoreService.getAllScores();
    }
}
