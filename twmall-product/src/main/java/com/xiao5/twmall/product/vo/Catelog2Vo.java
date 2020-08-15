package com.xiao5.twmall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Catelog2Vo {
    public Long id;
    public String name;
    public Long catalog1Id;
    public List<CateLog3> catalog3List;

    @Data
    public class CateLog3{
        public Long catalog2Id;
        public Long id;
        public String name;
    }
}
