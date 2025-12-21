package com.example.demo.util;

public class RiskLevelUtils {

    /**
     * Determine risk level based on total score.
     * Mapping:
     * 0-19   -> LOW
     * 20-49  -> MEDIUM
     * 50-79  -> HIGH
     * 80+    -> CRITICAL
     *
     * @param score the total score
     * @return risk level as String
     */
    public static String determineRiskLevel(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        } else if (score <= 19) {
            return "LOW";
        } else if (score <= 49) {
            return "MEDIUM";
        } else if (score <= 79) {
            return "HIGH";
        } else {
            return "CRITICAL";
        }
    }
}
