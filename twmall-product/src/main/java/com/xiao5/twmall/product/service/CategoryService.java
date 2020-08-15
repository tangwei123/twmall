package com.xiao5.twmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.product.entity.CategoryEntity;
import com.xiao5.twmall.product.vo.Catelog2Vo;

import java.util.List;
import java.util.Map;

/**
 * ???????????
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void removeMenuByIds(List<Long> catIds);

    String getCatelogPath(Long cateId);

    void updateDatail(CategoryEntity category);

    List<CategoryEntity> getFirstLevelCate();

    Map<String, List<Catelog2Vo>> getCatelog();
}

