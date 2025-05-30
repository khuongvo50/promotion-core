package com.kira.domain.result;


import com.kira.domain.model.enums.BenefitCategory;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class PromotionResult {
    private Map<BenefitCategory, List<AppliedPromotionResult>> groupedResults;
    private List<Long> bestDiscountRuleIds;
    private List<RuleResultPair> debugResults;
}
