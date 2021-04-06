package com.example.couponmanagement.validator;

import com.example.couponmanagement.dto.CouponDto;
import com.example.couponmanagement.exception.MissingMandatoryFieldException;
import org.springframework.stereotype.Component;

@Component
public class CouponValidator {
    public void validate(CouponDto couponDto) {
        if (couponDto == null || couponDto.getName() == null) {
            throw new MissingMandatoryFieldException("missing.field");
        }
    }
}
