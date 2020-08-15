package com.xiao5.twmall.product.service.impl;

import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.common.utils.Query;
import com.xiao5.twmall.product.entity.AttrAttrgroupRelationEntity;
import com.xiao5.twmall.product.service.AttrAttrgroupRelationService;
import com.xiao5.twmall.product.vo.AttrRespVo;
import com.xiao5.twmall.product.vo.AttrRespVo;
import com.xiao5.twmall.product.vo.CategoryBrandRelationVo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiao5.twmall.product.dao.AttrDao;
import com.xiao5.twmall.product.entity.AttrEntity;
import com.xiao5.twmall.product.service.AttrService;
import org.w3c.dom.Attr;


@Service("attrService")
@RabbitListener(queues = {"twmall2"})
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    @Autowired
    private AttrDao attrDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void addOneAttr(AttrRespVo AttrRespVo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(AttrRespVo, attrEntity);
        this.save(attrEntity);//新增属性信息
        attrAttrgroupRelationService.addOneRelation(attrEntity.getAttrId(), AttrRespVo.getAttrGroupId());
    }

    @Override
    public void updateOneAttr(AttrRespVo AttrRespVo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(AttrRespVo, attrEntity);
        this.updateById(attrEntity);//更新属性信息

        if (AttrRespVo.getAttrGroupId() != null){
            attrAttrgroupRelationService.updateOneRelation(attrEntity.getAttrId(), AttrRespVo.getAttrGroupId());
        }

    }

    @Override
    public AttrRespVo getOneAttrDetail(Integer attrId) {

        AttrRespVo attrRespVo = new AttrRespVo();

        AttrEntity attrEntity = this.getById(attrId);

        BeanUtils.copyProperties(attrEntity, attrRespVo);

        Map<String, Object> mapData = attrAttrgroupRelationService.getOneByAttrId(attrId);

        attrRespVo.setAttrGroupId((Long)mapData.get("attrGroupId"));
        attrRespVo.setCatelogPath((String)mapData.get("cateLogPath"));
        attrRespVo.setCatelogId((Long)mapData.get("cateLogId"));

        return attrRespVo;
    }

    @RabbitHandler
    public void getMessage(CategoryBrandRelationVo message){
        System.out.println("1接收到数据，内容为："+message);
    }

    @RabbitHandler
    public void getMessage(AttrAttrgroupRelationEntity message){
        System.out.println("2接收到数据，内容为："+message);
    }


}