package com.kira.domain.model;

import com.kira.domain.model.enums.PromotionType;
import lombok.Data;
import java.util.List;

@Data
public class PromotionRule {
    private Long id;
    private String name;
    private String expression;      // SpEL điều kiện áp dụng
    private boolean active;

    private int totalUsed;
    private int usageLimit;
    private int priority;           // độ ưu tiên
    private boolean combinable;     // có cộng dồn với rule khác không

    private List<PromotionAction> actions; // danh sách action đi kèm rule
}
