package com.example.couponmanagement.model;

import lombok.Data;

@Data
public class CouponRequestModel {
    private String name;
    private int discountQuantity;
    private int assignCount;
    private boolean isActive;

    public CouponRequestModel(String name, int discountQuantity, int assignCount, boolean isActive) {
        this.name = name;
        this.discountQuantity = discountQuantity;
        this.assignCount = assignCount;
        this.isActive = isActive;
    }
}
