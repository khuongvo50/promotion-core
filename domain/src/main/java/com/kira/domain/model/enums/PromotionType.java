package com.kira.domain.model.enums;

/**
 * Loại hành động khuyến mãi cụ thể — mỗi rule có thể có nhiều action (tương ứng với PromotionType).
 *
 * ⚠️ Lưu ý:
 * - Mỗi type sẽ thuộc một nhóm BenefitCategory (DISCOUNT, REWARD, GIFT,...)
 * - Một rule có thể chứa nhiều action với nhiều type khác nhau
 * - Dùng để phân loại và xử lý logic trong engine (áp dụng, resolver, tracking,...)
 */
public enum PromotionType {

    /**
     * 🔖 Giảm giá cố định (theo số tiền)
     * Ví dụ: Giảm trực tiếp 20.000đ
     * → Áp dụng vào tổng đơn, phí ship, hoặc sản phẩm tùy theo target
     */
    FIXED_DISCOUNT,

    /**
     * 📉 Giảm giá theo phần trăm
     * Ví dụ: Giảm 10% tổng đơn (có thể có giới hạn maxDiscount)
     * → Phải tính toán dựa trên giá trị hiện tại
     */
    PERCENTAGE_DISCOUNT,

    /**
     * 🎁 Tặng điểm thưởng cho khách hàng
     * Ví dụ: Tặng 500 điểm khi mua đơn trên 300.000đ
     * → Không ảnh hưởng tới giá, chỉ dùng để hiển thị và tích điểm
     */
    REWARD_POINT,

    /**
     * 💱 Dùng điểm (xu) để đổi lấy giảm giá
     * Ví dụ: Dùng 100 điểm để giảm 10.000đ (tùy theo pointToCashRate)
     * → Là hình thức “đổi điểm lấy tiền”, được tính là giảm trực tiếp
     */
    REDEEM_POINT

    // ⏳ Gợi ý mở rộng: GIFT, FREE_PRODUCT, CASHBACK, FREE_SHIPPING, etc.
}
