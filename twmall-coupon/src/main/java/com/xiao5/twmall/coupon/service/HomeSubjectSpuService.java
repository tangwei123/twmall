package com.xiao5.twmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.coupon.entity.HomeSubjectSpuEntity;

import java.util.Map;

/**
 * ??????
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 16:01:51
 */
public interface HomeSubjectSpuService extends IService<HomeSubjectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

