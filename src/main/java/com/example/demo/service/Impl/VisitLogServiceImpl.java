package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.VisitLog;
import com.example.demo.model.Visitor;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.service.VisitLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitLogServiceImpl implements VisitLogService {

    private final VisitLogRepository visitLogRepository;
    private final VisitorRepository visitorRepository;

    public VisitLogServiceImpl(VisitLogRepository visitLogRepository,
                               VisitorRepository visitorRepository) {
        this.visitLogRepository = visitLogRepository;
        this.visitorRepository = visitorRepository;
    }

    @Override
    public VisitLog createVisitLog(Long visitorId, VisitLog log) {
        Visitor visitor = visitorRepository.findById(visitorId)
                .orElseThrow(() -> new BadRequestException("Visitor not found"));

        if (log.getEntryTime() == null) {
            throw new BadRequestException("entryTime required");
        }

        if (log.getExitTime() != null && !log.getExitTime().isAfter(log.getEntryTime())) {
            throw new BadRequestException("exitTime must be after entryTime");
        }

        if (log.getPurpose() == null || log.getPurpose().isBlank()) {
            throw new BadRequestException("purpose required");
        }

        if (log.getLocation() == null || log.getLocation().isBlank()) {
            throw new BadRequestException("location required");
        }

        log.setVisitor(visitor);
        return visitLogRepository.save(log);
    }

    @Override
    public VisitLog getLog(Long id) {
        return visitLogRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("VisitLog not found"));
    }

    @Override
    public List<VisitLog> getLogsByVisitor(Long visitorId) {
        return visitLogRepository.findByVisitorSince(visitorId, null); // returns all
    }
}
