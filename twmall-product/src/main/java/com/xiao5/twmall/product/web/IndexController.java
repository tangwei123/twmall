package com.xiao5.twmall.product.web;

import com.alibaba.fastjson.JSON;
import com.xiao5.twmall.product.dao.SkuInfoDao;
import com.xiao5.twmall.product.entity.AttrAttrgroupRelationEntity;
import com.xiao5.twmall.product.entity.CategoryEntity;
import com.xiao5.twmall.product.service.CategoryService;
import com.xiao5.twmall.product.service.SkuInfoService;
import com.xiao5.twmall.product.vo.CategoryBrandRelationVo;
import com.xiao5.twmall.product.vo.Catelog2Vo;
import com.xiao5.twmall.product.vo.ItemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class IndexController {

    @Autowired
    public CategoryService categoryService;

    @Autowired
    public SkuInfoDao skuInfoDao;

    @Autowired
    public SkuInfoService skuInfoService;

    @Autowired
    public StringRedisTemplate redisTemplate;

    @Autowired
    public RabbitTemplate rabbitTemplate;

    @GetMapping({"/", "/index.html"})
    public ModelAndView index(HttpSession session){

        System.out.println(session.getAttribute("loginUser"));
        ModelAndView mav = new ModelAndView("index.html");
        List<CategoryEntity> cateEntityList = categoryService.getFirstLevelCate();
        List<CategoryEntity> cateEntityList2 = cateEntityList.stream().map(item->{
            item.setName(item.getName().replace("、", "/"));
            return item;
        }).collect(Collectors.toList());
        mav.addObject("cateEntityList", cateEntityList2);
        return mav;
    }

    @GetMapping("/index/catalog")
    public Map<String, List<Catelog2Vo>> getCatelog(){
        return categoryService.getCatelog();
    }

    @GetMapping("/snap/{goodsId}")
    public String snap(@PathVariable("goodsId") Integer goodsId){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String skuNum = valueOperations.get("goodsSku_"+String.valueOf(goodsId));
        if(skuNum == null){
            return "没有该商品的抢购活动";
        }
        Long leftNum = valueOperations.decrement("goodsSku_"+String.valueOf(goodsId));//减少redis中的数据
        if(leftNum < 0L){//
            //查询订单，如果有对应个数的订单就提示结束，否则提示抢购中
            return "抢购中.....";
        }else{
            try{
                //减少对应的表中的库存，并不是100%的成功
                Integer resultNum = skuInfoDao.updateSku(goodsId);
            }catch(Throwable error){//修改数据表失败，把redis的库存加回去
                valueOperations.increment("goodsSku_"+String.valueOf(goodsId));
            }
        }
        return null;
    }

    @GetMapping("/{skuId}.html")
    public ModelAndView item(@PathVariable("skuId") Long skuId) throws ExecutionException, InterruptedException {
        ModelAndView mav = new ModelAndView("item.html");
        skuInfoService.item(skuId);
        return mav;
    }

    @GetMapping("/sendMessage")
    public void sendMesage(){
        for ( int num = 1; num <= 10; num ++){

            if(num <= 5){
                CategoryBrandRelationVo t = new CategoryBrandRelationVo();
                t.setBrandId(1L);
                t.setBrandName("ceshi");
                rabbitTemplate.convertAndSend("twmall.direct.exchange", "twmall", t);
            }else{
                AttrAttrgroupRelationEntity m = new AttrAttrgroupRelationEntity();
                m.setAttrGroupId(1L);
                m.setAttrId(2L);
                m.setAttrSort(10);
                m.setId(2L);
                m.setIsDeleted(0);
                rabbitTemplate.convertAndSend("twmall.direct.exchange", "twmall", m);
            }


        }

    }


}
