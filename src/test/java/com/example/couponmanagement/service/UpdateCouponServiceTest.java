package com.example.couponmanagement.service;

import com.example.couponmanagement.base.BaseMockitoTest;
import com.example.couponmanagement.entity.Coupon;
import com.example.couponmanagement.model.CouponRequestModel;
import com.example.couponmanagement.repository.CouponRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

class UpdateCouponServiceTest extends BaseMockitoTest {

    @InjectMocks
    private UpdateCouponService updateCouponService;

    @Mock
    private CouponRepository couponRepository;

    @Test
    public void it_should_update_coupon() {
        //given
        Coupon coupon = new Coupon(1L, "yılbaşı", 20, 100, true, 0);
        CouponRequestModel couponRequestModel = new CouponRequestModel("yılbaşı", 15, 10, true);
        //when
        updateCouponService.updateCoupon(1L, couponRequestModel);

        //then
        verify(couponRepository).save(coupon);
    }
}