package com.kira.engine.helper;


import com.kira.domain.model.enums.BenefitCategory;
import com.kira.domain.model.enums.PromotionType;
import com.kira.domain.result.AppliedPromotionResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BenefitCategoryHelper {

    public static BenefitCategory map(PromotionType type) {
        return switch (type) {
            case FIXED_DISCOUNT, PERCENTAGE_DISCOUNT, REDEEM_POINT -> BenefitCategory.DISCOUNT;
            case REWARD_POINT -> BenefitCategory.REWARD;
//            case GIFT -> BenefitCategory.GIFT; // tương lai mở rộng
            default -> BenefitCategory.UNKNOWN;
        };
    }

    public static Map<BenefitCategory, List<AppliedPromotionResult>> groupByCategory(List<AppliedPromotionResult> appliedResults) {
        return appliedResults.stream()
                .collect(Collectors.groupingBy(r -> BenefitCategoryHelper.map(r.getType())));
    }
}
