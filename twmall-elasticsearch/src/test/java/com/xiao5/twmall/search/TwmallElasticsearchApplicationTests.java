package com.xiao5.twmall.search;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xiao5.twmall.search.config.twmallElasticSearchConfig;
import com.xiao5.twmall.search.to.SkuEsModel;
import com.xiao5.twmall.search.vo.UserVo;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class TwmallElasticsearchApplicationTests {

    @Autowired
    public RestHighLevelClient restHighLevelClient;

    @Test
    void contextLoads() throws IOException {

        IndexRequest request = new IndexRequest("users");
        request.id("1");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(jsonString, XContentType.JSON);
        IndexResponse indexReponse = restHighLevelClient.index(request, twmallElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(indexReponse);
    }

    @Test
    void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("bank");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("address", "mill")));
        searchSourceBuilder.aggregation(AggregationBuilders.terms("ageAgg").field("age"));
        searchSourceBuilder.aggregation(AggregationBuilders.avg("balanceAvg").field("balance"));
        SearchRequest sre = searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(sre, twmallElasticSearchConfig.COMMON_OPTIONS);


        Aggregations aggregations = searchResponse.getAggregations();
        List<Aggregation> listAggs = aggregations.asList();
        for (Aggregation everyAgg: listAggs){
            System.out.println(everyAgg.getName());
            System.out.println("*****************");
            if(everyAgg.getType().equalsIgnoreCase("avg")){
                Avg agg = aggregations.get(everyAgg.getName());
                System.out.println(agg.getValue());
            }else{
                Terms agg =  aggregations.get(everyAgg.getName());
                for (Terms.Bucket bucket : agg.getBuckets()) {
                    String keyString = bucket.getKeyAsString();
                    System.out.println(keyString);
                }
            }
        }

        SearchHits searchHits = searchResponse.getHits();
        System.out.println(searchHits.getTotalHits().value);
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String index = hit.getIndex();
            String id = hit.getId();
            System.out.println(index);
            System.out.println(id);
            System.out.println(hit.getSourceAsString());
            System.out.println("----------------");
            // do something with the SearchHit
        }
    }

    @Test
    public void testAddSku() throws IOException {


        /**
         * IndexRequest request = new IndexRequest("users");
 *         request.id("1");
 *         String jsonString = "{" +
 *                 "\"user\":\"kimchy\"," +
 *                 "\"postDate\":\"2013-01-30\"," +
 *                 "\"message\":\"trying out Elasticsearch\"" +
 *                 "}";
 *         request.source(jsonString, XContentType.JSON);
 *         IndexResponse indexReponse = restHighLevelClient.index(request, twmallElasticSearchConfig.COMMON_OPTIONS);
 *         System.out.println(indexReponse);
         */

        BulkRequest bulkRequest = new BulkRequest();

        IndexRequest indexRequest = new IndexRequest("goodssku");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        indexRequest.source(jsonString, XContentType.JSON);
        bulkRequest.add(indexRequest);

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, twmallElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(bulkResponse.buildFailureMessage());
    }
}
