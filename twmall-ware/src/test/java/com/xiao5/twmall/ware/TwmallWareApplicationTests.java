package com.xiao5.twmall.ware;

import com.xiao5.twmall.ware.entity.WareInfoEntity;
import com.xiao5.twmall.ware.service.WareInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TwmallWareApplicationTests {

    @Autowired
    public WareInfoService wareInfoService;

    @Test
    void contextLoads() {
        WareInfoEntity wareInfoEntity = new WareInfoEntity();
        wareInfoEntity.setName("xxxxx");

        wareInfoService.save(wareInfoEntity);
    }

}
