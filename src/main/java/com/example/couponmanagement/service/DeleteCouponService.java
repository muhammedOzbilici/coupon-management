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
public class DeleteCouponService {
    private final CouponRepository couponRepository;

    public boolean deleteCoupon(String couponName) {
        boolean isSuccessfullyDeleted = true;
        Optional<Coupon> foundedCoupon = couponRepository.findByName(couponName);
        if (foundedCoupon.isPresent()) {
            couponRepository.delete(foundedCoupon.get());
        } else {
            isSuccessfullyDeleted = false;
        }
        return isSuccessfullyDeleted;
    }
}
