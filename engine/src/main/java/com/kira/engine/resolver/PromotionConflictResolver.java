package com.kira.engine.resolver;

import com.kira.domain.context.PromotionContext;
import com.kira.domain.result.AppliedPromotionResult;
import com.kira.domain.result.RuleResultPair;

import java.util.List;

public interface PromotionConflictResolver {
        List<Long> resolveBestRuleIds(List<AppliedPromotionResult> appliedResults);
}
