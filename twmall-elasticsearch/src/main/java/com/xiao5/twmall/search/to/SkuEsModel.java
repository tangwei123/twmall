package com.xiao5.twmall.search.to;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@ToString
@Data
public class SkuEsModel {

    private Long spuId;

    private Long skuId;

    private String skuTitle;

    private BigDecimal skuPrice;

    private String skuImg;

    private Long saleCount;

    private Boolean hasStock;

    private Long hotScore;

    private Long brandId;

    private String brandName;

    private String brandImg;

    private Long catelogId;

    private String catelogName;

    private List<Attrs> attrs;

    @Data
    public static class Attrs{
        private Long attrId;

        private String attrName;

        private String attrValue;
    }
}
