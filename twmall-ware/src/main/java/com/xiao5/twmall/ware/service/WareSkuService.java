package com.xiao5.twmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.ware.entity.WareSkuEntity;

import java.util.List;
import java.util.Map;

/**
 * ��Ʒ���
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 17:07:17
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<WareSkuEntity> getSkuHasStock(List<Long> skuIds);
}

