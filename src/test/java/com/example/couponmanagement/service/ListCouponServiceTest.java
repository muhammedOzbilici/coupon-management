package com.example.couponmanagement.service;

import com.example.couponmanagement.base.BaseMockitoTest;
import com.example.couponmanagement.entity.Coupon;
import com.example.couponmanagement.repository.CouponRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class ListCouponServiceTest extends BaseMockitoTest {

    @InjectMocks
    private ListCouponService listCouponService;

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
    public void it_should_list_all_coupons() {
        //given

        //when

        //then
    }
}