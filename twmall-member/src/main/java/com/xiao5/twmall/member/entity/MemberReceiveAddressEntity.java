package com.xiao5.twmall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ?????????
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 16:23:38
 */
@Data
@TableName("ums_member_receive_address")
public class MemberReceiveAddressEntity implements Serializable {
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
	 * ?????????
	 */
	private String name;
	/**
	 * ??
	 */
	private String phone;
	/**
	 * ????????
	 */
	private String postCode;
	/**
	 * ???/????
	 */
	private String province;
	/**
	 * ????
	 */
	private String city;
	/**
	 * ??
	 */
	private String region;
	/**
	 * ??????(???)
	 */
	private String detailAddress;
	/**
	 * ?????????
	 */
	private String areacode;
	/**
	 * ??????
	 */
	private Integer defaultStatus;

}
