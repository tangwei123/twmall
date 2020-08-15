package com.xiao5.twmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.member.entity.MemberReceiveAddressEntity;

import java.util.Map;

/**
 * ?????????
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 16:23:38
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

