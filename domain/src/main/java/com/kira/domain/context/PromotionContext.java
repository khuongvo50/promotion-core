package com.kira.domain.context;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PromotionContext {

    private long customerId;
    private BigDecimal totalAmount;
    private BigDecimal shippingFee;
    private int availablePoints;
    private List<Item> items;

    public PromotionContext(long customerId,
                            BigDecimal totalAmount,
                            BigDecimal shippingFee,
                            int availablePoints,
                            List<Item> items) {
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.shippingFee = shippingFee;
        this.availablePoints = availablePoints;
        this.items = items;
    }

    public PromotionContext() {}

    public BigDecimal getSubtotal() {
        return items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
