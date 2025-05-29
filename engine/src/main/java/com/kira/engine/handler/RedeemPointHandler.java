package com.kira.engine.handler;

import com.kira.domain.context.PromotionContext;
import com.kira.domain.model.PromotionAction;
import com.kira.domain.model.PromotionRule;
import com.kira.domain.model.enums.PromotionType;
import com.kira.domain.result.AppliedPromotionResult;

import java.math.BigDecimal;

public class RedeemPointHandler implements PromotionRuleHandler {

    @Override
    public PromotionType getType() {
        return PromotionType.REDEEM_POINT;
    }

    @Override
    public AppliedPromotionResult apply(PromotionAction action, PromotionContext context, PromotionRule rule) {
        BigDecimal maxPoint = BigDecimal.valueOf(context.getAvailablePoints());
        BigDecimal cash = maxPoint.multiply(action.getPointToCashRate());

        return AppliedPromotionResult.builder()
                .ruleId(rule.getId())
                .ruleName(rule.getName())
                .type(action.getType())
                .target(null)
                .value(cash)
                .message("Đổi " + maxPoint.intValue() + " điểm thành " + cash + "đ")
                .build();
    }
}
