package com.xiao5.twmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.coupon.entity.CouponHistoryEntity;

import java.util.Map;

/**
 * ????????????¼
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 16:01:52
 */
public interface CouponHistoryService extends IService<CouponHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

