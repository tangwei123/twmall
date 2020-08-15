package com.xiao5.twmall.product.dao;

import com.xiao5.twmall.product.entity.ProductAttrValueEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * spu?????
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
@Mapper
@Component
public interface ProductAttrValueDao extends BaseMapper<ProductAttrValueEntity> {
	
}
