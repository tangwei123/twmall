/**
  * Copyright 2020 bejson.com 
  */
package com.xiao5.twmall.product.vo.spusavevo;
import com.xiao5.twmall.common.group.AddGroup;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

/**
 * Auto-generated: 2020-05-28 14:28:41
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class SpuSaveVo {

    @NotBlank(message = "商品spuName不能为空", groups = {AddGroup.class})
    private String spuName;

    @NotBlank(message = "商品spuDescription不能为空", groups = {AddGroup.class})
    private String spuDescription;

    @NotEmpty(message = "商品catalogId不合法")
    @Min(value=1, message = "商品catalogId不合法", groups = {AddGroup.class})
    private Long catalogId;
    private Long brandId;
    private BigDecimal weight;
    private Integer publishStatus;
    private List<String> decript;
    private List<String> images;
    private Bounds bounds;
    private List<BaseAttrs> baseAttrs;
    private List<Skus> skus;


}