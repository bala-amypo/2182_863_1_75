package com.example.demo.service.Impl;

import com.example.demo.model.RiskScore;
import com.example.demo.repository.RiskScoreRepository;
import com.example.demo.service.RiskScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskScoreServiceImpl implements RiskScoreService {

    private final RiskScoreRepository riskScoreRepository;

    public RiskScoreServiceImpl(RiskScoreRepository riskScoreRepository) {
        this.riskScoreRepository = riskScoreRepository;
    }

    @Override
    public List<RiskScore> getAllScores() {
        return riskScoreRepository.findAll();
    }
}
