package com.kira.infra.entity;

import jakarta.persistence.*;
import lombok.*;

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
}
