package com.xiao5.twmall.product.service.impl;

import com.xiao5.twmall.common.utils.Query;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.product.config.MyThreadPoolConfig;
import com.xiao5.twmall.product.dao.SkuImagesDao;
import com.xiao5.twmall.product.entity.SkuImagesEntity;
import com.xiao5.twmall.product.entity.SpuInfoDescEntity;
import com.xiao5.twmall.product.service.*;
import com.xiao5.twmall.product.vo.ItemVo;
import com.xiao5.twmall.product.vo.SaleAttrGroupVo;
import com.xiao5.twmall.product.vo.SaleAttrValueVo;
import com.xiao5.twmall.product.vo.SkuItemSaleAttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiao5.twmall.product.dao.SkuInfoDao;
import com.xiao5.twmall.product.entity.SkuInfoEntity;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Autowired
    public SkuImagesService skuImagesService;

    @Autowired
    public SpuInfoDescService spuInfoDescService;

    @Autowired
    public AttrGroupService attrGroupService;

    @Autowired
    public SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    public ThreadPoolExecutor threadPool;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public void item(Long skuId) throws ExecutionException, InterruptedException {
        ItemVo itemVo = new ItemVo();
        CompletableFuture<SkuInfoEntity> future = CompletableFuture.supplyAsync(()->{
            //获取sku的基本信息,sku_info表
            SkuInfoEntity skuInfoEntity = this.getById(skuId);
            return skuInfoEntity;
        }, threadPool);
        CompletableFuture future02 = future.thenApplyAsync((res)->{
            //获取spu销售属性组合
            List<SkuItemSaleAttrVo> saleAttrList = skuSaleAttrValueService.getSaleAttrsBySpuId(res.getSpuId());
            return saleAttrList;
        }, threadPool);
        CompletableFuture future03 = future.thenApplyAsync((res)->{
            //获取spu的规格参数信息
            List<SaleAttrGroupVo> attrList = attrGroupService.getAttrBySpuId(res.getCatalogId());
            return attrList;
        }, threadPool);
        CompletableFuture future04 = future.thenApplyAsync((res)->{
            //获取spu介绍
            SpuInfoDescEntity spuInfoDescEntity = spuInfoDescService.getById(res.getSpuId());
            return spuInfoDescEntity;
        }, threadPool);

        CompletableFuture future05 = CompletableFuture.allOf(future02, future03, future04);
        future05.get();
        //获取sku的图片信息,sku_images表
        List<SkuImagesEntity> skuImages = skuImagesService.list(new QueryWrapper<SkuImagesEntity>().eq("sku_id", skuId));

    }

}