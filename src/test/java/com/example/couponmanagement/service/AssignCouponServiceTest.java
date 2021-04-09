package com.example.couponmanagement.service;

import com.example.couponmanagement.base.BaseMockitoTest;
import com.example.couponmanagement.entity.Coupon;
import com.example.couponmanagement.repository.CouponRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class AssignCouponServiceTest extends BaseMockitoTest {

    @InjectMocks
    private AssignCouponService assignCouponService;

    @Mock
    private CouponRepository couponRepository;

    private final List<Integer> couponAmounts = Arrays.asList(100, 1);

    @Test
    public void it_should_assign_coupon_withoutConcurrency() {
        // given
        final Coupon coupon = couponRepository.save(new Coupon(1L, "yılbaşı", 10, 50, true, 0));
        assertEquals(java.util.Optional.of(0), coupon.getVersion());

        // when
        assignCouponService.assignCoupon(1L, "ahmet");

        // then
        final Coupon newCoupon = couponRepository.findById(coupon.getId()).get();

        assertAll(
                () -> assertEquals(java.util.Optional.of(2), newCoupon.getVersion()),
                () -> assertEquals(0, newCoupon.getAssignCount())
        );
    }

    @Test
    public void it_should_assign_coupon_withOptimisticLockingHandling() throws InterruptedException {
        // given
        final Coupon coupon = couponRepository.save(new Coupon(1L, "yılbaşı", 10, 50, true, 0));
        assertEquals(java.util.Optional.of(0), coupon.getVersion());

        // when
        final ExecutorService executor = Executors.newFixedThreadPool(couponAmounts.size());

        for (final int amount : couponAmounts) {
            executor.execute(() -> assignCouponService.assignCoupon(1L, "ahmet"));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // then
        final Coupon newCoupon = couponRepository.findById(coupon.getId()).get();

        assertAll(
                () -> assertEquals(java.util.Optional.of(1), newCoupon.getVersion()),
                () -> assertEquals(0, newCoupon.getAssignCount()),
                () -> verify(assignCouponService, times(3)).assignCoupon(anyInt(), anyString())
        );
    }


}