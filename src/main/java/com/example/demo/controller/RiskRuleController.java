package com.example.demo.controller;

import com.example.demo.model.RiskRule;
import com.example.demo.service.RiskRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/risk-rules")
@Tag(name = "Risk Rules")
public class RiskRuleController {

    private final RiskRuleService riskRuleService;

    public RiskRuleController(RiskRuleService riskRuleService) {
        this.riskRuleService = riskRuleService;
    }

    @PostMapping
    public RiskRule createRule(@RequestBody RiskRule rule) {
        return riskRuleService.createRule(rule);
    }

    @GetMapping("/{id}")
    public RiskRule getRule(@PathVariable Long id) {
        return riskRuleService.getRule(id);
    }

    @GetMapping
    public List<RiskRule> getAllRules() {
        return riskRuleService.getAllRules();
    }
}
