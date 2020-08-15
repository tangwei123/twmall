package com.xiao5.twmall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.common.utils.R;
import com.xiao5.twmall.product.vo.AttrRespVo;
import com.xiao5.twmall.product.vo.AttrRespVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xiao5.twmall.product.entity.AttrEntity;
import com.xiao5.twmall.product.service.AttrService;



/**
 * ???????
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
@RestController
@RequestMapping("/product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:attr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    @RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId") Long attrId){
		AttrEntity attr = attrService.getById(attrId);

        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:attr:save")
    public R save(@RequestBody AttrEntity attr){
		attrService.save(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:attr:update")
    public R update(@RequestBody AttrEntity attr){
		attrService.updateById(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));
        return R.ok();
    }

    @PostMapping("/addOneAttr")
    public R addOneAttr(@RequestBody AttrRespVo AttrRespVo){
        attrService.addOneAttr(AttrRespVo);
        return R.ok();
    }

    @PostMapping("/updateOneAttr")
    public R updateOneAttr(@RequestBody AttrRespVo AttrRespVo){
        attrService.updateOneAttr(AttrRespVo);
        return R.ok();
    }

    @GetMapping("/getOneAttrDetail/{attrId}")
    public R getOneAttrDetail(@PathVariable("attrId") Integer attrId){
        AttrRespVo attrRespVo = attrService.getOneAttrDetail(attrId);
        return R.ok().put("data", attrRespVo);
    }

}
