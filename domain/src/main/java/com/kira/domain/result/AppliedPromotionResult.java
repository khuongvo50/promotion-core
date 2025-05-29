package com.kira.domain.result;

import com.kira.domain.model.enums.PromotionTarget;
import com.kira.domain.model.enums.PromotionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AppliedPromotionResult {

    private Long ruleId;                   // ID của rule
    private String ruleName;               // Tên rule để hiển thị/log

    private PromotionType type;            // FIXED_DISCOUNT, REWARD_POINT,...
    private PromotionTarget target;        // ORDER_TOTAL, SHIPPING_FEE,...

    private BigDecimal value;              // Giá trị đã áp dụng thực tế
    private String message;                // Mô tả (e.g. “Giảm 10% phí ship”)
}
