package com.example.demo.controller;

import com.example.demo.model.VisitLog;
import com.example.demo.service.VisitLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visit-logs")
public class VisitLogController {

    private final VisitLogService visitLogService;

    public VisitLogController(VisitLogService visitLogService) {
        this.visitLogService = visitLogService;
    }

    @PostMapping("/{visitorId}")
    public ResponseEntity<VisitLog> create(@PathVariable Long visitorId, @RequestBody VisitLog log) {
        VisitLog created = visitLogService.createVisitLog(visitorId, log);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitLog> get(@PathVariable Long id) {
        VisitLog log = visitLogService.getLog(id);
        return ResponseEntity.ok(log);
    }

    @GetMapping("/visitor/{visitorId}")
    public ResponseEntity<List<VisitLog>> listByVisitor(@PathVariable Long visitorId) {
        List<VisitLog> logs = visitLogService.getLogsByVisitor(visitorId);
        return ResponseEntity.ok(logs);
    }
}
