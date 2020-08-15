package com.xiao5.twmall.coupon.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpuBoundsVo {
    private Long id;
    private Long spuId;
    private BigDecimal growBounds;
    private BigDecimal buyBounds;
    private Integer work;

}
