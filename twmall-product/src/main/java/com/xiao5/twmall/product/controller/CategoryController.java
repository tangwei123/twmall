package com.xiao5.twmall.product.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.xiao5.twmall.common.group.AddGroup;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.xiao5.twmall.product.entity.CategoryEntity;
import com.xiao5.twmall.product.service.CategoryService;

import javax.validation.Valid;


/**
 * ???????????
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list/tree")
    @RequiresPermissions("product:category:list")
    public R list(@RequestParam Map<String, Object> params){
        List<CategoryEntity> cateList = categoryService.listWithTree();
        return R.ok().put("cateTree", cateList);
    }

    @PostMapping("/list/t")
    @RequiresPermissions("product:category:t")
    public R listt(@RequestParam Map<String, Object> params){
        PageUtils cateList = categoryService.queryPage(params);
        return R.ok().put("cateTree", cateList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    @RequiresPermissions("product:category:info")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:category:save")
    public R save(@Validated({AddGroup.class}) @RequestBody CategoryEntity category){
        categoryService.save(category);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:category:update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateDatail(category);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:category:delete")
    public R delete(@RequestBody Long[] catIds){
//		categoryService.removeByIds(Arrays.asList(catIds));

        categoryService.removeMenuByIds(Arrays.asList(catIds));

        return R.ok().put("result", "删除操作成功");
    }

}
