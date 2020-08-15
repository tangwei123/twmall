package com.xiao5.twmall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ???????????
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 16:01:52
 */
@Data
@TableName("sms_coupon_spu_category_relation")
public class CouponSpuCategoryRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * ????id
	 */
	private Long couponId;
	/**
	 * ???????id
	 */
	private Long categoryId;
	/**
	 * ???????????
	 */
	private String categoryName;

}
