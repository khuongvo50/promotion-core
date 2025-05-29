package com.kira.api.config;

import com.kira.engine.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PromotionRuleHandlerConfig {

    @Bean
    public List<PromotionRuleHandler> promotionHandlers() {
        return List.of(
                new FixedDiscountHandler(),
                new PercentageDiscountHandler(),
                new RewardPointHandler(),
                new RedeemPointHandler()
        );
    }
}