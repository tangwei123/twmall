package com.xiao5.twmall.product.vo.feignvo.coupon;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SkuFullReduction {
    private Long id;
    private Long skuId;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer addOther;
}
