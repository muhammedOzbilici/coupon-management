package com.example.couponmanagement.repository;

import com.example.couponmanagement.entity.Coupon;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
class CouponRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    CouponRepository couponRepository;

    @Test
    void it_should_find_by_name() {
        //arrange
        Coupon savedCoupon = new Coupon(1L, "yılbaşı", 20, 100, true);
        jdbcTemplate.update(
                "INSERT INTO coupon (id, name, discountQuantity, assignCount, isActive) VALUES (?, ?, ?, ?, ?)",
                savedCoupon.getId(), savedCoupon.getName(), savedCoupon.getDiscountQuantity(), savedCoupon.getAssignCount(),
                savedCoupon.isActive()
        );

        //act
        Optional<Coupon> expectedCoupon = couponRepository.findById(1L);

        //assert
        Coupon coupon = expectedCoupon.get();
        assertThat(coupon).isEqualToComparingFieldByField(coupon);
    }

    @Test
    void it_should_assign_coupon_by_coupon_name() {
        //arrange

        //act

        //assert
    }
}