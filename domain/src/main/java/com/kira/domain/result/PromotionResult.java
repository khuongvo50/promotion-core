package com.kira.domain.result;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PromotionResult {

    // Debug / audit — giúp theo dõi rule nào đã chạy như thế nào
    private List<RuleResultPair> results;

    // Kết quả thực thi thực tế — dùng để apply vào đơn hàng
    private List<AppliedPromotionResult> appliedResults;
}
