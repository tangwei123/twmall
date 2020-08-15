package com.xiao5.twmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.coupon.entity.SpuBoundsEntity;
import com.xiao5.twmall.coupon.vo.SpuBoundsVo;

import java.util.List;
import java.util.Map;

/**
 * ???spu????????
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 16:01:52
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addSpuBounds(SpuBoundsVo spuBoundsVos);
}

