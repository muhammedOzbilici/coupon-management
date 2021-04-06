package com.example.couponmanagement.repository;

import com.example.couponmanagement.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByName(String couponName);
    @Modifying
    @Query("update coupon u set u.assignCount = ?2 where u.name = ?1")
    void setCouponAssignCountByCouponName(String couponName, int assignCount);
}
