package com.kira.domain;

import java.math.BigDecimal;
import java.util.List;

public record PromotionContext(
        String customerId,
        BigDecimal totalAmount,
        BigDecimal shippingFee,
        int availablePoints,
        List<CartItem> items
) {}
