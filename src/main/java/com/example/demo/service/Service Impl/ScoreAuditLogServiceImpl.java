package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;
import java.util.*;
import com.example.demo.exception.ResourceNotFoundException;


@Service
public class ScoreAuditLogServiceImpl implements ScoreAuditLogService {

    private final ScoreAuditLogRepository auditLogRepository;
    private final VisitorRepository visitorRepository;
    private final RiskRuleRepository ruleRepository;

    public ScoreAuditLogServiceImpl(ScoreAuditLogRepository auditLogRepository,
                                    VisitorRepository visitorRepository,
                                    RiskRuleRepository ruleRepository) {
        this.auditLogRepository = auditLogRepository;
        this.visitorRepository = visitorRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public ScoreAuditLog logScoreChange(Long visitorId, Long ruleId, ScoreAuditLog log) {
        Visitor visitor = visitorRepository.findById(visitorId)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
        RiskRule rule = ruleRepository.findById(ruleId)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        if (log.getReason() == null || log.getReason().isBlank()) {
            throw new IllegalArgumentException("reason required");
        }

        if (log.getScoreChange() < 0) {
            throw new IllegalArgumentException("scoreChange must be >= 0");
        }

        log.setVisitor(visitor);
        log.setAppliedRule(rule);

        return auditLogRepository.save(log);
    }

    @Override
    public List<ScoreAuditLog> getLogsByVisitor(Long visitorId) {
        return auditLogRepository.findByVisitorId(visitorId);
    }

    @Override
    public ScoreAuditLog getLog(Long id) {
        return auditLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Audit log not found"));
    }
}
