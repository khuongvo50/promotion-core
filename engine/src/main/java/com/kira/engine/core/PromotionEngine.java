package com.kira.engine.core;

import com.kira.domain.PromotionContext;
import com.kira.domain.PromotionRule;
import com.kira.domain.result.PromotionResult;
import com.kira.domain.result.RuleResultPair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PromotionEngine {

    private final ExpressionParser parser = new SpelExpressionParser();

    public PromotionResult apply(PromotionContext context, List<PromotionRule> rules) {
        List<RuleResultPair> resultPairs = new ArrayList<>();

        for (PromotionRule rule : rules) {
            if (!rule.active()) continue;

            try {
                StandardEvaluationContext spelContext = new StandardEvaluationContext(context);
                Boolean passed = parser.parseExpression(rule.expression()).getValue(spelContext, Boolean.class);

                if (Boolean.TRUE.equals(passed)) {
                    resultPairs.add(new RuleResultPair(rule, "APPLIED"));
                } else {
                    resultPairs.add(new RuleResultPair(rule, "SKIPPED"));
                }

            } catch (Exception e) {
                log.error("Rule [{}] failed: {}", rule.name(), e.getMessage());
                resultPairs.add(new RuleResultPair(rule, "ERROR"));
            }
        }

        return new PromotionResult(resultPairs);
    }
}
