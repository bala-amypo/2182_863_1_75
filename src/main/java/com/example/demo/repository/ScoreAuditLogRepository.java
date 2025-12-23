package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.demo.exception.ResourceNotFoundException;


@Repository
public interface ScoreAuditLogRepository extends JpaRepository<ScoreAuditLog, Long> {
    List<ScoreAuditLog> findByVisitorId(Long visitorId);
}
