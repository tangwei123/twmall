package com.xiao5.twmall.search.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * catalog3Id=12&keyword=小米&sort=saleCount_desc(asc)或者skuPrice_desc(asc)或者hotScore_desc(asc)&hasStock=0/1(是否有货)&skuPrice=1_500/_500/500_&brandId=12&brandId=13&attrs=1_其他:安卓&attrs=2_5寸:6寸&page=1
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SearchParam {
    private String keyword;//页面传递的搜索关键字
    private Long catalog3Id;//页面传递的三级分类ID
    private String sort;//自定义的排序字符串
    private Integer hasStock;//是否有库存
    private String skuPrice;//价格区间
    private List<Integer> brandId;//品牌ID号列表
    private List<String> attrs;//属性值列表
    private Integer pageNum;//页码
}
