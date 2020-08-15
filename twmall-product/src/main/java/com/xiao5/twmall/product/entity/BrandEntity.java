package com.xiao5.twmall.product.entity;

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
 * @date 2020-05-08 08:51:39
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ???id
	 */
	@TableId
	private Long brandId;
	/**
	 * ?????
	 */
	private String name;
	/**
	 * ???logo???
	 */
	private String logo;
	/**
	 * ????
	 */
	private String descript;
	/**
	 * ?????[0-???????1-???]
	 */
	private Integer showStatus;
	/**
	 * ?????????
	 */
	private String firstLetter;
	/**
	 * ????
	 */
	private Integer sort;

}
