package com.example.couponmanagement.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "coupon")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int discountQuantity;
    private int assignCount;
    private boolean isActive;

    public Coupon(Long id, String name, int discountQuantity, int assignCount, boolean isActive) {
        this.id = id;
        this.name = name;
        this.discountQuantity = discountQuantity;
        this.assignCount = assignCount;
        this.isActive = isActive;
    }

    public Coupon() {
    }
}
