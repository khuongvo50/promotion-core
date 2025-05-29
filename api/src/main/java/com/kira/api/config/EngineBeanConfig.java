package com.kira.api.config;

import com.kira.engine.core.PromotionEngine;
import com.kira.engine.handler.PromotionRuleHandler;
import com.kira.engine.handler.PromotionRuleHandlerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EngineBeanConfig {

    @Bean
    public PromotionRuleHandlerFactory promotionRuleHandlerFactory(List<PromotionRuleHandler> handlers) {
        return new PromotionRuleHandlerFactory(handlers);
    }

    @Bean
    public PromotionEngine promotionEngine(PromotionRuleHandlerFactory factory) {
        return new PromotionEngine(factory);
    }
}
