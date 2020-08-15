package com.xiao5.twmall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.mysql.cj.util.StringUtils;
import com.xiao5.twmall.common.group.AddGroup;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.common.utils.R;
import com.xiao5.twmall.product.vo.spusavevo.SpuSaveVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.xiao5.twmall.product.entity.SpuInfoEntity;
import com.xiao5.twmall.product.service.SpuInfoService;



/**
 * spu???
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @RequiresPermissions("product:spuinfo:list")
    public R list(@RequestBody Map<String, Object> params){
        PageUtils page = spuInfoService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:spuinfo:info")
    public R info(@PathVariable("id") Long id){
		SpuInfoEntity spuInfo = spuInfoService.getById(id);

        return R.ok().put("spuInfo", spuInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:spuinfo:save")
    public R save(@Validated({AddGroup.class}) @RequestBody SpuSaveVo spuSaveVo){
        spuInfoService.addOneSpu(spuSaveVo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:spuinfo:update")
    public R update(@RequestBody SpuInfoEntity spuInfo){
		spuInfoService.updateById(spuInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:spuinfo:delete")
    public R delete(@RequestBody Long[] ids){
		spuInfoService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    @GetMapping("/{spuId}/up")
    public R upOneProduct(@PathVariable("spuId") Long spuId){
        spuInfoService.upOneProduct(spuId);
        return R.ok();
    }

}
