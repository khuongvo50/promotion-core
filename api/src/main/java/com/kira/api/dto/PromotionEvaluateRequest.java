package com.kira.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PromotionEvaluateRequest {
    private Long customerId;
    private BigDecimal totalAmount;
    private BigDecimal shippingFee;
    private int availablePoints;
    private List<ItemDTO> items;

    @Data
    public static class ItemDTO {
        private Long itemId;
        private Integer quantity;
        private BigDecimal price;
    }
}
