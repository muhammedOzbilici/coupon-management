package com.example.couponmanagement.service;

import com.example.couponmanagement.entity.Coupon;
import com.example.couponmanagement.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListCouponService {
    private final CouponRepository couponRepository;

    public List<Coupon> listCoupon() {
        return couponRepository.findAll();
    }
}
