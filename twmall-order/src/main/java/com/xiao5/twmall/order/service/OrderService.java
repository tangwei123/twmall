package com.xiao5.twmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.order.entity.OrderEntity;

import java.util.Map;

/**
 * ????
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 14:46:52
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

