package com.kira.domain.result;


import com.kira.domain.model.enums.PromotionTarget;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class PromotionResult {
    private List<AppliedPromotionResult> appliedResults;
    private Map<PromotionTarget, List<Long>> bestRuleIdMap;
    private List<RuleResultPair> debugResults;
}
