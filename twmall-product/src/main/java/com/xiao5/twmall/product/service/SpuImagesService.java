package com.xiao5.twmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.product.entity.SpuImagesEntity;

import java.util.Map;

/**
 * spu??
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

