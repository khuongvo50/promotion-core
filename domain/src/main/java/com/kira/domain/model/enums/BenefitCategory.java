package com.kira.domain.model.enums;

/**
 * Nhóm phân loại lợi ích của khuyến mãi — dùng để tách xử lý đánh giá ("best") và hiển thị.
 *
 * ⚠️ Lưu ý:
 * - Chỉ nhóm DISCOUNT mới dùng để đánh giá "best rule"
 * - Các nhóm REWARD, GIFT chỉ phục vụ mục đích hiển thị
 * - Dễ mở rộng khi thêm loại khuyến mãi mới (FREE_SHIPPING, CASHBACK, ...)
 */
public enum BenefitCategory {

    /**
     * 💸 Nhóm khuyến mãi giảm giá trực tiếp (có thể quy đổi ra tiền)
     * Bao gồm: FIXED_DISCOUNT, PERCENTAGE_DISCOUNT, REDEEM_POINT
     */
    DISCOUNT,

    /**
     * 🎁 Nhóm tặng điểm thưởng (không ảnh hưởng trực tiếp tới giá đơn hàng)
     * Bao gồm: REWARD_POINT
     */
    REWARD,

    /**
     * 🧧 Nhóm tặng quà hiện vật, voucher,... (không quy đổi được giá trị ngay)
     * Bao gồm: GIFT, FREE_PRODUCT (tùy mở rộng)
     */
    GIFT,

    /**
     * ❓ Dành cho các loại khuyến mãi chưa rõ hoặc chưa được hỗ trợ phân loại
     */
    UNKNOWN
}