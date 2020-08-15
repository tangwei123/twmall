package com.xiao5.twmall.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiao5.twmall.common.utils.Query;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.product.service.CategoryBrandRelationService;
import com.xiao5.twmall.product.vo.Catelog2Vo;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiao5.twmall.product.dao.CategoryDao;
import com.xiao5.twmall.product.entity.CategoryEntity;
import com.xiao5.twmall.product.service.CategoryService;


@Slf4j
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    public CategoryDao categoryDao;

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> listCate = categoryDao.selectList(null);
        List<CategoryEntity> level1Menu = listCate.stream().filter((categoryEntity)->{
            return categoryEntity.getParentCid() == 0;
        }).sorted((menu1, menu2)->{
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).map((menu)->{
            menu.setChildCate(getChild(menu, listCate));
            return menu;
        }).collect(Collectors.toList());

        return level1Menu;
    }

    @Override
    public void removeMenuByIds(List<Long> catIds) {
        //TODO 检查当前删除的菜单，是否被别的地方引用
        categoryDao.deleteBatchIds(catIds);
    }

    @Override
    public String getCatelogPath(Long cateId) {
        List<String> list = new ArrayList<>();
        this.getParentCatelog(cateId, list);
        Collections.reverse(list);
        String str = "";
        for (String everyCateName: list){
            str = str + everyCateName + "、";
        }
        str = str.substring(0, (str.length() -1));
        return str;
    }

    @Override
    public void updateDatail(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCateInfo(category.getCatId(), category.getName());
    }

    @Cacheable(value = "caterogy", key = "'getFirstLevelCate'", sync = true)
    @Override
    public List<CategoryEntity> getFirstLevelCate() {
        List<CategoryEntity> categoryEntityList = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0).eq("cat_level", 1));
        return categoryEntityList;
    }


    @Override
    public Map<String, List<Catelog2Vo>> getCatelog() {
        //处理二级、三级分类
        Map<String, List<Catelog2Vo>> stringListMap = null;
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String mapData = valueOperations.get("stringListMap");
        if(mapData == null){
            log.error("内存被击穿");
            RLock lock = redissonClient.getLock("menuList");
            lock.lock();
            try{
                log.error("获取到锁");
                mapData = valueOperations.get("stringListMap");
                if(mapData == null){
                    log.error("数据不存在");
                    List<CategoryEntity> categoryEntityList = baseMapper.selectList(null);//获取所有的数据
                    if(categoryEntityList != null){
                        stringListMap = categoryEntityList.stream().collect(Collectors.toMap(
                                (k)->{
                                    return k.getCatId().toString();
                                },
                                (v)->{
                                    List<CategoryEntity> secondCateEntity = categoryEntityList.stream().filter(item->{
                                        return item.getParentCid() == v.getCatId();
                                    }).collect(Collectors.toList());//baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", v.getCatId()));
                                    List<Catelog2Vo> catelog2VoList = secondCateEntity.stream().map(item->{
                                        Catelog2Vo catelog2Vo = new Catelog2Vo();
                                        catelog2Vo.setCatalog1Id(v.getCatId());
                                        catelog2Vo.setId(item.getCatId());
                                        catelog2Vo.setName(item.getName());
                                        List<CategoryEntity> thirdCateEntity = categoryEntityList.stream().filter(it->{
                                            return it.getParentCid() == item.getCatId();
                                        }).collect(Collectors.toList());//baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", item.getCatId()));
                                        List<Catelog2Vo.CateLog3> thirdCatelogList = thirdCateEntity.stream().map(everyThird->{
                                            Catelog2Vo.CateLog3 cateLog3 = catelog2Vo.new CateLog3();
                                            cateLog3.setId(everyThird.getCatId());
                                            cateLog3.setName(everyThird.getName());
                                            cateLog3.setCatalog2Id(item.getCatId());
                                            return cateLog3;
                                        }).collect(Collectors.toList());
                                        catelog2Vo.setCatalog3List(thirdCatelogList);
                                        return catelog2Vo;
                                    }).collect(Collectors.toList());
                                    return catelog2VoList;
                                }
                        ));
                        valueOperations.set("stringListMap", JSON.toJSONString(stringListMap), new Random().nextInt(), TimeUnit.MINUTES);
                    }else{
                        valueOperations.set("stringListMap", "0", 3, TimeUnit.MINUTES);
                    }
                    stringListMap = JSON.parseObject(valueOperations.get("stringListMap"), new TypeReference<Map<String, List<Catelog2Vo>>>(){});
                }else{
                    log.error("数据已取到");
                    stringListMap = JSON.parseObject(mapData, new TypeReference<Map<String, List<Catelog2Vo>>>(){});
                }
            }catch(Exception error){

            }
            lock.unlock();
        }else{
            stringListMap = JSON.parseObject(mapData, new TypeReference<Map<String, List<Catelog2Vo>>>(){});
        }
        return stringListMap;
    }

    public void getParentCatelog(Long cateId, List list){
        CategoryEntity categoryEntity = this.getById(cateId);
        list.add(categoryEntity.getName());
        if (categoryEntity.getParentCid() != 0){
            this.getParentCatelog(categoryEntity.getParentCid(), list);
        }
    }

    //递归查找子菜单
    private List<CategoryEntity> getChild(CategoryEntity root, List<CategoryEntity> entities){
        List<CategoryEntity> cateList = entities.stream().filter((everyCate)->{
            return root.getCatId() == everyCate.getParentCid();
        }).sorted((cate1, cate2)->{
            return (cate1.getSort() == null ? 0 : cate1.getSort()) - (cate2.getSort() == null ? 0 : cate2.getSort());
        }).map((everyCate)->{
            everyCate.setChildCate(getChild(everyCate, entities));
            return everyCate;
        }).collect(Collectors.toList());


        return cateList;
    }


}