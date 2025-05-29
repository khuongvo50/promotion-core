package com.kira.api.controller;

import com.kira.api.dto.PromotionApplyRequest;
import com.kira.domain.context.Item;
import com.kira.domain.context.PromotionContext;
import com.kira.domain.model.PromotionRule;
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
        PromotionContext context = new PromotionContext();
        context.setCustomerId(request.getCustomerId());
        context.setTotalAmount(request.getTotalAmount());
        context.setShippingFee(request.getShippingFee());
        context.setAvailablePoints(request.getAvailablePoints());

        context.setItems(
                request.getItems().stream()
                        .map(i -> {
                            var item = new Item();
                            item.setItemId(i.getItemId());
                            item.setQuantity(i.getQuantity());
                            item.setPrice(i.getPrice());
                            return item;
                        }).toList()
        );

        List<PromotionRule> rules = promotionRuleStorage.findActiveRules();
        return promotionEngine.evaluate(context, rules);
    }
}
