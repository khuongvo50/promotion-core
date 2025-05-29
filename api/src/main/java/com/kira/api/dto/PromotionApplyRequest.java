package com.kira.api.dto;

import com.kira.domain.CartItem;

import java.math.BigDecimal;
import java.util.List;

public record PromotionApplyRequest(
        String customerId,
        BigDecimal totalAmount,
        BigDecimal shippingFee,
        int availablePoints,
        List<CartItem> items
) {}
