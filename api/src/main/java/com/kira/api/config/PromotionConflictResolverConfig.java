package com.kira.api.config;

import com.kira.engine.resolver.MaxCashDiscountResolver;
import com.kira.engine.resolver.PromotionConflictResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PromotionConflictResolverConfig {
    @Bean
    public PromotionConflictResolver maxCashDiscountResolver() {
        return new MaxCashDiscountResolver();
    }
}
