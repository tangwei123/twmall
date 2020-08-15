package com.xiao5.twmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * spu???
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
@Data
@TableName("pms_spu_info")
public class SpuInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ???id
	 */
	@TableId
	private Long id;
	/**
	 * ???????
	 */
	private String spuName;
	/**
	 * ???????
	 */
	private String spuDescription;
	/**
	 * ????????id
	 */
	private Long catalogId;
	/**
	 * ???id
	 */
	private Long brandId;
	/**
	 * 
	 */
	private BigDecimal weight;
	/**
	 * ?????[0 - ?¼??1 - ???]
	 */
	private Integer publishStatus;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;

}
