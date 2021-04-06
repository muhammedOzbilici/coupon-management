package com.example.couponmanagement.service;

import com.example.couponmanagement.entity.Coupon;
import com.example.couponmanagement.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateCouponService {
    private final CouponRepository couponRepository;

    public boolean updateCoupon(Coupon coupon) {
        boolean isSuccessfullyUpdated = true;
        String couponName = coupon.getName();
        Optional<Coupon> foundedCoupon = couponRepository.findByName(couponName);
        if (foundedCoupon.isPresent()) {
            couponRepository.save(coupon);
        } else {
            isSuccessfullyUpdated = false;
        }
        return isSuccessfullyUpdated;
    }
}
