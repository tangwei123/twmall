package com.xiao5.twmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.product.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * ?????????
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils getAllCateForBrand(Long brandId, Integer page);

    void addOneCateBrand(CategoryBrandRelationEntity categoryBrandRelationEntity);

    void updateBrand(Long brandId, String name);

    void updateCateInfo(Long catId, String name);

    PageUtils getBrandInfoAccordCatelogId(Long catelogId);
}

