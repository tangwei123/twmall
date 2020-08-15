package com.xiao5.twmall.product.vo.feignvo.coupon;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MemberPriceRemote {
    private Long id;
    private Long skuId;
    private Long memberLevelId;
    private String memberLevelName;
    private BigDecimal memberPrice;
    private Integer addOther;
}
