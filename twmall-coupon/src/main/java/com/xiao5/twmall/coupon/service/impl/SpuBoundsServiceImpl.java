package com.xiao5.twmall.coupon.service.impl;

import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.common.utils.Query;
import com.xiao5.twmall.coupon.vo.SpuBoundsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiao5.twmall.coupon.dao.SpuBoundsDao;
import com.xiao5.twmall.coupon.entity.SpuBoundsEntity;
import com.xiao5.twmall.coupon.service.SpuBoundsService;


@Service("spuBoundsService")
public class SpuBoundsServiceImpl extends ServiceImpl<SpuBoundsDao, SpuBoundsEntity> implements SpuBoundsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuBoundsEntity> page = this.page(
                new Query<SpuBoundsEntity>().getPage(params),
                new QueryWrapper<SpuBoundsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void addSpuBounds(SpuBoundsVo spuBoundsVos) {
        SpuBoundsEntity spuBoundsEntity = new SpuBoundsEntity();
        BeanUtils.copyProperties(spuBoundsVos, spuBoundsEntity);
        this.save(spuBoundsEntity);
    }

}