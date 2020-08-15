package com.xiao5.twmall.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.common.utils.R;
import com.xiao5.twmall.member.feign.CouponFeignService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.xiao5.twmall.member.entity.MemberEntity;
import com.xiao5.twmall.member.service.MemberService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


/**
 * ???
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 16:23:38
 */
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private CouponFeignService couponFeignService;

    @GetMapping("/coupons")
    public R memberCoupon(){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUsername("tangwei");
        R memberCoupon = couponFeignService.memberCoupons();

        R res = R.ok().put("member", memberEntity).put("coupon", memberCoupon.get("coupon"));
        return res;
    }

    @GetMapping("/login")
    public ModelAndView login(HttpSession session){
        ModelAndView mav = new ModelAndView("/login.html");
        session.setAttribute("loginUser", "tangwei");
        return mav;
    }

    @GetMapping("/register")
    public ModelAndView register(HttpSession session){
        ModelAndView mav = new ModelAndView("/register.html");
        System.out.println(session.getAttribute("loginUser"));
        return mav;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("member:member:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("member:member:info")
    public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("member:member:save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("member:member:update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("member:member:delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
