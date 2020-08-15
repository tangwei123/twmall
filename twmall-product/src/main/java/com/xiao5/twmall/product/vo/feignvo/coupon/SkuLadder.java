package com.xiao5.twmall.product.vo.feignvo.coupon;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SkuLadder {
    private Long id;
    /**
     * spu_id
     */
    private Long skuId;
    /**
     * ??????
     */
    private Integer fullCount;
    /**
     * ????
     */
    private BigDecimal discount;
    /**
     * ????
     */
    private BigDecimal price;
    /**
     * ?????????????[0-????????1-?????]
     */
    private Integer addOther;
}
