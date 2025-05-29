package com.kira.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PromotionApplyRequest {
    private long customerId;
    private BigDecimal totalAmount;
    private BigDecimal shippingFee;
    private int availablePoints;
    private List<ItemDTO> items;

    @Data
    public static class ItemDTO {
        private long itemId;
        private int quantity;
        private BigDecimal price;
    }
}
