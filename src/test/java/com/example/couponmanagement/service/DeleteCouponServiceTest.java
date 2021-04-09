package com.example.couponmanagement.service;

import com.example.couponmanagement.base.BaseMockitoTest;
import com.example.couponmanagement.entity.Coupon;
import com.example.couponmanagement.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteCouponServiceTest extends BaseMockitoTest {

    @InjectMocks
    private DeleteCouponService deleteCouponService;

    @Mock
    private CouponRepository couponRepository;

    @Test
    public void it_should_delete_coupon() {
        //given
        Coupon coupon = new Coupon(1L, "yılbaşı", 20, 100, true, 0);
        couponRepository.save(coupon);

        //when
        deleteCouponService.deleteCoupon(1L);

        //then
        assertThat(couponRepository.findById(coupon.getId())).isEmpty();
    }
}