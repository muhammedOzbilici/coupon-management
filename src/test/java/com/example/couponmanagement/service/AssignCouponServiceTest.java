package com.example.couponmanagement.service;

import com.example.couponmanagement.base.BaseMockitoTest;
import com.example.couponmanagement.entity.Coupon;
import com.example.couponmanagement.repository.CouponRepository;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class AssignCouponServiceTest extends BaseMockitoTest {

    @InjectMocks
    private AssignCouponService assignCouponService;

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
    public void it_should_assign_coupon() {
        //given

        //when

        //then
    }
}