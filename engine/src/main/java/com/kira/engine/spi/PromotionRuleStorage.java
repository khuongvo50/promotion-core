package com.kira.engine.spi;

import com.kira.domain.PromotionRule;

import java.util.List;
import java.util.Optional;

public interface PromotionRuleStorage {
    Optional<PromotionRule> findById(Long id);
    void save(PromotionRule rule);

    List<PromotionRule> findActiveRules();
}
