package com.xiao5.twmall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ?????
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 16:01:51
 */
@Data
@TableName("sms_seckill_promotion")
public class SeckillPromotionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * ??????
	 */
	private String title;
	/**
	 * ???????
	 */
	private Date startTime;
	/**
	 * ????????
	 */
	private Date endTime;
	/**
	 * ????????
	 */
	private Integer status;
	/**
	 * ???????
	 */
	private Date createTime;
	/**
	 * ??????
	 */
	private Long userId;

}
