package com.xiao5.twmall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("sms_seckill_sku_notice")
public class SeckillSkuNoticeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * member_id
	 */
	private Long memberId;
	/**
	 * sku_id
	 */
	private Long skuId;
	/**
	 * ??????id
	 */
	private Long sessionId;
	/**
	 * ???????
	 */
	private Date subcribeTime;
	/**
	 * ???????
	 */
	private Date sendTime;
	/**
	 * ?????[0-?????1-???]
	 */
	private Integer noticeType;

}
