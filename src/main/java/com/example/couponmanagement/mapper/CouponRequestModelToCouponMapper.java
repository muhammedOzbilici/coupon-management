package com.example.couponmanagement.mapper;

import com.example.couponmanagement.entity.Coupon;
import com.example.couponmanagement.model.CouponRequestModel;
import org.springframework.stereotype.Component;

@Component
public class CouponRequestModelToCouponMapper {

    public static Coupon map(CouponRequestModel couponRequestModel) {
        Coupon coupon = new Coupon();
        coupon.setName(couponRequestModel.getName());
        coupon.setAssignCount(couponRequestModel.getAssignCount());
        coupon.setDiscountQuantity(couponRequestModel.getDiscountQuantity());
        coupon.setActive(couponRequestModel.isActive());
        return coupon;
    }
}
