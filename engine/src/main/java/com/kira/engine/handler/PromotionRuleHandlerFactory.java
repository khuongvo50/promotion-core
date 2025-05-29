package com.kira.engine.handler;

import com.kira.domain.model.enums.PromotionType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PromotionRuleHandlerFactory {

    private final Map<PromotionType, PromotionRuleHandler> handlerMap = new EnumMap<>(PromotionType.class);

    public PromotionRuleHandlerFactory(List<PromotionRuleHandler> handlers) {
        for (PromotionRuleHandler handler : handlers) {
            handlerMap.put(handler.getType(), handler);
        }
    }

    public PromotionRuleHandler getHandler(PromotionType type) {
        PromotionRuleHandler handler = handlerMap.get(type);
        if (handler == null) {
            throw new IllegalArgumentException("No handler found for type: " + type);
        }
        return handler;
    }
}
