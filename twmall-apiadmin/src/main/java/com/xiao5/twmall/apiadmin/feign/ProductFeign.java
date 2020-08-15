package com.xiao5.twmall.apiadmin.feign;

import com.xiao5.twmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("twmall-gateway")
public interface ProductFeign {
    @GetMapping("/product/category/list/tree")
    public R getProductList();

    @PostMapping("/product/category/delete")
    public R deleteCategory(@RequestBody Long[] id);

    @PostMapping("/product/category/save")
    public R addCategory(@RequestBody Map data);

    @PostMapping("/product/category/update")
    public R updateCategory(@RequestBody Map data);

    @GetMapping("/product/category/info/{catId}")
    public R getCategoryDetail(@RequestParam("catId") Long catId);


    /**
     * 商品属性分类feign调用
     */
    @PostMapping("/product/attrgroup/getAttrGroupByCateId/{cateId}")
    public R getAttrGroupByCateId(@RequestBody Map<String, Object> params, @RequestParam("cateId") Long cateId);

    @PostMapping("/product/attrgroup/save")
    public R addAttrGroup(@RequestBody Map<String, Object> params);

    @PostMapping("/product/attrgroup/update")
    public R updateAttrGroup(@RequestBody Map<String, Object> params);
}
