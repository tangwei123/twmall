package com.xiao5.twmall.search.vo;

import com.xiao5.twmall.search.to.SkuEsModel;
import lombok.Data;

import java.util.List;

@Data
public class SearchResult {
    private List<SkuEsModel> products;//获取到的商品信息
    private Integer pageNum;//当前页码
    private Long total;//总记录数
    private Integer totalPages;//总页数
    private List<SearchResult.BrandInfo> brandInfoList;//存放所有的品牌
    private List<SearchResult.categoryInfo> categoryInfoList;//存放所有的分类
    private List<SearchResult.attrInfo> attrInfoList;//存放所有的属性

    @Data
    public static class BrandInfo{
        private Long brandId;
        private String brandName;
        private String brandImg;
    }

    @Data
    public static class categoryInfo{
        private Long catelogId;
        private String catelogName;
    }

    @Data
    public static class attrInfo{
        private Long attrId;
        private String attrName;
        private List<String> attrValue;
    }
}
