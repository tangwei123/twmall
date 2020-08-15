package com.xiao5.twmall.ware.service.impl;

import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiao5.twmall.ware.dao.WareSkuDao;
import com.xiao5.twmall.ware.entity.WareSkuEntity;
import com.xiao5.twmall.ware.service.WareSkuService;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Autowired
    public WareSkuDao wareSkuDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                new QueryWrapper<WareSkuEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<WareSkuEntity> getSkuHasStock(List<Long> skuIds) {
        return wareSkuDao.getSkuHasStock(skuIds);
    }

}