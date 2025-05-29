package com.kira.api.controller;

import com.kira.api.dto.PromotionApplyRequest;
import com.kira.domain.PromotionContext;
import com.kira.domain.result.PromotionResult;
import com.kira.engine.core.PromotionEngine;
import com.kira.engine.spi.PromotionRuleStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotion")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionEngine promotionEngine;
    private final PromotionRuleStorage promotionRuleStorage;

    @PostMapping("/apply")
    public PromotionResult applyPromotion(@RequestBody PromotionApplyRequest request) {
        PromotionContext context = new PromotionContext(
                request.customerId(),
                request.totalAmount(),
                request.shippingFee(),
                request.availablePoints(),
                request.items()
        );

        // Lấy tất cả rule (hoặc sau này có filter active, channel...)
        List<com.kira.domain.PromotionRule> rules = promotionRuleStorage.findActiveRules();

        return promotionEngine.apply(context, rules);
    }
}
