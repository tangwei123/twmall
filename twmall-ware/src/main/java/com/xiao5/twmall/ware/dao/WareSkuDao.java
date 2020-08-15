package com.xiao5.twmall.ware.dao;

import com.xiao5.twmall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ��Ʒ���
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 17:07:17
 */
@Mapper
@Component
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {

    public List<WareSkuEntity> getSkuHasStock(@Param("skuIds") List<Long>  skuIds);
}
