package com.kira.domain;

public record PromotionRule(
        Long id,
        String name,
        String expression,
        boolean active
) {}
