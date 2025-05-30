package com.kira.engine.resolver;

import com.kira.domain.model.enums.PromotionType;
import com.kira.domain.result.AppliedPromotionResult;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MaxCashDiscountResolver implements PromotionConflictResolver {

    @Override
    public List<Long> resolveBestRuleIds(List<AppliedPromotionResult> appliedResults) {
        // Bỏ qua các loại không giảm trực tiếp (như REWARD_POINT)
        Map<Long, BigDecimal> totalDiscountByRule = appliedResults.stream()
                .filter(r -> isDiscountType(r.getType()))
                .collect(Collectors.groupingBy(
                        AppliedPromotionResult::getRuleId,
                        Collectors.mapping(
                                AppliedPromotionResult::getValue,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        )
                ));

        BigDecimal maxDiscount = totalDiscountByRule.values().stream()
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        return totalDiscountByRule.entrySet().stream()
                .filter(e -> e.getValue().compareTo(maxDiscount) == 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private boolean isDiscountType(PromotionType type) {
        return type == PromotionType.FIXED_DISCOUNT
                || type == PromotionType.PERCENTAGE_DISCOUNT
                || type == PromotionType.REDEEM_POINT;
    }
}
