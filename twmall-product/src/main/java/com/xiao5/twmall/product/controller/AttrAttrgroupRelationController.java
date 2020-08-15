package com.xiao5.twmall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.common.utils.R;
import com.xiao5.twmall.product.vo.AttrGroupRelationVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.xiao5.twmall.product.entity.AttrAttrgroupRelationEntity;
import com.xiao5.twmall.product.service.AttrAttrgroupRelationService;




/**
 * ????&??????????
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
@RestController
@RequestMapping("product/attrattrgrouprelation")
public class AttrAttrgroupRelationController {
    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:attrattrgrouprelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrAttrgroupRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:attrattrgrouprelation:info")
    public R info(@PathVariable("id") Long id){
		AttrAttrgroupRelationEntity attrAttrgroupRelation = attrAttrgroupRelationService.getById(id);

        return R.ok().put("attrAttrgroupRelation", attrAttrgroupRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:attrattrgrouprelation:save")
    public R save(@RequestBody AttrAttrgroupRelationEntity attrAttrgroupRelation){
		attrAttrgroupRelationService.save(attrAttrgroupRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:attrattrgrouprelation:update")
    public R update(@RequestBody AttrAttrgroupRelationEntity attrAttrgroupRelation){
		attrAttrgroupRelationService.updateById(attrAttrgroupRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:attrattrgrouprelation:delete")
    public R delete(@RequestBody Long[] ids){
		attrAttrgroupRelationService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/deleteRelations")
    @RequiresPermissions("product:attrattrgrouprelation:delete")
    public R delete(@RequestBody AttrGroupRelationVo[] attrGroupRelationVos){
        attrAttrgroupRelationService.deleteRelations(attrGroupRelationVos);
        return R.ok();
    }

    @PostMapping("/addRelations")
    public R addRelations(@RequestBody AttrGroupRelationVo[] attrGroupRelationVo){
        try{
            attrAttrgroupRelationService.addRelations(attrGroupRelationVo);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return R.ok();
    }

}
