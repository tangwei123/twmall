package com.xiao5.twmall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ????????????
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 16:01:52
 */
@Data
@TableName("sms_seckill_sku_relation")
public class SeckillSkuRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * ??id
	 */
	private Long promotionId;
	/**
	 * ??????id
	 */
	private Long promotionSessionId;
	/**
	 * ???id
	 */
	private Long skuId;
	/**
	 * ??????
	 */
	private BigDecimal seckillPrice;
	/**
	 * ???????
	 */
	private BigDecimal seckillCount;
	/**
	 * ÿ?????????
	 */
	private BigDecimal seckillLimit;
	/**
	 * ????
	 */
	private Integer seckillSort;

}
