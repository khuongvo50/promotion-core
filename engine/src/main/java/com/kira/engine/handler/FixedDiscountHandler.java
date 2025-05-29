package com.kira.engine.handler;

import com.kira.domain.context.PromotionContext;
import com.kira.domain.model.PromotionAction;
import com.kira.domain.model.PromotionRule;
import com.kira.domain.model.enums.PromotionType;
import com.kira.domain.result.AppliedPromotionResult;
import com.kira.engine.handler.PromotionRuleHandler;

import java.math.BigDecimal;

public class FixedDiscountHandler implements PromotionRuleHandler {

    @Override
    public PromotionType getType() {
        return PromotionType.FIXED_DISCOUNT;
    }

    @Override
    public AppliedPromotionResult apply(PromotionAction action, PromotionContext context, PromotionRule rule) {
        BigDecimal discountAmount = action.getValue();

        return AppliedPromotionResult.builder()
                .ruleId(rule.getId())
                .ruleName(rule.getName())
                .type(action.getType())
                .target(action.getTarget())
                .value(discountAmount)
                .message("Giảm trực tiếp " + discountAmount + "đ vào " + action.getTarget())
                .build();
    }
}
