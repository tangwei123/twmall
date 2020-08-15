package com.xiao5.twmall.coupon.service.impl;

import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.common.utils.Query;
import com.xiao5.twmall.coupon.vo.SkuLadderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiao5.twmall.coupon.dao.SkuLadderDao;
import com.xiao5.twmall.coupon.entity.SkuLadderEntity;
import com.xiao5.twmall.coupon.service.SkuLadderService;


@Service("skuLadderService")
public class SkuLadderServiceImpl extends ServiceImpl<SkuLadderDao, SkuLadderEntity> implements SkuLadderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuLadderEntity> page = this.page(
                new Query<SkuLadderEntity>().getPage(params),
                new QueryWrapper<SkuLadderEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void addOneSkuLadder(SkuLadderVo skuLadderVo) {
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        BeanUtils.copyProperties(skuLadderVo, skuLadderEntity);
        this.save(skuLadderEntity);
    }

}