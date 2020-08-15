package com.xiao5.twmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.product.entity.AttrGroupEntity;
import com.xiao5.twmall.product.vo.AttrGroupVo;
import com.xiao5.twmall.product.vo.ItemVo;
import com.xiao5.twmall.product.vo.SaleAttrGroupVo;
import com.xiao5.twmall.product.vo.SaleAttrValueVo;

import java.util.List;
import java.util.Map;

/**
 * ???????
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils getAttrGroupByCateId(Map<String, Object> params, Long cateId);

    PageUtils getOneGroupIncludeAttrs(Long attrGroupId);

    PageUtils getOneGroupExcludeAttrs(Long attrGroupId);

    List<AttrGroupVo> withattr(Long catelogId);

    List<SaleAttrGroupVo> getAttrBySpuId(Long catelogId);
}

