package com.xiao5.twmall.product.vo;

import lombok.Data;

import java.util.List;

@Data
public class SaleAttrGroupVo {
    public String attrGroupName;
    public List<SaleAttrValueVo> attrValueList;
}
