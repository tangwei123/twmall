package com.xiao5.twmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.coupon.entity.HomeSubjectEntity;

import java.util.Map;

/**
 * ???????jd????????????ÿ??????????µ?????????????????
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 16:01:51
 */
public interface HomeSubjectService extends IService<HomeSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

