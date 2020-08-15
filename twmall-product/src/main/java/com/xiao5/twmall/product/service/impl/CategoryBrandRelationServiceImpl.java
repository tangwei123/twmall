package com.xiao5.twmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiao5.twmall.common.utils.Query;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.product.dao.BrandDao;
import com.xiao5.twmall.product.dao.CategoryDao;
import com.xiao5.twmall.product.entity.BrandEntity;
import com.xiao5.twmall.product.entity.CategoryEntity;
import com.xiao5.twmall.product.vo.CategoryBrandRelationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiao5.twmall.product.dao.CategoryBrandRelationDao;
import com.xiao5.twmall.product.entity.CategoryBrandRelationEntity;
import com.xiao5.twmall.product.service.CategoryBrandRelationService;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {
    @Autowired
    private CategoryDao categoryDao = null;
    @Autowired
    private BrandDao brandDao = null;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils getAllCateForBrand(Long brandId, Integer page) {

        IPage pageInfo = new Page<>(page, 1);
        QueryWrapper<CategoryBrandRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_id", brandId);

        IPage<CategoryBrandRelationEntity> pageData = this.page(pageInfo, queryWrapper);

        return new PageUtils(pageData);
    }

    @Override
    public void addOneCateBrand(CategoryBrandRelationEntity categoryBrandRelationEntity) {
        Long brandId = categoryBrandRelationEntity.getBrandId();
        Long catelogId = categoryBrandRelationEntity.getCatelogId();

        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);
        categoryBrandRelationEntity.setCatelogName(categoryEntity.getName());
        BrandEntity brandEntity = brandDao.selectById(brandId);
        categoryBrandRelationEntity.setBrandName(brandEntity.getName());

        this.save(categoryBrandRelationEntity);
    }

    @Override
    public void updateBrand(Long brandId, String name) {

        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setBrandName(name);

        UpdateWrapper<CategoryBrandRelationEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("brand_id", brandId);

        boolean res = this.update(categoryBrandRelationEntity, updateWrapper);
        if(!res){
            throw new RuntimeException("修改品牌关联信息失败");
        }
    }

    @Override
    public void updateCateInfo(Long catId, String name) {

        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setBrandName(name);

        UpdateWrapper<CategoryBrandRelationEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("catelog_id", catId);
        this.update(categoryBrandRelationEntity, updateWrapper);
    }

    @Override
    public PageUtils getBrandInfoAccordCatelogId(Long catelogId) {
        QueryWrapper<CategoryBrandRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("catelog_id", catelogId);
        IPage<CategoryBrandRelationEntity> pageInfo = this.page( new Page(1, 10), queryWrapper);

//        List<CategoryBrandRelationVo> categoryBrandRelationVos = pageInfo.getRecords().stream().map((everyBrandAndCateRelation)->{
//            CategoryBrandRelationVo categoryBrandRelationVo = new CategoryBrandRelationVo();
//            BeanUtils.copyProperties(everyBrandAndCateRelation, categoryBrandRelationVo);
//            return categoryBrandRelationVo;
//        }).collect(Collectors.toList());
//
//        pageInfo.setRecords(categoryBrandRelationVos);

        return new PageUtils(pageInfo);
    }

}