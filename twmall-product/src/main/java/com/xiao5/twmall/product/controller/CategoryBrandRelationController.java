package com.xiao5.twmall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.common.utils.R;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xiao5.twmall.product.entity.CategoryBrandRelationEntity;
import com.xiao5.twmall.product.service.CategoryBrandRelationService;


/**
 * ?????????
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:categorybrandrelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryBrandRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:categorybrandrelation:info")
    public R info(@PathVariable("id") Long id){
		CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:categorybrandrelation:save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
		categoryBrandRelationService.save(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:categorybrandrelation:update")
    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
		categoryBrandRelationService.updateById(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:categorybrandrelation:delete")
    public R delete(@RequestBody Long[] ids){
		categoryBrandRelationService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }


    @GetMapping("/getAllCateForBrand/{brandId}")
    public R getAllCateForBrand(@PathVariable("brandId") Long brandId, @Param("page") Integer page){
        return R.ok().put("data", categoryBrandRelationService.getAllCateForBrand(brandId, page));
    }

    @PostMapping("/addOneCateBrand")
    public R addOneCateBrand(@RequestBody CategoryBrandRelationEntity categoryBrandRelationEntity){
        categoryBrandRelationService.addOneCateBrand(categoryBrandRelationEntity);
        return R.ok().put("data", "新增成功");
    }


    @GetMapping("/getBrandInfoAccordCatelogId/{catelogId}")
    public R getBrandInfoAccordCatelogId(@PathVariable("catelogId") Long catelogId){
        PageUtils brandInfo = categoryBrandRelationService.getBrandInfoAccordCatelogId(catelogId);
        return R.ok().put("brandInfo", brandInfo);
    }

}
