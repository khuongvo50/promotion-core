package com.kira.engine.core;

import com.kira.domain.context.PromotionContext;
import com.kira.domain.model.PromotionAction;
import com.kira.domain.model.PromotionRule;
import com.kira.domain.model.enums.BenefitCategory;
import com.kira.domain.result.AppliedPromotionResult;
import com.kira.domain.result.PromotionResult;
import com.kira.domain.result.RuleResultPair;
import com.kira.domain.result.enums.RuleApplyStatus;
import com.kira.engine.handler.PromotionRuleHandler;
import com.kira.engine.handler.PromotionRuleHandlerFactory;
import com.kira.engine.helper.BenefitCategoryHelper;
import com.kira.engine.resolver.MaxCashDiscountResolver;
import com.kira.engine.resolver.PromotionConflictResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class PromotionEngine {
    private final ExpressionParser parser = new SpelExpressionParser();

    private final PromotionRuleHandlerFactory handlerFactory;
    private final PromotionConflictResolver discountResolver;

    public PromotionEngine(PromotionRuleHandlerFactory handlerFactory,
                           PromotionConflictResolver discountResolver) {
        this.handlerFactory = handlerFactory;
        this.discountResolver = discountResolver;
    }

    public PromotionResult evaluate(PromotionContext context, List<PromotionRule> rules) {
        List<RuleResultPair> debugResults = new ArrayList<>();
        List<AppliedPromotionResult> appliedResults = new ArrayList<>();

        for (PromotionRule rule : rules) {
            if (!rule.isActive()) {
                debugResults.add(new RuleResultPair(rule, RuleApplyStatus.SKIPPED, "Rule is not active"));
                continue;
            }

            try {
                StandardEvaluationContext spelContext = new StandardEvaluationContext(context);
                Boolean passed = parser.parseExpression(rule.getExpression()).getValue(spelContext, Boolean.class);

                if (Boolean.TRUE.equals(passed)) {
                    for (PromotionAction action : rule.getActions()) {
                        PromotionRuleHandler handler = handlerFactory.getHandler(action.getType());
                        AppliedPromotionResult result = handler.apply(action, context, rule);
                        appliedResults.add(result);
                    }

                    debugResults.add(new RuleResultPair(rule, RuleApplyStatus.APPLIED, "All actions applied"));
                } else {
                    debugResults.add(new RuleResultPair(rule, RuleApplyStatus.SKIPPED, "Condition did not match"));
                }

            } catch (Exception e) {
                log.error("Rule [{}] evaluation failed: {}", rule.getName(), e.getMessage());
                debugResults.add(new RuleResultPair(rule, RuleApplyStatus.ERROR, "Exception: " + e.getMessage()));
            }
        }

        Map<BenefitCategory, List<AppliedPromotionResult>> grouped = BenefitCategoryHelper.groupByCategory(appliedResults);

        List<AppliedPromotionResult> discountGroup = grouped.getOrDefault(BenefitCategory.DISCOUNT, List.of());
        List<Long> bestDiscountRuleIds = discountResolver.resolveBestRuleIds(discountGroup);

        return PromotionResult.builder()
                .groupedResults(grouped)
                .bestDiscountRuleIds(bestDiscountRuleIds)
                .debugResults(debugResults)
                .build();
    }
}
