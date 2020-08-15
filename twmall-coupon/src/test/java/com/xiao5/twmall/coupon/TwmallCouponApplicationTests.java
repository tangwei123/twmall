package com.xiao5.twmall.coupon;

import com.xiao5.twmall.coupon.entity.CouponSpuRelationEntity;
import com.xiao5.twmall.coupon.service.CouponSpuRelationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TwmallCouponApplicationTests {


    @Autowired
    public CouponSpuRelationService couponSpuRelationService;

    @Test
    void contextLoads() {

        CouponSpuRelationEntity couponSpuRelationEntity = new CouponSpuRelationEntity();
        couponSpuRelationEntity.setCouponId(1L);

        couponSpuRelationService.save(couponSpuRelationEntity);
    }

}
