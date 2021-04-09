package com.example.couponmanagement.service;

import com.example.couponmanagement.base.BaseMockitoTest;
import com.example.couponmanagement.entity.Coupon;
import com.example.couponmanagement.repository.CouponRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

class AddCouponServiceTest extends BaseMockitoTest {
    @InjectMocks
    private AddCouponService addCouponService;

    @Mock
    private CouponRepository couponRepository;
    public Coupon entity;

    @Before
    public void before() {
        entity.setName("yılbaşı");
        entity.setDiscountQuantity(20);
        entity.setAssignCount(100);
        entity.setActive(true);
    }

    @Test
    public void it_should_save_coupon() {
        //given
        Coupon coupon = new Coupon(1L, "yılbaşı", 20, 100, true,0);

        //when
        addCouponService.addCoupon(coupon);

        //then
        verify(couponRepository).save(coupon);
    }

}