package com.xiao5.twmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.coupon.entity.SkuFullReductionEntity;
import com.xiao5.twmall.coupon.vo.SkuFullReductionVo;

import java.util.Map;

/**
 * ??????????
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 16:01:52
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addOneSkuFullReduction(SkuFullReductionVo skuFullReductionVo);
}

