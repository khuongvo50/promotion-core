package com.kira.api.controller;

import com.kira.api.dto.PromotionEvaluateRequest;
import com.kira.api.dto.PromotionSuggestionDTO;
import com.kira.domain.context.Item;
import com.kira.domain.context.PromotionContext;
import com.kira.domain.model.PromotionRule;
import com.kira.domain.model.enums.PromotionTarget;
import com.kira.domain.result.AppliedPromotionResult;
import com.kira.domain.result.PromotionResult;
import com.kira.engine.core.PromotionEngine;
import com.kira.engine.spi.PromotionRuleStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/promotion")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionEngine promotionEngine;
    private final PromotionRuleStorage promotionRuleStorage;

    @PostMapping("/evaluate")
    public Map<String, List<PromotionSuggestionDTO>> evaluate(@RequestBody PromotionEvaluateRequest request) {
        PromotionContext context = fromEvaluateRequest(request);
        List<PromotionRule> rules = promotionRuleStorage.findActiveRules();

        PromotionResult result = promotionEngine.evaluate(context, rules);

        // Map target → best ruleId list
        Map<PromotionTarget, List<Long>> bestMap = result.getBestRuleIdMap();

        // Group lại theo target
        Map<PromotionTarget, List<AppliedPromotionResult>> grouped =
                result.getAppliedResults().stream()
                        .collect(Collectors.groupingBy(AppliedPromotionResult::getTarget));

        Map<String, List<PromotionSuggestionDTO>> response = new LinkedHashMap<>();

        for (Map.Entry<PromotionTarget, List<AppliedPromotionResult>> entry : grouped.entrySet()) {
            PromotionTarget target = entry.getKey();
            List<AppliedPromotionResult> list = entry.getValue();
            List<Long> bestIds = bestMap.getOrDefault(target, List.of());

            List<PromotionSuggestionDTO> suggestions = list.stream()
                    .map(r -> PromotionSuggestionDTO.builder()
                            .ruleId(r.getRuleId())
                            .ruleName(r.getRuleName())
                            .type(r.getType())
                            .target(r.getTarget())
                            .value(r.getValue())
                            .message(r.getMessage())
                            .isBest(bestIds.contains(r.getRuleId()))
                            .build())
                    .toList();

            response.put(target.name(), suggestions);
        }

        return response;
    }

    public PromotionContext fromEvaluateRequest(PromotionEvaluateRequest request) {
        PromotionContext context = new PromotionContext();
        context.setCustomerId(request.getCustomerId());
        context.setTotalAmount(request.getTotalAmount());
        context.setShippingFee(request.getShippingFee());
        context.setAvailablePoints(request.getAvailablePoints());

        context.setItems(request.getItems().stream().map(i -> {
            Item item = new Item();
            item.setItemId(i.getItemId());
            item.setQuantity(i.getQuantity());
            item.setPrice(i.getPrice());
            return item;
        }).toList());

        return context;
    }
}
