package com.xiao5.twmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.xiao5.twmall.common.group.AddGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * ???????????
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
@Data
@TableName("pms_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * ????id
	 */
	@TableId
	private Long catId;
	/**
	 * ????????
	 */
	@NotBlank(message = "分类名称必须提交", groups = {AddGroup.class})
	private String name;
	/**
	 * ??????id
	 */
	private Long parentCid;
	/**
	 * ??
	 */
	private Integer catLevel;
	/**
	 * ??????[0-???????1???]
	 */
	@TableLogic(value = "1", delval = "0")
	private Integer showStatus;
	/**
	 * ????
	 */
	private Integer sort;
	/**
	 * ?????
	 */
	private String icon;
	/**
	 * ???????
	 */
	private String productUnit;
	/**
	 * ???????
	 */
	private Integer productCount;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@TableField(exist = false)
	private List<CategoryEntity> childCate;

}
