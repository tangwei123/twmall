package com.xiao5.twmall.product.feign;

import com.xiao5.twmall.common.utils.R;
import com.xiao5.twmall.product.to.WareSkuStockTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("twmall-ware")
public interface WareFeign {

    @PostMapping("/ware/waresku/getSkuHasStock")
//    public List<WareSkuStockTo> getWareSkuInfo(@RequestBody List<Long> skuId);
    public R getWareSkuInfo(@RequestBody List<Long> skuId);

}
