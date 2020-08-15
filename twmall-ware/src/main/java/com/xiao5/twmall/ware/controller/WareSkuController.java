package com.xiao5.twmall.ware.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.common.utils.R;
import com.xiao5.twmall.ware.to.WareSkuStockTo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xiao5.twmall.ware.entity.WareSkuEntity;
import com.xiao5.twmall.ware.service.WareSkuService;



/**
 * ��Ʒ���
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 17:07:17
 */
@RestController
@RequestMapping("ware/waresku")
public class WareSkuController {
    @Autowired
    private WareSkuService wareSkuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ware:waresku:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wareSkuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ware:waresku:info")
    public R info(@PathVariable("id") Long id){
		WareSkuEntity wareSku = wareSkuService.getById(id);

        return R.ok().put("wareSku", wareSku);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ware:waresku:save")
    public R save(@RequestBody WareSkuEntity wareSku){
		wareSkuService.save(wareSku);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ware:waresku:update")
    public R update(@RequestBody WareSkuEntity wareSku){
		wareSkuService.updateById(wareSku);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ware:waresku:delete")
    public R delete(@RequestBody Long[] ids){
		wareSkuService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    @PostMapping("/getSkuHasStock")
    public R getSkuHasStock(@RequestBody List<Long> skuIds){
        List<WareSkuEntity> wareSkuEntityList = wareSkuService.getSkuHasStock(skuIds);
//        List<WareSkuStockTo> stockList = wareSkuEntityList.stream().map((everyWare)->{
//            WareSkuStockTo wareSkuStockTo = new WareSkuStockTo();
//            BeanUtils.copyProperties(everyWare, wareSkuStockTo);
//            return wareSkuStockTo;
//        }).collect(Collectors.toList());

        List<Map<String, Object>> stockList = wareSkuEntityList.stream().map(item->{
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("skuId", item.getSkuId());
            map.put("stock", item.getStock());
            return map;
        }).collect(Collectors.toList());

        return R.ok().setData(stockList);
    }


}
