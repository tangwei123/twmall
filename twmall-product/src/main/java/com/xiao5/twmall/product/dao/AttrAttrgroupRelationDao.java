package com.xiao5.twmall.product.dao;

import com.xiao5.twmall.product.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ????&??????????
 * 
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
@Mapper
@Component
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {

    void deleteBranch(@Param("listData") List<AttrAttrgroupRelationEntity> listData);

    void addRelation();

    List<AttrAttrgroupRelationEntity> checkExists(@Param("attrgroupRelationEntities") List<AttrAttrgroupRelationEntity> attrgroupRelationEntities);
}
