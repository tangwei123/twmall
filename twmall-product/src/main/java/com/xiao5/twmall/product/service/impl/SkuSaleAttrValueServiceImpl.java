package com.xiao5.twmall.product.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao5.twmall.common.utils.Query;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.product.vo.SkuItemSaleAttrVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiao5.twmall.product.dao.SkuSaleAttrValueDao;
import com.xiao5.twmall.product.entity.SkuSaleAttrValueEntity;
import com.xiao5.twmall.product.service.SkuSaleAttrValueService;


@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements SkuSaleAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuSaleAttrValueEntity> page = this.page(
                new Query<SkuSaleAttrValueEntity>().getPage(params),
                new QueryWrapper<SkuSaleAttrValueEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuItemSaleAttrVo> getSaleAttrsBySpuId(Long spuId) {
        return baseMapper.saleAttrsBySpuId(spuId);
    }

}