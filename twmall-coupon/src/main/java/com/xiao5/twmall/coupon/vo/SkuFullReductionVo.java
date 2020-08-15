package com.xiao5.twmall.coupon.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SkuFullReductionVo {
    private Long id;
    private Long skuId;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer addOther;
}
