package com.xiao5.twmall.product.vo;

import lombok.Data;

@Data
public class AttrRespVo extends AttrVo {

    private Long attrGroupId;

    private Long catelogId;

    private String catelogPath;
}
