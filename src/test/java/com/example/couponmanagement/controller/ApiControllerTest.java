package com.example.couponmanagement.controller;

import com.example.couponmanagement.base.BaseIT;
import com.example.couponmanagement.entity.Coupon;
import com.example.couponmanagement.repository.CouponRepository;
import com.example.couponmanagement.service.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigInteger;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiController.class)
class ApiControllerTest extends BaseIT {

    @MockBean
    private ListCouponService listCouponService;
    @MockBean
    private AddCouponService addCouponService;
    @MockBean
    private AssignCouponService assignCouponService;
    @MockBean
    private DeleteCouponService deleteCouponService;
    @MockBean
    private UpdateCouponService updateCouponService;

    @Captor
    private ArgumentCaptor<Coupon> couponCaptor;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    public void it_should_save_coupon() throws Exception {

        //given
        Coupon coupon = new Coupon(1L, "yılbaşı", 20, 100, true, 0);

        //when
        ResultActions expectedResult = mockMvc.perform(post("/save-coupon")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print());

        //then
        verify(addCouponService).addCoupon(couponCaptor.capture());
        Coupon expectedCoupon = couponCaptor.getValue();
        assertThat(expectedCoupon).isEqualToComparingFieldByField(coupon);
        expectedResult.andExpect(status().is3xxRedirection());

    }

    @Test
    public void it_should_list_all_coupons() throws Exception {

        //given
        Coupon coupon = new Coupon(1L, "yılbaşı", 20, 100, true, 0);
        Coupon coupon1 = new Coupon(2L, "yılbaşı2", 21, 100, true, 0);
        Coupon coupon2 = new Coupon(3L, "yılbaşı3", 22, 100, true, 0);
        couponRepository.save(coupon);
        couponRepository.save(coupon1);
        couponRepository.save(coupon2);

        //when
        ResultActions expectedResult = mockMvc.perform(post("/list-all-coupons")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print());

        //then
        assertThat(expectedResult).isNotNull();
        assertThat(expectedResult.getClass()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void it_should_delete_coupon_by_name() throws Exception {

        //given
        Coupon coupon = new Coupon(1L, "yılbaşı", 20, 100, true, 0);
        given(couponRepository.findById(1L)).willReturn(Optional.of(coupon));

        //when
        ResultActions expectedResult = mockMvc.perform(get("/delete-coupon/" + coupon.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print());

        //then
        verify(couponRepository).deleteById(1L);
        expectedResult.andExpect(status().is3xxRedirection());
    }

    @Test
    public void it_should_update_coupon() throws Exception {

        //given
        Coupon savedCoupon = new Coupon(1L, "yılbaşı", 20, 100, true, 0);
        couponRepository.save(savedCoupon);
        given(couponRepository.findById(savedCoupon.getId())).willReturn(Optional.of(savedCoupon));

        //when
        ResultActions expectedResult = mockMvc.perform(post("/update-coupon/1/ahmet")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print());

        //then
        verify(couponRepository).save(couponCaptor.capture());
        Coupon expectedCoupon = couponCaptor.getValue();
        assertThat(expectedCoupon.getAssignCount()).isEqualTo(savedCoupon.getAssignCount() - 1);
        expectedResult.andExpect(status().is3xxRedirection());

    }

}