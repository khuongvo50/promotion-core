package com.kira.domain.context;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Item {

    private long itemId;
    private int quantity;

    // Optional: giá nếu cần xử lý sau này (vd: áp dụng theo giá sp)
    private BigDecimal price;

    public Item() {}

    public Item(long itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Item(long itemId, int quantity, BigDecimal price) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }
}
