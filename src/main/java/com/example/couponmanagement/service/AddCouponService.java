package com.example.couponmanagement.service;

import com.example.couponmanagement.entity.Coupon;
import com.example.couponmanagement.mapper.CouponRequestModelToCouponMapper;
import com.example.couponmanagement.model.CouponRequestModel;
import com.example.couponmanagement.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddCouponService {
    private final CouponRepository couponRepository;

    public boolean addCoupon(CouponRequestModel couponRequestModel) {
        boolean isSuccessfullyAdded = true;
        String couponName = couponRequestModel.getName();
        Optional<Coupon> foundedCoupon = couponRepository.findByName(couponName);
        if (foundedCoupon.isPresent()) {
            isSuccessfullyAdded = false;
        } else {
            Coupon coupon = CouponRequestModelToCouponMapper.map(couponRequestModel);
            if (coupon.getAssignCount() == 0) {
                coupon.setActive(false);
            }
            couponRepository.save(coupon);
        }
        return isSuccessfullyAdded;
    }

    public void addCoupon(Coupon capture) {

    }
}
