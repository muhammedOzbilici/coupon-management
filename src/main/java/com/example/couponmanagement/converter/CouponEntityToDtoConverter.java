package com.example.couponmanagement.converter;

import com.example.couponmanagement.dto.CouponDto;
import com.example.couponmanagement.entity.Coupon;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CouponEntityToDtoConverter implements Converter<Coupon,CouponDto> {

    @Override
    public CouponDto convert(Coupon coupon) {
        CouponDto couponDto = new CouponDto();
        couponDto.setName(coupon.getName());
        couponDto.setAssignCount(coupon.getAssignCount());
        couponDto.setDiscountQuantity(coupon.getDiscountQuantity());
        couponDto.setActive(coupon.isActive());
        return couponDto;
    }
}
