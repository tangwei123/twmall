package com.xiao5.twmall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ????????
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 14:46:52
 */
@Data
@TableName("oms_payment_info")
public class PaymentInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * ????????????????
	 */
	private String orderSn;
	/**
	 * ????id
	 */
	private Long orderId;
	/**
	 * ??????????????
	 */
	private String alipayTradeNo;
	/**
	 * ???????
	 */
	private BigDecimal totalAmount;
	/**
	 * ????????
	 */
	private String subject;
	/**
	 * ?????
	 */
	private String paymentStatus;
	/**
	 * ???????
	 */
	private Date createTime;
	/**
	 * ??????
	 */
	private Date confirmTime;
	/**
	 * ???????
	 */
	private String callbackContent;
	/**
	 * ??????
	 */
	private Date callbackTime;

}
