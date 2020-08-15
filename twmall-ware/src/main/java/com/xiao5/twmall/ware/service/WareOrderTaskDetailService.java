package com.xiao5.twmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.ware.entity.WareOrderTaskDetailEntity;

import java.util.Map;

/**
 * ��湤����
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 17:07:17
 */
public interface WareOrderTaskDetailService extends IService<WareOrderTaskDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

