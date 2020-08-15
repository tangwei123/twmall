package com.xiao5.twmall.coupon.service.impl;

import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.common.utils.Query;
import com.xiao5.twmall.coupon.vo.MemberPriceVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiao5.twmall.coupon.dao.MemberPriceDao;
import com.xiao5.twmall.coupon.entity.MemberPriceEntity;
import com.xiao5.twmall.coupon.service.MemberPriceService;


@Service("memberPriceService")
public class MemberPriceServiceImpl extends ServiceImpl<MemberPriceDao, MemberPriceEntity> implements MemberPriceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberPriceEntity> page = this.page(
                new Query<MemberPriceEntity>().getPage(params),
                new QueryWrapper<MemberPriceEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void addMemberPrice(List<MemberPriceVo> memberPriceVos) {
        List<MemberPriceEntity> memberPriceEntities = memberPriceVos.stream().map((item)->{
            MemberPriceEntity memberPriceEntity = new MemberPriceEntity();
            BeanUtils.copyProperties(item, memberPriceEntity);
            return memberPriceEntity;
        }).collect(Collectors.toList());
        this.saveBatch(memberPriceEntities);
    }

}