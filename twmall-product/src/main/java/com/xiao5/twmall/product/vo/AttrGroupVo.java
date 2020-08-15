package com.xiao5.twmall.product.vo;

import lombok.Data;

import java.util.List;

@Data
public class AttrGroupVo {

    private Long attrGroupId;
    /**
     * ????
     */
    private String attrGroupName;
    /**
     * ????
     */
    private Integer sort;
    /**
     * ????
     */
    private String descript;
    /**
     * ?????
     */
    private String icon;
    /**
     * ????????id
     */
    private Long catelogId;

    private List<AttrVo> attrs;
}
