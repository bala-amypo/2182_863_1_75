package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface RiskRuleRepository extends JpaRepository<RiskRule, Long> {
}
