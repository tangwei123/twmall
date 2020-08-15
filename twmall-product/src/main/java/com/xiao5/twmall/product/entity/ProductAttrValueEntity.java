package com.xiao5.twmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * spu?????
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
@Data
@TableName("pms_product_attr_value")
public class ProductAttrValueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * ???id
	 */
	private Long spuId;
	/**
	 * ????id
	 */
	private Long attrId;
	/**
	 * ??????
	 */
	private String attrName;
	/**
	 * ?????
	 */
	private String attrValue;
	/**
	 * ???
	 */
	private Integer attrSort;
	/**
	 * ?????????????????????0-?? 1-???
	 */
	private Integer quickShow;

}
