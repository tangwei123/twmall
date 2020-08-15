package com.xiao5.twmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.product.entity.SpuInfoEntity;
import com.xiao5.twmall.product.vo.spusavevo.SpuSaveVo;

import java.util.Map;

/**
 * spu???
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addOneSpu(SpuSaveVo spuSaveVo);

    PageUtils queryPageByCondition(Map<String, Object> params);

    void upOneProduct(Long spuId);
}

