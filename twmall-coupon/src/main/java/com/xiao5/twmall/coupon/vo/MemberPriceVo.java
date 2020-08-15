package com.xiao5.twmall.coupon.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MemberPriceVo {
    private Long id;
    private Long skuId;
    private Long memberLevelId;
    private String memberLevelName;
    private BigDecimal memberPrice;
    private Integer addOther;
}
