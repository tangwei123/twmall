package com.xiao5.twmall.product.dao;

import com.xiao5.twmall.product.entity.SkuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * sku???
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
@Mapper
@Component
public interface SkuInfoDao extends BaseMapper<SkuInfoEntity> {
    public Integer updateSku(Integer goodsId);
}