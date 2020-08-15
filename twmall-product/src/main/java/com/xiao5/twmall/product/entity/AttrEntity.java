package com.xiao5.twmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ???????
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
@Data
@TableName("pms_attr")
public class AttrEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ????id
	 */
	@TableId
	private Long attrId;
	/**
	 * ??????
	 */
	private String attrName;
	/**
	 * ??????????[0-???????1-???]
	 */
	private Integer searchType;
	/**
	 * ???????
	 */
	private String icon;
	/**
	 * ???????[?ö?????]
	 */
	private String valueSelect;
	/**
	 * ????????[0-?????????1-?????????2-???????????????????????]
	 */
	private Integer attrType;
	/**
	 * ??????[0 - ???ã?1 - ????]
	 */
	private Long enable;
	/**
	 * ????????
	 */
	private Long catelogId;
	/**
	 * ?????????????????????0-?? 1-???????sku????????????
	 */
	private Integer showDesc;

}