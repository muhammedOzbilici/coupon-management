package com.example.couponmanagement.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CouponDto {
    @NotNull
    private String name;
    @NotNull
    private int discountQuantity;
    @NotNull
    private int assignCount;
    @NotNull
    private boolean isActive;
}
