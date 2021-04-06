package com.example.couponmanagement.controller;

import com.example.couponmanagement.base.BaseIT;
import com.example.couponmanagement.entity.Coupon;
import com.example.couponmanagement.service.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
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

    @Test
    public void it_should_save_coupon() throws Exception {

        //given
        Coupon coupon = new Coupon(1L, "yılbaşı", 20, 100, true);

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

        //when

        //then
    }

    @Test
    public void it_should_delete_coupon_by_name() throws Exception {

        //given

        //when


        //then

    }

    @Test
    public void it_should_update_coupon() throws Exception {

        //given

        //when

        //then

    }

}