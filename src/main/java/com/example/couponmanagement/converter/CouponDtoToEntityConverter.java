package com.example.couponmanagement.converter;

import com.example.couponmanagement.dto.CouponDto;
import com.example.couponmanagement.entity.Coupon;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CouponDtoToEntityConverter implements Converter<CouponDto, Coupon> {
    @Override
    public Coupon convert(CouponDto couponDto) {
        Coupon coupon = new Coupon();
        coupon.setName(couponDto.getName());
        coupon.setAssignCount(couponDto.getAssignCount());
        coupon.setDiscountQuantity(couponDto.getDiscountQuantity());
        coupon.setActive(couponDto.isActive());
        return coupon;
    }
}
