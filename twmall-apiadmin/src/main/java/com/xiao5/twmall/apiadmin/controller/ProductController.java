package com.xiao5.twmall.apiadmin.controller;

import com.xiao5.twmall.apiadmin.feign.ProductFeign;
import com.xiao5.twmall.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    public ProductFeign productFeign;

    @GetMapping("/product/category/list")
    public R getAllProductCategoryList(){
        return productFeign.getProductList();
    }

    @GetMapping("/product/category/delete/{id}")
    public R deleteOneCategory(@PathVariable("id") Long id){
        Long[] ids = {id};
        return productFeign.deleteCategory(ids);
    }

    @PostMapping("/product/category/add")
    public R addOneCategory(@RequestBody Map mapData){
        return productFeign.addCategory(mapData);
    }

    @PostMapping("/product/category/update")
    public R updateOneCategory(@RequestBody Map mapData){
        return productFeign.updateCategory(mapData);
    }

    @GetMapping("/product/category/info/{id}")
    public R getCategoryDetail(@PathVariable("id") Long id){
        return productFeign.getCategoryDetail(id);
    }


    /**
     * 商品属性分类的接口
     */
    @PostMapping("/product/attrgroup/getAttrGroupByCateId/{cateId}")
    public R getAttrGroupByCateId(@RequestBody Map<String, Object> params, @PathVariable("cateId") Long cateId){
        return productFeign.getAttrGroupByCateId(params, cateId);
    }

    @PostMapping("/product/attrgroup/addattrgroup")
    public R addAttrGroup(@RequestBody Map<String, Object> params){
        return productFeign.addAttrGroup(params);
    }


    @PostMapping("/product/attrgroup/updateattrgroup")
    public R updateAttrGroup(@RequestBody Map<String, Object> params){
        return productFeign.updateAttrGroup(params);
    }


}
