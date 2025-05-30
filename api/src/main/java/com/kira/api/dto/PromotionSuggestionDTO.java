package com.kira.api.dto;

import com.kira.domain.model.enums.PromotionType;
import com.kira.domain.model.enums.PromotionTarget;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PromotionSuggestionDTO {
    private Long ruleId;
    private String ruleName;
    private PromotionType type;
    private PromotionTarget target;
    private BigDecimal value;
    private String message;
    private boolean isBest;
}
