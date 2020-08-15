package com.xiao5.twmall.member.feign;

import com.xiao5.twmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "twmall-coupon")
@Component
public interface CouponFeignService {

    @GetMapping(value = "/coupon/coupon/member/coupon")
    public R memberCoupons();
}
