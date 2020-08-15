package com.xiao5.twmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xiao5.twmall.common.utils.Query;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.product.entity.AttrGroupEntity;
import com.xiao5.twmall.product.service.AttrGroupService;
import com.xiao5.twmall.product.service.CategoryService;
import com.xiao5.twmall.product.vo.AttrGroupRelationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiao5.twmall.product.dao.AttrAttrgroupRelationDao;
import com.xiao5.twmall.product.entity.AttrAttrgroupRelationEntity;
import com.xiao5.twmall.product.service.AttrAttrgroupRelationService;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<AttrAttrgroupRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void addOneRelation(Long attrId, Long attrGroupId) {
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        attrAttrgroupRelationEntity.setAttrId(attrId);
        attrAttrgroupRelationEntity.setAttrGroupId(attrGroupId);
        this.save(attrAttrgroupRelationEntity);
    }

    @Override
    public void updateOneRelation(Long attrId, Long attrGroupId) {
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        attrAttrgroupRelationEntity.setAttrGroupId(attrGroupId);

        UpdateWrapper<AttrAttrgroupRelationEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("attr_id", attrId);
        this.update(attrAttrgroupRelationEntity, updateWrapper);
    }

    @Override
    public Map<String, Object> getOneByAttrId(Integer attrId) {
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = this.getOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
        Long attrGroupId = attrAttrgroupRelationEntity.getAttrGroupId();
        AttrGroupEntity attrGroupEntity = attrGroupService.getById(attrGroupId);

        String catelogPath = categoryService.getCatelogPath(attrGroupEntity.getCatelogId());

        Map<String, Object> resData = new LinkedHashMap<>();

        resData.put("cateLogPath", catelogPath);
        resData.put("cateLogId", attrGroupEntity.getCatelogId());
        resData.put("attrGroupId", attrGroupId);
        return resData;
    }

    @Override
    public void deleteRelations(AttrGroupRelationVo[] attrGroupRelationVos) {

        List<AttrAttrgroupRelationEntity> listData = Arrays.asList(attrGroupRelationVos).stream().map((everyVos)->{
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(everyVos, attrAttrgroupRelationEntity);
            return attrAttrgroupRelationEntity;
        }).collect(Collectors.toList());

        attrAttrgroupRelationDao.deleteBranch(listData);
    }

    @Override
    public void addRelations(AttrGroupRelationVo[] attrGroupRelationVo) {



        List<AttrAttrgroupRelationEntity> attrgroupRelationEntities = Arrays.asList(attrGroupRelationVo).stream().map((every)->{
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(every, attrAttrgroupRelationEntity);
            return attrAttrgroupRelationEntity;
        }).collect(Collectors.toList());

        List<AttrAttrgroupRelationEntity> attrgroupRelation = attrAttrgroupRelationDao.checkExists(attrgroupRelationEntities);
        if(attrgroupRelation != null && attrgroupRelation.size() != 0){
            throw new RuntimeException("被新增的关联关系有的已经存在，请检查");
        }

        this.saveBatch(attrgroupRelationEntities);
    }


}