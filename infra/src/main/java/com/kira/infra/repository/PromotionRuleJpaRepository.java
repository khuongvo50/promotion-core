package com.kira.infra.repository;

import com.kira.infra.entity.PromotionRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRuleJpaRepository extends JpaRepository<PromotionRuleEntity, Long> {
    List<PromotionRuleEntity> findByActiveTrue();
}
