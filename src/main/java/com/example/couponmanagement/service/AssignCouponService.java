package com.example.couponmanagement.service;

import com.example.couponmanagement.entity.Coupon;
import com.example.couponmanagement.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleObjectStateException;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssignCouponService {
    private final CouponRepository couponRepository;

    @Retryable(include = {ConcurrencyFailureException.class, StaleObjectStateException.class},
            backoff = @Backoff(delay = 100L, multiplier = 3), maxAttempts = 5)
    public boolean assignCoupon(long id, String username) {
        boolean isSuccessfullyAssigned = true;
        Optional<Coupon> foundedCoupon = couponRepository.findById(id);
        if (foundedCoupon.isPresent()) {
            Coupon coupon = foundedCoupon.get();
            int assignCount = coupon.getAssignCount();
            if (assignCount > 0) {
                int newAssignCount = assignCount - 1;
                coupon.setActive(newAssignCount != 0);
                coupon.setAssignCount(newAssignCount);
                couponRepository.saveAndFlush(coupon);
            } else {
                isSuccessfullyAssigned = false;
            }
        } else {
            isSuccessfullyAssigned = false;
        }
        return isSuccessfullyAssigned;
    }
}
