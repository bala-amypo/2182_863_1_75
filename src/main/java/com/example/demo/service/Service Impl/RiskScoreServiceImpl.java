package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import com.example.demo.util.RiskLevelUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RiskScoreServiceImpl implements RiskScoreService {

    private final RiskScoreRepository riskScoreRepository;
    private final VisitorRepository visitorRepository;
    private final RiskRuleRepository riskRuleRepository;

    public RiskScoreServiceImpl(RiskScoreRepository riskScoreRepository,
                                VisitorRepository visitorRepository,
                                RiskRuleRepository riskRuleRepository) {
        this.riskScoreRepository = riskScoreRepository;
        this.visitorRepository = visitorRepository;
        this.riskRuleRepository = riskRuleRepository;
    }

    @Override
    public RiskScore evaluateVisitor(Long visitorId) {
        Visitor visitor = visitorRepository.findById(visitorId)
                .orElseThrow(() -> new RuntimeException("Visitor not found"));

        return riskScoreRepository.findByVisitorId(visitorId).orElseGet(() -> {
            int scoreValue = 50;

            RiskScore score = new RiskScore();
            score.setVisitor(visitor);
            score.setTotalScore(scoreValue);
            score.setRiskLevel(RiskLevelUtils.determineRiskLevel(scoreValue));

            RiskRule appliedRule = riskRuleRepository.findAll().stream().findFirst()
                    .orElseThrow(() -> new RuntimeException("No RiskRule found"));
            score.setRiskRule(appliedRule);

            return riskScoreRepository.save(score);
        });
    }

    @Override
    public RiskScore getScoreForVisitor(Long visitorId) {
        return riskScoreRepository.findByVisitorId(visitorId).orElse(null);
    }

    @Override
    public List<RiskScore> getAllScores() {
        return riskScoreRepository.findAll();
    }
}
