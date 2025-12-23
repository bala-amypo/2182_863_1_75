package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.stereotype.*;
import java.util.*;

@Service
public class RiskRuleServiceImpl implements RiskRuleService {

    private final RiskRuleRepository riskRuleRepository;

    public RiskRuleServiceImpl(RiskRuleRepository riskRuleRepository) {
        this.riskRuleRepository = riskRuleRepository;
    }

    @Override
    public RiskRule createRule(RiskRule rule) {
        return riskRuleRepository.save(rule);
    }

    @Override
    public List<RiskRule> getAllRules() {
        return riskRuleRepository.findAll();
    }

    @Override
    public RiskRule getRule(Long id) {
        return riskRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RiskRule not found"));
    }
}
