package com.xiao5.twmall.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import com.xiao5.twmall.common.utils.Query;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.product.dao.AttrAttrgroupRelationDao;
import com.xiao5.twmall.product.dao.AttrDao;
import com.xiao5.twmall.product.entity.AttrAttrgroupRelationEntity;
import com.xiao5.twmall.product.entity.AttrEntity;
import com.xiao5.twmall.product.service.AttrAttrgroupRelationService;
import com.xiao5.twmall.product.service.AttrService;
import com.xiao5.twmall.product.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiao5.twmall.product.dao.AttrGroupDao;
import com.xiao5.twmall.product.entity.AttrGroupEntity;
import com.xiao5.twmall.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    private AttrDao attrDao;

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    @Autowired
    private AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils getAttrGroupByCateId(Map<String, Object> params, Long cateId) {

        Long page = 1L;
        if (params.containsKey("page")){
            page = Long.parseLong((String)params.get("page"));
        }

        Long limit = 1L;
        if (params.containsKey("limit")){
            limit = Long.parseLong((String)params.get("limit"));
        }
        IPage<AttrGroupEntity> basePage = new Page<AttrGroupEntity>(page, limit);


        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<AttrGroupEntity>();
        if(cateId != 0){
            queryWrapper.eq("catelog_id", cateId);
        }
        if(params.containsKey("key")){
            String key = (String)params.get("key");
            if(!StringUtils.isEmptyOrWhitespaceOnly(key)){
                queryWrapper.and((Obj)->{
                    Obj.eq("attr_group_id", key).or().like("attr_group_name", key);
                });
            }
        }
        queryWrapper.orderByDesc("sort");

        IPage<AttrGroupEntity>  pageInfo = this.page(basePage, queryWrapper);
        return new PageUtils(pageInfo);
    }

    @Override
    public PageUtils getOneGroupIncludeAttrs(Long attrGroupId) {
        List<AttrAttrgroupRelationEntity> listRelation = attrAttrgroupRelationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrGroupId));
        List<Long> attrIdList = listRelation.stream().map((everyList)->{
            return everyList.getAttrId();
        }).collect(Collectors.toList());
        return new PageUtils(attrDao.selectPage(new Page(2, 1), new QueryWrapper<AttrEntity>().in("attr_id", attrIdList)));
    }

    @Override
    public PageUtils getOneGroupExcludeAttrs(Long attrGroupId) {
        List<AttrAttrgroupRelationEntity> listRelation = attrAttrgroupRelationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrGroupId));
        List<Long> attrIdList = listRelation.stream().map((everyLation)->{
            return everyLation.getAttrId();
        }).collect(Collectors.toList());

        return new PageUtils(attrDao.selectPage(new Page<AttrEntity>(1, 10), new QueryWrapper<AttrEntity>().notIn("attr_id", attrIdList)));
    }

    @Override
    public List<AttrGroupVo> withattr(Long catelogId) {
        //获取分类下所有的属性组
        List<AttrGroupEntity> attrGroupEntities = this.baseMapper.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));

        List<AttrGroupVo> attrGroupVos = attrGroupEntities.stream().map((item)->{
            AttrGroupVo attrGroupVo = new AttrGroupVo();
            BeanUtils.copyProperties(item, attrGroupVo);

            //根据属性组找到属性与属性组的关系
            List<AttrAttrgroupRelationEntity> attrgroupRelationEntities = attrAttrgroupRelationService.list(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", item.getAttrGroupId()));

            //获取所有的属性
            List<Long> attrIds = attrgroupRelationEntities.stream().map((everyRelation)->{
                return everyRelation.getAttrId();
            }).collect(Collectors.toList());
            List<AttrEntity> attrEntities = attrService.list(new QueryWrapper<AttrEntity>().in("attr_id", attrIds));

            List<AttrVo> attrVos = attrEntities.stream().map((everyAttr)->{
                AttrVo attrVo = new AttrVo();
                BeanUtils.copyProperties(everyAttr, attrVo);
                return attrVo;
            }).collect(Collectors.toList());

            attrGroupVo.setAttrs(attrVos);

            return attrGroupVo;
        }).collect(Collectors.toList());

        return attrGroupVos;

    }

    @Override
    public List<SaleAttrGroupVo> getAttrBySpuId(Long catelogId) {
        return baseMapper.getAttrBySpuId(catelogId);
    }

}