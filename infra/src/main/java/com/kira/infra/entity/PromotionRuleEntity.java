package com.kira.infra.entity;

import com.kira.domain.model.PromotionAction;
import com.kira.infra.converter.JsonToListActionConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "promotion_rules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionRuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String expression;

    private boolean active;

    private int totalUsed;

    private int usageLimit;

    private int priority;

    private boolean combinable;

    @Convert(converter = JsonToListActionConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<PromotionAction> actions;
}
