package com.kira.infra.adapter;

import com.kira.domain.model.PromotionRule;
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
                .map(this::mapToDomain);
    }

    @Override
    public void save(PromotionRule rule) {
        PromotionRuleEntity entity = PromotionRuleEntity.builder()
                .id(rule.getId())
                .name(rule.getName())
                .expression(rule.getExpression())
                .active(rule.isActive())
                .totalUsed(rule.getTotalUsed())
                .usageLimit(rule.getUsageLimit())
                .priority(rule.getPriority())
                .combinable(rule.isCombinable())
                .actions(rule.getActions())
                .build();

        repository.save(entity);
    }

    @Override
    public List<PromotionRule> findActiveRules() {
        return repository.findByActiveTrue().stream()
                .map(this::mapToDomain)
                .toList();
    }

    private PromotionRule mapToDomain(PromotionRuleEntity entity) {
        PromotionRule rule = new PromotionRule();
        rule.setId(entity.getId());
        rule.setName(entity.getName());
        rule.setExpression(entity.getExpression());
        rule.setActive(entity.isActive());
        rule.setTotalUsed(entity.getTotalUsed());
        rule.setUsageLimit(entity.getUsageLimit());
        rule.setPriority(entity.getPriority());
        rule.setCombinable(entity.isCombinable());
        rule.setActions(entity.getActions());
        return rule;
    }
}