package com.xiao5.twmall.product.dao;

import com.xiao5.twmall.product.entity.AttrGroupEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao5.twmall.product.vo.ItemVo;
import com.xiao5.twmall.product.vo.SaleAttrGroupVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * ???????
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
@Mapper
public interface AttrGroupDao extends BaseMapper<AttrGroupEntity> {

    List<SaleAttrGroupVo> getAttrBySpuId(@Param("catelogId") Long catelogId);
}
