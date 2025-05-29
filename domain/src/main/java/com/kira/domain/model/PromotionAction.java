package com.kira.domain.model;

import com.kira.domain.model.enums.PromotionTarget;
import com.kira.domain.model.enums.PromotionType;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class PromotionAction {
    private PromotionType type;             // FIXED_DISCOUNT, REWARD_POINT,...
    private PromotionTarget target;         // SHIPPING_FEE, ORDER_TOTAL,...
    private BigDecimal value;               // Giá trị khuyến mãi (tiền hoặc % hoặc điểm)
    private BigDecimal maxDiscount;         // optional: giới hạn nếu là giảm %

    // chỉ dùng nếu type == REDEEM_POINT
    private BigDecimal pointToCashRate;     // ví dụ: 1 point = 100đ ⇒ rate = 100
}
