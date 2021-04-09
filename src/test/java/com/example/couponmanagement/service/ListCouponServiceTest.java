package com.example.couponmanagement.service;

import com.example.couponmanagement.base.BaseMockitoTest;
import com.example.couponmanagement.entity.Coupon;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ListCouponServiceTest extends BaseMockitoTest {

    @InjectMocks
    private ListCouponService listCouponService;

    @Mock
    public List<Coupon> couponList;


    @Test
    public void it_should_list_all_coupons() {
        //given
        when(listCouponService.listCoupon()).thenReturn(couponList);

        //when
        List<Coupon> couponList = listCouponService.listCoupon();

        //then
        assertThat(couponList).isNotNull();
    }
}