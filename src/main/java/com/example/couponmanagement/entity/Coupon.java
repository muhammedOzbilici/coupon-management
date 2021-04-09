package com.example.couponmanagement.entity;

import lombok.Data;

import javax.persistence.*;

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
    @Version
    private Integer version;

    public Coupon(Long id, String name, int discountQuantity, int assignCount, boolean isActive, Integer version) {
        this.id = id;
        this.name = name;
        this.discountQuantity = discountQuantity;
        this.assignCount = assignCount;
        this.isActive = isActive;
        this.version = version;
    }

    public Coupon() {
    }
}
