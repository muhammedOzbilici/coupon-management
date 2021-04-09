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
public class UpdateCouponService {
    private final CouponRepository couponRepository;

    public boolean updateCoupon(Long id, CouponRequestModel couponRequestModel) {
        boolean isSuccessfullyUpdated = true;
        Optional<Coupon> foundedCoupon = couponRepository.findById(id);
        if (foundedCoupon.isPresent()) {
            Coupon coupon = CouponRequestModelToCouponMapper.map(couponRequestModel);
            coupon.setId(foundedCoupon.get().getId());
            if (couponRequestModel.getAssignCount() == 0) {
                coupon.setActive(false);
            }
            couponRepository.saveAndFlush(coupon);
        } else {
            isSuccessfullyUpdated = false;
        }
        return isSuccessfullyUpdated;
    }
}
