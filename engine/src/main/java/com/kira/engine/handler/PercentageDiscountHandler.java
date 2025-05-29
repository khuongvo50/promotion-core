package com.kira.engine.handler;

import com.kira.domain.context.PromotionContext;
import com.kira.domain.model.PromotionAction;
import com.kira.domain.model.PromotionRule;
import com.kira.domain.model.enums.PromotionType;
import com.kira.domain.result.AppliedPromotionResult;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentageDiscountHandler implements PromotionRuleHandler {

    @Override
    public PromotionType getType() {
        return PromotionType.PERCENTAGE_DISCOUNT;
    }

    @Override
    public AppliedPromotionResult apply(PromotionAction action, PromotionContext context, PromotionRule rule) {
        BigDecimal baseAmount = switch (action.getTarget()) {
            case ORDER_TOTAL -> context.getTotalAmount();
            case SHIPPING_FEE -> context.getShippingFee();
            default -> throw new IllegalStateException("Unexpected value: " + action.getTarget());
        };

        BigDecimal discount = baseAmount.multiply(action.getValue().divide(BigDecimal.valueOf(100)));

        if (action.getMaxDiscount() != null) {
            discount = discount.min(action.getMaxDiscount());
        }

        return AppliedPromotionResult.builder()
                .ruleId(rule.getId())
                .ruleName(rule.getName())
                .type(action.getType())
                .target(action.getTarget())
                .value(discount)
                .message("Giảm " + action.getValue() + "% (" + discount + "đ) vào " + action.getTarget())
                .build();
    }
}
