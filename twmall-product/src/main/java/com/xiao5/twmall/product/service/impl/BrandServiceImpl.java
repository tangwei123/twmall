package com.xiao5.twmall.product.service.impl;

import com.mysql.cj.util.StringUtils;
import com.xiao5.twmall.common.utils.Query;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.product.dao.CategoryBrandRelationDao;
import com.xiao5.twmall.product.entity.CategoryBrandRelationEntity;
import com.xiao5.twmall.product.service.CategoryBrandRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiao5.twmall.product.dao.BrandDao;
import com.xiao5.twmall.product.entity.BrandEntity;
import com.xiao5.twmall.product.service.BrandService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Service("brandService")
@Slf4j
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                new QueryWrapper<BrandEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDataById(BrandEntity brand) {
        this.updateById(brand);
        if(!StringUtils.isEmptyOrWhitespaceOnly(brand.getName())){
            try{
                categoryBrandRelationService.updateBrand(brand.getBrandId(), brand.getName());
            }catch(Exception exc){
                log.error(exc.getMessage());
                throw new RuntimeException("修改品牌关联信息出错");
            }
        }
    }
}