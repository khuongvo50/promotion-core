package com.kira.engine.handler;

import com.kira.domain.context.PromotionContext;
import com.kira.domain.model.PromotionAction;
import com.kira.domain.model.PromotionRule;
import com.kira.domain.model.enums.PromotionType;
import com.kira.domain.result.AppliedPromotionResult;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RedeemPointHandler implements PromotionRuleHandler {

    @Override
    public PromotionType getType() {
        return PromotionType.REDEEM_POINT;
    }

    @Override
    public AppliedPromotionResult apply(PromotionAction action, PromotionContext context, PromotionRule rule) {
        BigDecimal maxPoint = BigDecimal.valueOf(context.getAvailablePoints());
        BigDecimal cash = maxPoint.multiply(action.getPointToCashRate());

        // Nếu có giới hạn, lấy min giữa tiền tính được và maxDiscount
        if (action.getMaxDiscount() != null) {
            cash = cash.min(action.getMaxDiscount());
        }

        // Tính lại số điểm thực sự được sử dụng (để log message chuẩn hơn nếu cần)
        BigDecimal pointUsed = cash.divide(action.getPointToCashRate(), 0, RoundingMode.DOWN);

        return AppliedPromotionResult.builder()
                .ruleId(rule.getId())
                .ruleName(rule.getName())
                .type(action.getType())
                .target(action.getTarget())
                .value(cash)
                .message("Đổi " + pointUsed.intValue() + " điểm thành " + cash + "đ")
                .build();
    }
}
