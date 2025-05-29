package com.kira.domain.result;

import com.kira.domain.model.PromotionRule;
import com.kira.domain.result.enums.RuleApplyStatus;

public record RuleResultPair(
        PromotionRule rule,
        RuleApplyStatus status, // e.g. "APPLIED", "SKIPPED", "ERROR"
        String message
) {}
