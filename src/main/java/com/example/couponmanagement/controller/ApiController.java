package com.example.couponmanagement.controller;

import com.example.couponmanagement.entity.Coupon;
import com.example.couponmanagement.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class ApiController {
    private final ListCouponService listCouponService;
    private final AddCouponService addCouponService;
    private final DeleteCouponService deleteCouponService;
    private final UpdateCouponService updateCouponService;
    private final AssignCouponService assignCouponService;

    @GetMapping(value = "/list-all-coupons")
    public ResponseEntity<List<Coupon>> listAllCoupons() {
        return (ResponseEntity<List<Coupon>>) listCouponService.listCoupon();
    }

    @PostMapping(value = "/save-coupon")
    public ResponseEntity<String> addCoupon(Coupon coupon) {
        boolean isSuccessfullyAdded = addCouponService.addCoupon(coupon);
        if (isSuccessfullyAdded) {
            return new ResponseEntity<>("Coupon successfully saved", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Couldn't save coupon, because already exist", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete-coupon")
    public ResponseEntity<String> deleteCoupon(String couponName) {
        boolean isSuccessfullyDeleted = deleteCouponService.deleteCoupon(couponName);
        if (isSuccessfullyDeleted) {
            return new ResponseEntity<>("Coupon successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Couldn't find any coupon with this name", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/update-coupon")
    public ResponseEntity<String> updateCoupon(Coupon coupon) {
        boolean isSuccessfullyUpdated = updateCouponService.updateCoupon(coupon);
        if (isSuccessfullyUpdated) {
            return new ResponseEntity<>("Coupon successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Couldn't find any coupon with this name", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/assign-coupon")
    public ResponseEntity<String> assignCoupon(Coupon coupon, String userName) {
        boolean isSuccessfullyAssigned = assignCouponService.assignCoupon(coupon, userName);
        if (isSuccessfullyAssigned) {
            return new ResponseEntity<>("Coupon successfully assigned", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Couldn't find any coupon with this name", HttpStatus.BAD_REQUEST);
        }
    }
}
