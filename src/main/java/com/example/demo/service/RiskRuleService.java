package com.example.demo.service;

import com.example.demo.model.*;
import java.util.*;

public interface RiskRuleService {
    RiskRule createRule(RiskRule rule);
    List<RiskRule> getAllRules();
    RiskRule getRule(Long id);
}
