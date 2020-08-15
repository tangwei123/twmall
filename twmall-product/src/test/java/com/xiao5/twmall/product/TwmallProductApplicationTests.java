package com.xiao5.twmall.product;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.xiao5.twmall.product.entity.BrandEntity;
import com.xiao5.twmall.product.service.BrandService;
import com.xiao5.twmall.product.vo.CategoryBrandRelationVo;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class TwmallProductApplicationTests {


    @Autowired
    public BrandService brandService;

    @Autowired
    public RedisTemplate redisTemplate;

    @Autowired
    public AmqpAdmin amqpAdmin;

    @Autowired
    public RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
//        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setBrandId(2L);
//        brandEntity.setDescript("yyyyyy");
//        brandService.updateById(brandEntity);

//        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", "2L"));
//        for (BrandEntity everyBrand:list){
//            System.out.println(everyBrand);
//        }
//        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//        valueOperations.set("hello", "world");
//
//        System.out.println(valueOperations.get("hello"));
        amqpAdmin.declareExchange(new DirectExchange("twmall.direct.exchange", true, false));
        amqpAdmin.declareQueue(new Queue("twmall2", true, false, false));
        amqpAdmin.declareBinding(new Binding("twmall2", Binding.DestinationType.QUEUE, "twmall.direct.exchange", "twmall", new LinkedHashMap<>()));
    }

    @Test
    public void sendMessage(){
        CategoryBrandRelationVo t = new CategoryBrandRelationVo();
        t.setBrandId(1L);
        t.setBrandName("ceshi");
        rabbitTemplate.convertAndSend("twmall.direct.exchange", "twmall", JSON.toJSONString(t));
    }
}
