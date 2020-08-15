package com.xiao5.twmall.coupon.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SkuLadderVo {
    private Long id;
    private Long skuId;
    private Integer fullCount;
    private BigDecimal discount;
    private BigDecimal price;
    private Integer addOther;
}
