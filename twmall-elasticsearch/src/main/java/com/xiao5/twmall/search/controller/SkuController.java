package com.xiao5.twmall.search.controller;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.util.StringUtils;
import com.xiao5.twmall.common.utils.R;
import com.xiao5.twmall.search.config.twmallElasticSearchConfig;
import com.xiao5.twmall.search.to.SkuEsModel;
import joptsimple.internal.Strings;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("search")
public class SkuController {

    @Autowired
    public RestHighLevelClient restHighLevelClient;

    @PostMapping("/save/addSku")
    public boolean addSku(@RequestBody List<SkuEsModel> skuEsModelList) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel everySkuEsModel: skuEsModelList){
            IndexRequest indexRequest = new IndexRequest("twmall_product");
            indexRequest.id(everySkuEsModel.getSkuId().toString());
            indexRequest.source(JSON.toJSONString(everySkuEsModel), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, twmallElasticSearchConfig.COMMON_OPTIONS);
        boolean result = bulkResponse.hasFailures();
        log.error(String.valueOf(result));
        //如果加入到es中出现错误，这儿可以获取到错误，然后重新再操作一次
        if(result){
            log.error("商品加入es错误,尝试再来一次");
            this.addSku(skuEsModelList);
        }else{
            log.info("加入成功");
        }
        return result;
    }
}
