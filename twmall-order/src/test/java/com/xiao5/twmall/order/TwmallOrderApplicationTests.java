package com.xiao5.twmall.order;

import com.xiao5.twmall.order.entity.OrderReturnReasonEntity;
import com.xiao5.twmall.order.service.OrderReturnReasonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TwmallOrderApplicationTests {

    @Autowired
    public OrderReturnReasonService orderReturnReasonService;
    @Test
    void contextLoads() {

        OrderReturnReasonEntity orderReturnReasonEntity = new OrderReturnReasonEntity();
        orderReturnReasonEntity.setName("xxxx");

        orderReturnReasonService.save(orderReturnReasonEntity);

    }

}
