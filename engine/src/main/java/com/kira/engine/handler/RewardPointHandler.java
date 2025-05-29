package com.kira.engine.handler;

import com.kira.domain.context.PromotionContext;
import com.kira.domain.model.PromotionAction;
import com.kira.domain.model.PromotionRule;
import com.kira.domain.model.enums.PromotionType;
import com.kira.domain.result.AppliedPromotionResult;

public class RewardPointHandler implements PromotionRuleHandler {

    @Override
    public PromotionType getType() {
        return PromotionType.REWARD_POINT;
    }

    @Override
    public AppliedPromotionResult apply(PromotionAction action, PromotionContext context, PromotionRule rule) {
        return AppliedPromotionResult.builder()
                .ruleId(rule.getId())
                .ruleName(rule.getName())
                .type(action.getType())
                .target(null) // không áp dụng lên phí hay đơn hàng
                .value(action.getValue())
                .message("Tặng " + action.getValue() + " điểm thưởng")
                .build();
    }
}
