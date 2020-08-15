package com.xiao5.twmall.product.feign;

import com.xiao5.twmall.common.utils.R;
import com.xiao5.twmall.product.vo.feignvo.coupon.MemberPriceRemote;
import com.xiao5.twmall.product.vo.feignvo.coupon.SkuFullReduction;
import com.xiao5.twmall.product.vo.feignvo.coupon.SkuLadder;
import com.xiao5.twmall.product.vo.spusavevo.Bounds;
import com.xiao5.twmall.product.vo.spusavevo.MemberPrice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "twmall-coupon")
public interface CouponFeign {

    @PostMapping("/coupon/spubounds/addSpuBounds")
    public R addSpuBounds(@RequestBody Bounds bounds);

    @PostMapping("/coupon/skuladder/addOneSkuLadder")
    public R addOneSkuLadder(@RequestBody SkuLadder skuLadder);

    @PostMapping("/coupon/skufullreduction/addOneSkuFullReduction")
    public R addOneSkuFullReduction(@RequestBody SkuFullReduction skuFullReduction);

    @PostMapping("/coupon/memberprice/addMemberPrice")
    public R addMemberPrice(@RequestBody List<MemberPriceRemote> memberPrices);
}
