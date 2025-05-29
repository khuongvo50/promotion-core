package com.kira.engine.handler;

import com.kira.domain.context.PromotionContext;
import com.kira.domain.model.PromotionAction;
import com.kira.domain.model.PromotionRule;
import com.kira.domain.result.AppliedPromotionResult;
import com.kira.domain.model.enums.PromotionType;

public interface PromotionRuleHandler {
    PromotionType getType();

    AppliedPromotionResult apply(PromotionAction action, PromotionContext context, PromotionRule rule);
}
