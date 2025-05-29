package com.kira.domain.result;

import com.kira.domain.PromotionRule;

public record RuleResultPair(
        PromotionRule rule,
        String status // e.g. "APPLIED", "SKIPPED", "ERROR"
) {}
