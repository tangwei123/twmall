package com.xiao5.twmall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ????????????¼
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 16:01:52
 */
@Data
@TableName("sms_coupon_history")
public class CouponHistoryEntity implements Serializable {
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
	 * ???id
	 */
	private Long memberId;
	/**
	 * ???????
	 */
	private String memberNickName;
	/**
	 * ??????[0->???????1->???????]
	 */
	private Integer getType;
	/**
	 * ???????
	 */
	private Date createTime;
	/**
	 * ?????[0->???ã?1->????ã?2->?????]
	 */
	private Integer useType;
	/**
	 * ??????
	 */
	private Date useTime;
	/**
	 * ????id
	 */
	private Long orderId;
	/**
	 * ??????
	 */
	private Long orderSn;

}
