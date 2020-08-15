package com.xiao5.twmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.product.entity.SkuInfoEntity;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * sku???
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void item(Long skuId) throws ExecutionException, InterruptedException;
}

