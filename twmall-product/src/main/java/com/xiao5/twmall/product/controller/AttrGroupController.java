package com.xiao5.twmall.product.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.xiao5.twmall.common.inter.UpdateGroup;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.common.utils.R;
import com.xiao5.twmall.product.entity.AttrEntity;
import com.xiao5.twmall.product.entity.CategoryEntity;
import com.xiao5.twmall.product.service.CategoryService;
import com.xiao5.twmall.product.vo.AttrGroupVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.xiao5.twmall.product.entity.AttrGroupEntity;
import com.xiao5.twmall.product.service.AttrGroupService;



/**
 * ???????
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;


    @Autowired
    private CategoryService categoryService;


    @PostMapping(value="/getAttrGroupByCateId/{cateId}")
    public R getAttrGroupByCateId(@RequestBody Map<String, Object> params, @PathVariable("cateId") Long cateId){
        return R.ok().put("page", attrGroupService.getAttrGroupByCateId(params, cateId));
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrGroupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    @RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        String catelogPath = categoryService.getCatelogPath(attrGroup.getCatelogId());
		attrGroup.setCatelogPath(catelogPath);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);
        return R.ok().put("data", "新增成功");
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:attrgroup:update")
    public R update(@Validated({UpdateGroup.class}) @RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);
        return R.ok().put("data", "编辑成功");
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

    /**
     * 获取一个属性组中的属性列表
     * @return
     */
    @GetMapping("/getOneGroupIncludeAttrs/{attrGroupId}")
    public R getOneGroupIncludeAttrs(@PathVariable("attrGroupId") Long attrGroupId){
        PageUtils listAttr = attrGroupService.getOneGroupIncludeAttrs(attrGroupId);
        return R.ok().put("data", listAttr);
    }

    /**
     * 获取一个属性组中的属性列表
     * @return
     */
    @GetMapping("/getOneGroupExcludeAttrs/{attrGroupId}")
    public R getOneGroupExcludeAttrs(@PathVariable("attrGroupId") Long attrGroupId){
        PageUtils listAttr = attrGroupService.getOneGroupExcludeAttrs(attrGroupId);
        return R.ok().put("data", listAttr);
    }

    @GetMapping("/{catelogId}/withattr")
    public R withattr(@PathVariable("catelogId") Long catelogId){
        List<AttrGroupVo> attrGroupVos = attrGroupService.withattr(catelogId);
        return R.ok().put("data", attrGroupVos);
    }
}
