package com.kira.infra.adapter;

import com.kira.domain.PromotionRule;
import com.kira.engine.spi.PromotionRuleStorage;
import com.kira.infra.entity.PromotionRuleEntity;
import com.kira.infra.repository.PromotionRuleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PromotionRuleJpaAdapter implements PromotionRuleStorage {

    private final PromotionRuleJpaRepository repository;

    @Override
    public Optional<PromotionRule> findById(Long id) {
        return repository.findById(id)
                .map(e -> new PromotionRule(e.getId(), e.getName(), e.getExpression(), e.isActive()));
    }

    @Override
    public void save(PromotionRule rule) {
        repository.save(PromotionRuleEntity.builder()
                .id(rule.id())
                .name(rule.name())
                .expression(rule.expression())
                .active(rule.active())
                .build());
    }

    @Override
    public List<PromotionRule> findActiveRules() {
        return repository.findByActiveTrue().stream()
                .map(e -> new PromotionRule(
                        e.getId(),
                        e.getName(),
                        e.getExpression(),
                        e.isActive()
                ))
                .toList();
    }
}
