package com.xiao5.twmall.product.feign;

import com.xiao5.twmall.product.to.es.SkuEsModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("twmall-search")
public interface EsFeign {

    @PostMapping("/search/save/addSku")
    public boolean addSku(@RequestBody List<SkuEsModel> listSkuEsModel);
}
