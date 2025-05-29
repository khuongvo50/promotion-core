package com.kira.api.config;

import com.kira.engine.core.PromotionEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EngineBeanConfig {

    @Bean
    public PromotionEngine promotionEngine() {
        return new PromotionEngine();
    }
}
