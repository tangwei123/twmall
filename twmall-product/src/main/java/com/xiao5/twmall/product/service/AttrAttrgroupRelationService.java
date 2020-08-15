package com.xiao5.twmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.product.entity.AttrAttrgroupRelationEntity;
import com.xiao5.twmall.product.entity.AttrEntity;
import com.xiao5.twmall.product.vo.AttrGroupRelationVo;
import java.util.List;
import java.util.Map;

/**
 * ????&??????????
 *
 * @author tangwei
 * @email tangwei@qmail.com
 * @date 2020-05-08 08:51:39
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addOneRelation(Long attrId, Long attrGroupId);

    void updateOneRelation(Long attrId, Long attrGroupId);

    Map<String, Object> getOneByAttrId(Integer attrId);

    void deleteRelations(AttrGroupRelationVo[] attrGroupRelationVos);

    void addRelations(AttrGroupRelationVo[] attrGroupRelationVo);
}

