package com.example.demo.controller;

import com.example.demo.model.ScoreAuditLog;
import com.example.demo.service.ScoreAuditLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/score-logs")
@Tag(name = "Score Audit Logs")
public class ScoreAuditLogController {

    private final ScoreAuditLogService scoreAuditLogService;

    public ScoreAuditLogController(ScoreAuditLogService scoreAuditLogService) {
        this.scoreAuditLogService = scoreAuditLogService;
    }

    @PostMapping("/{visitorId}/{ruleId}")
    public ScoreAuditLog logScoreChange(
            @PathVariable Long visitorId,
            @PathVariable Long ruleId,
            @RequestBody ScoreAuditLog log) {
        return scoreAuditLogService.logScoreChange(visitorId, ruleId, log);
    }

    @GetMapping("/{id}")
    public ScoreAuditLog getLog(@PathVariable Long id) {
        return scoreAuditLogService.getLog(id);
    }

    @GetMapping("/visitor/{visitorId}")
    public List<ScoreAuditLog> getLogsByVisitor(@PathVariable Long visitorId) {
        return scoreAuditLogService.getLogsByVisitor(visitorId);
    }
}
