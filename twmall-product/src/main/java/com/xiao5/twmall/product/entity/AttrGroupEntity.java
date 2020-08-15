package com.xiao5.twmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.xiao5.twmall.common.inter.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * ???????
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ????id
	 */
	@NotNull(message = "编辑分组必传", groups = {UpdateGroup.class})
	@TableId
	private Long attrGroupId;
	/**
	 * ????
	 */
	private String attrGroupName;
	/**
	 * ????
	 */
	private Integer sort;
	/**
	 * ????
	 */
	private String descript;
	/**
	 * ?????
	 */
	private String icon;
	/**
	 * ????????id
	 */
	private Long catelogId;

	@TableField(exist = false)
	private String catelogPath;

}
