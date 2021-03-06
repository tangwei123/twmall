package com.xiao5.twmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.product.entity.AttrEntity;
import com.xiao5.twmall.product.vo.AttrRespVo;
import com.xiao5.twmall.product.vo.AttrRespVo;

import java.util.Map;

/**
 * ???????
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addOneAttr(AttrRespVo AttrRespVo);

    void updateOneAttr(AttrRespVo AttrRespVo);

    AttrRespVo getOneAttrDetail(Integer attrId);
}

