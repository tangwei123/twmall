package com.xiao5.twmall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ???
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 16:23:38
 */
@Data
@TableName("ums_member")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * ??????id
	 */
	private Long levelId;
	/**
	 * ?û???
	 */
	private String username;
	/**
	 * ????
	 */
	private String password;
	/**
	 * ???
	 */
	private String nickname;
	/**
	 * ???????
	 */
	private String mobile;
	/**
	 * ????
	 */
	private String email;
	/**
	 * ???
	 */
	private String header;
	/**
	 * ???
	 */
	private Integer gender;
	/**
	 * ????
	 */
	private Date birth;
	/**
	 * ???????
	 */
	private String city;
	/**
	 * ??
	 */
	private String job;
	/**
	 * ???????
	 */
	private String sign;
	/**
	 * ?û????
	 */
	private Integer sourceType;
	/**
	 * ????
	 */
	private Integer integration;
	/**
	 * ????
	 */
	private Integer growth;
	/**
	 * ??????
	 */
	private Integer status;
	/**
	 * ??????
	 */
	private Date createTime;

}
