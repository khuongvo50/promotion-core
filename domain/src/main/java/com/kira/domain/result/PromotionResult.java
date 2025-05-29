package com.kira.domain.result;

import java.util.List;

public record PromotionResult(
        List<RuleResultPair> results
) {}
