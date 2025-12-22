package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.RiskScoreService;
import com.example.demo.util.RiskLevelUtils;
import com.example.demo.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RiskScoreServiceImpl implements RiskScoreService {

    private final VisitorRepository visitorRepository;
    private final VisitLogRepository visitLogRepository;
    private final RiskRuleRepository riskRuleRepository;
    private final RiskScoreRepository riskScoreRepository;
    private final ScoreAuditLogRepository scoreAuditLogRepository;

    public RiskScoreServiceImpl(VisitorRepository visitorRepository,
                                VisitLogRepository visitLogRepository,
                                RiskRuleRepository riskRuleRepository,
                                RiskScoreRepository riskScoreRepository,
                                ScoreAuditLogRepository scoreAuditLogRepository) {
        this.visitorRepository = visitorRepository;
        this.visitLogRepository = visitLogRepository;
        this.riskRuleRepository = riskRuleRepository;
        this.riskScoreRepository = riskScoreRepository;
        this.scoreAuditLogRepository = scoreAuditLogRepository;
    }

    @Override
    public RiskScore evaluateVisitor(Long visitorId) {
        Visitor visitor = visitorRe
