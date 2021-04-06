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
public class AssignCouponService {
    private final CouponRepository couponRepository;

    public boolean assignCoupon(Coupon coupon, String userName) {
        boolean isSuccessfullyAssigned = true;
        String couponName = coupon.getName();
        Optional<Coupon> foundedCoupon = couponRepository.findByName(couponName);
        if (foundedCoupon.isPresent()) {
            couponRepository.setCouponAssignCountByCouponName(foundedCoupon.get().getName(), foundedCoupon.get().getAssignCount() + 1);
        } else {
            isSuccessfullyAssigned = false;
        }
        return isSuccessfullyAssigned;
    }
}
