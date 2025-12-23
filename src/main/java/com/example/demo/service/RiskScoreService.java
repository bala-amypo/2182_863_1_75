package com.example.demo.service;

import com.example.demo.model.*;
import java.util.*;

public interface RiskScoreService {
    RiskScore evaluateVisitor(Long visitorId);
    RiskScore getScoreForVisitor(Long visitorId);
    List<RiskScore> getAllScores();
}
