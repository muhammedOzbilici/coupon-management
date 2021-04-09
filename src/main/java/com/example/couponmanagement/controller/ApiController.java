package com.example.couponmanagement.controller;

import com.example.couponmanagement.entity.Coupon;
import com.example.couponmanagement.model.CouponRequestModel;
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
    public List<Coupon> listAllCoupons() {
        return listCouponService.listCoupon();
    }

    @PostMapping(value = "/save-coupon")
    public ResponseEntity<String> addCoupon(@RequestBody CouponRequestModel couponRequestModel) {
        try {
            boolean isSuccessfullyAdded = addCouponService.addCoupon(couponRequestModel);
            if (isSuccessfullyAdded) {
                return new ResponseEntity<>("Coupon successfully saved.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Couldn't save coupon, because already exist.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete-coupon/{id}")
    public ResponseEntity<String> deleteCoupon(@PathVariable Long id) {
        try {
            boolean isSuccessfullyDeleted = deleteCouponService.deleteCoupon(id);
            if (isSuccessfullyDeleted) {
                return new ResponseEntity<>("Coupon with id=" + id + " successfully deleted.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Couldn't find any coupon with this id.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/update-coupon/{id}")
    public ResponseEntity<String> updateCoupon(@RequestBody CouponRequestModel couponRequestModel,
                                               @PathVariable Long id) {
        try {
            boolean isSuccessfullyUpdated = updateCouponService.updateCoupon(id, couponRequestModel);
            if (isSuccessfullyUpdated) {
                return new ResponseEntity<>("Coupon with id=" + id + " successfully updated.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Couldn't find any coupon with this id.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/assign-coupon/{id}/{username}")
    public ResponseEntity<String> assignCoupon(@PathVariable Long id,
                                               @PathVariable String username) {
        try {
            boolean isSuccessfullyAssigned = assignCouponService.assignCoupon(id, username);
            if (isSuccessfullyAssigned) {
                return new ResponseEntity<>("Coupon successfully assigned to username=" + username + ".", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Couldn't update.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
