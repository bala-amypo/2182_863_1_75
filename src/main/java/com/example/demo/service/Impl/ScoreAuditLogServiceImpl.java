package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ScoreAuditLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScoreAuditLogServiceImpl implements ScoreAuditLogService {

    private final ScoreAuditLogRepository scoreAuditLogRepository;
    private final VisitorRepository visitorRepository;
    private final RiskRuleRepository riskRuleRepository;

    public ScoreAuditLogServiceImpl(ScoreAuditLogRepository scoreAuditLogRepository,
                                    VisitorRepository visitorRepository,
                                    RiskRuleRepository riskRuleRepository) {
        this.scoreAuditLogRepository = scoreAuditLogRepository;
        this.visitorRepository = visitorRepository;
        this.riskRuleRepository = riskRuleRepository;
    }

    @Override
    public ScoreAuditLog logScoreChange(Long visitorId, Long ruleId, ScoreAuditLog log) {
        Visitor visitor = visitorRepository.findById(visitorId)
                .orElseThrow(() -> new BadRequestException("Visitor not found"));
        RiskRule rule = riskRuleRepository.findById(ruleId)
                .orElseThrow(() -> new BadRequestException("Rule not found"));

        if (log.getReason() == null || log.getReason().isBlank()) {
            throw new BadRequestException("reason required");
        }

        log.setVisitor(visitor);
        log.setAppliedRule(rule);
        log.setScoreChange(Math.max(log.getScoreChange(), 0));
        log.setLoggedAt(LocalDateTime.now());

        return scoreAuditLogRepository.save(log);
    }

    @Override
    public ScoreAuditLog getLog(Long id) {
        return scoreAuditLogRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("ScoreAuditLog not found"));
    }

    @Override
    public List<ScoreAuditLog> getLogsByVisitor(Long visitorId) {
        return scoreAuditLogRepository.findByVisitorId(visitorId);
    }
}
