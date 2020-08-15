package com.xiao5.twmall.product.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import com.xiao5.twmall.common.utils.Query;
import com.xiao5.twmall.common.utils.PageUtils;
import com.xiao5.twmall.common.utils.R;
import com.xiao5.twmall.product.dao.*;
import com.xiao5.twmall.product.entity.*;
import com.xiao5.twmall.product.feign.CouponFeign;
import com.xiao5.twmall.product.feign.EsFeign;
import com.xiao5.twmall.product.feign.WareFeign;
import com.xiao5.twmall.product.service.*;
import com.xiao5.twmall.product.to.WareSkuStockTo;
import com.xiao5.twmall.product.to.es.SkuEsModel;
import com.xiao5.twmall.product.vo.feignvo.coupon.MemberPriceRemote;
import com.xiao5.twmall.product.vo.feignvo.coupon.SkuFullReduction;
import com.xiao5.twmall.product.vo.feignvo.coupon.SkuLadder;
import com.xiao5.twmall.product.vo.spusavevo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.transaction.annotation.Transactional;


@Service("spuInfoService")
@Slf4j
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    private SpuInfoDescService spuInfoDescService;

    @Autowired
    private SpuImagesService spuImagesService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @Autowired
    private ProductAttrValueDao productAttrValueDao;

    @Autowired
    private AttrService attrService;

    @Autowired
    private SkuInfoService skuInfoService;

    @Autowired
    private SkuInfoDao skuInfoDao;

    @Autowired
    private SkuImagesService skuImagesService;

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private AttrDao attrDao;

    @Autowired
    private CouponFeign couponFeign;

    @Autowired
    private EsFeign esFeign;

    @Autowired
    private WareFeign wareFeign;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );
        return new PageUtils(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addOneSpu(SpuSaveVo spuSaveVo) {
        //保存spu的信息
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(spuSaveVo, spuInfoEntity);
        Date date = new Date();
        spuInfoEntity.setCreateTime(date);
        this.save(spuInfoEntity);
        //保存spu描述的图片
//        List<SpuInfoDescEntity> spuInfoDescEntities = spuSaveVo.getDecript().stream().map((item)->{
            SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
            spuInfoDescEntity.setSpuId(spuInfoEntity.getId());
            spuInfoDescEntity.setDecript(String.join(",", spuSaveVo.getDecript()));
//            return spuInfoDescEntity;
//        }).collect(Collectors.toList());
        spuInfoDescService.save(spuInfoDescEntity);

        //保存spu的图片集
        List<SpuImagesEntity> spuImagesEntities = spuSaveVo.getImages().stream().map((item)->{
            SpuImagesEntity spuImagesEntity = new SpuImagesEntity();
            spuImagesEntity.setSpuId(spuInfoEntity.getId());
//            spuImagesEntity.setImgName("");
//            spuImagesEntity.setImgSort(0);
//            spuImagesEntity.setDefaultImg(0);
            spuImagesEntity.setImgUrl(item);
            return spuImagesEntity;
        }).collect(Collectors.toList());
        spuImagesService.saveBatch(spuImagesEntities);
        //保存spu的规格参数 pms_product_attr_value
        List<ProductAttrValueEntity> productAttrValueEntities = spuSaveVo.getBaseAttrs().stream().map((item)->{
            ProductAttrValueEntity productAttrValueEntity = new ProductAttrValueEntity();
            productAttrValueEntity.setSpuId(spuInfoEntity.getId());


            AttrEntity attrEntity = attrService.getById(item.getAttrId());
            productAttrValueEntity.setAttrName(attrEntity.getAttrName());
            productAttrValueEntity.setAttrId(item.getAttrId());
            productAttrValueEntity.setAttrValue(item.getAttrValues());
            productAttrValueEntity.setQuickShow(item.getShowDesc());
            return productAttrValueEntity;
        }).collect(Collectors.toList());
        productAttrValueService.saveBatch(productAttrValueEntities);

        //保存spu的积分信息  twmall_sms的sms_spu_bounds
        Bounds bounds = new Bounds();
        bounds.setSpuId(spuInfoEntity.getId());
        bounds.setBuyBounds(spuSaveVo.getBounds().getBuyBounds());
        bounds.setGrowBounds(spuSaveVo.getBounds().getGrowBounds());
        couponFeign.addSpuBounds(bounds);

        //保存sku信息
        List<Skus> skuses = spuSaveVo.getSkus();
        skuses.forEach((everySku)->{
            //保存sku基本信息 sku_info
            String skuDefaultImg = "";
            for (Images everyImg:everySku.getImages()){
                if(everyImg.getDefaultImg() == 1){
                    skuDefaultImg = everyImg.getImgUrl();
                }
            }

            SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
            skuInfoEntity.setSpuId(spuInfoEntity.getId());
            skuInfoEntity.setSkuName(everySku.getSkuName());
            skuInfoEntity.setSkuTitle(everySku.getSkuTitle());
            skuInfoEntity.setSkuSubtitle(everySku.getSkuSubtitle());
            skuInfoEntity.setPrice(everySku.getPrice());
            skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
            skuInfoEntity.setCatalogId(spuInfoEntity.getCatalogId());
            skuInfoEntity.setSaleCount(0L);
            skuInfoEntity.setSkuDefaultImg(skuDefaultImg);
            skuInfoService.save(skuInfoEntity);

            //保存sku图片信息 sku_images
            List<SkuImagesEntity> skuImagesEntities = everySku.getImages().stream().map((img)->{
                SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                BeanUtils.copyProperties(img, skuImagesEntity);
                skuImagesEntity.setSkuId(skuInfoEntity.getSkuId());
                return skuImagesEntity;
            }).filter((entity)->{
                return !StringUtils.isEmptyOrWhitespaceOnly(entity.getImgUrl());
            }).collect(Collectors.toList());
            skuImagesService.saveBatch(skuImagesEntities);

            //保存sku销售属性信息 sku_sale_attr_value
            List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = everySku.getAttr().stream().map((everyAttr)->{
                SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                skuSaleAttrValueEntity.setSkuId(skuInfoEntity.getSkuId());
                BeanUtils.copyProperties(everyAttr, skuSaleAttrValueEntity);
                return skuSaleAttrValueEntity;
            }).collect(Collectors.toList());
            skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);


            //保存sku的优惠、满减等信息 twmall_sms的 sms_sku_ladder、sms_sku_full_reduction、sms_member_price
            //sms_sku_ladder
            SkuLadder skuLadder = new SkuLadder();
            skuLadder.setSkuId(skuInfoEntity.getSkuId());
            skuLadder.setFullCount(everySku.getFullCount());
            skuLadder.setDiscount(everySku.getDiscount());
            skuLadder.setPrice(everySku.getPrice());
            couponFeign.addOneSkuLadder(skuLadder);

            //sms_sku_full_reduction
            SkuFullReduction skuFullReduction = new SkuFullReduction();
            skuFullReduction.setSkuId(skuInfoEntity.getSkuId());
            skuFullReduction.setFullPrice(everySku.getFullPrice());
            skuFullReduction.setReducePrice(everySku.getReducePrice());
            couponFeign.addOneSkuFullReduction(skuFullReduction);

            //sms_member_price
            List<MemberPriceRemote> memberPrices = everySku.getMemberPrice().stream().map((everyMemberPrice)->{
                MemberPriceRemote memberPrice = new MemberPriceRemote();
                memberPrice.setId(everyMemberPrice.getId());
                memberPrice.setSkuId(skuInfoEntity.getSkuId());
                memberPrice.setMemberLevelId(everyMemberPrice.getId());
                memberPrice.setMemberLevelName(everyMemberPrice.getName());
                memberPrice.setMemberPrice(everyMemberPrice.getPrice());
                return memberPrice;
            }).collect(Collectors.toList());
            couponFeign.addMemberPrice(memberPrices);
        });
    }


    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {

        QueryWrapper<SpuInfoEntity> queryWrapper = new QueryWrapper<>();

        if(params.containsKey("key") && params.get("key") != null && !StringUtils.isEmptyOrWhitespaceOnly(String.valueOf(params.get("key")))){
            queryWrapper.and(i->
                i.eq("id", params.get("key")).or().like("spu_name", params.get("key"))
            );
        }

        if(params.containsKey("catelogId") && params.get("catelogId") != null && !StringUtils.isEmptyOrWhitespaceOnly(String.valueOf(params.get("catelogId")))){
            queryWrapper.eq("catalog_id", params.get("catelogId"));
        }

        if(params.containsKey("brandId") && params.get("brandId") != null && !StringUtils.isEmptyOrWhitespaceOnly(String.valueOf(params.get("brandId")))){
            queryWrapper.eq("brand_id", params.get("brandId"));
        }

        if(params.containsKey("status") && params.get("status") != null && !StringUtils.isEmptyOrWhitespaceOnly(String.valueOf(params.get("status")))){
            queryWrapper.eq("publish_status", params.get("status"));
        }

        IPage<SpuInfoEntity> pageinfo = this.page(new Page<SpuInfoEntity>(1,10),  queryWrapper);
        return new PageUtils(pageinfo);
    }

    @Override
    public void upOneProduct(Long spuId) {

        SpuInfoEntity spuInfoEntity = this.getById(spuId);
        if(spuInfoEntity == null){
            throw new RuntimeException("");
        }
        spuInfoEntity.setPublishStatus(1);
        spuInfoEntity.setUpdateTime(new Date());
        this.updateById(spuInfoEntity);//修改商品的商家状态和更新时间

        List<ProductAttrValueEntity> listProductAttrValueEntity = productAttrValueDao.selectList(new QueryWrapper<ProductAttrValueEntity>().eq("spu_id", spuInfoEntity.getId()));//获取商品对应的属性信息
        List<Long> listAttrId = listProductAttrValueEntity.stream().map((evetyAttrEntity)->{
            return evetyAttrEntity.getAttrId();
        }).collect(Collectors.toList());

        List<AttrEntity> listAttrEntity = attrDao.selectList(new QueryWrapper<AttrEntity>().in("attr_id", listAttrId).eq("search_type", 1));//获取到所有的属性中能被索引的属性
        List<SkuEsModel.Attrs> listSkuEsModelAttrs = listAttrEntity.stream().map((everyAttr)->{
            SkuEsModel.Attrs attr = new SkuEsModel.Attrs();
            attr.setAttrValue(everyAttr.getValueSelect());
            attr.setAttrName(everyAttr.getAttrName());
            attr.setAttrId(everyAttr.getAttrId());
            return attr;
        }).collect(Collectors.toList());



        List<SkuEsModel> listSkuModel = new LinkedList<>();
        List<SkuInfoEntity> listSkuInfo = skuInfoDao.selectList(new QueryWrapper<SkuInfoEntity>().eq("spu_id", spuId));//根据spuId查找到所有的sku信息

        //调用远程服务，查看库存
        List<Long> skuIds = listSkuInfo.stream().map(item->{
            return item.getSkuId();
        }).collect(Collectors.toList());

        Map<Long, Integer> collector = new LinkedHashMap<>();
        try{
            R r = wareFeign.getWareSkuInfo(skuIds);
            TypeReference<List<WareSkuStockTo>> typeReference = new TypeReference<List<WareSkuStockTo>>(){};
            List<WareSkuStockTo> wareSkuStockToList = r.getData(typeReference);
            collector = wareSkuStockToList.stream().collect(Collectors.toMap(item->item.getSkuId(), item->item.getStock()));

        }catch(Exception error){
            log.error("调用远程查询库存出粗:", error);
        }


        for (SkuInfoEntity everySkuInfoEntity: listSkuInfo){

            SkuEsModel skuEsModel = new SkuEsModel();
            skuEsModel.setSpuId(spuInfoEntity.getId());
            skuEsModel.setSkuId(everySkuInfoEntity.getSkuId());
            skuEsModel.setSkuTitle(everySkuInfoEntity.getSkuTitle());
            skuEsModel.setSkuPrice(everySkuInfoEntity.getPrice());
            skuEsModel.setSkuImg(everySkuInfoEntity.getSkuDefaultImg());
            skuEsModel.setSaleCount(everySkuInfoEntity.getSaleCount());
            skuEsModel.setHotScore(0L);
            if(collector.size() != 0){
                if(collector.get(everySkuInfoEntity.getSkuId()) != null && collector.get(everySkuInfoEntity.getSkuId()) > 0){
                    skuEsModel.setHasStock(true);
                }else{
                    skuEsModel.setHasStock(false);
                }
            }else{
                skuEsModel.setHasStock(false);
            }



            BrandEntity brandEntity = brandDao.selectById(everySkuInfoEntity.getBrandId());//获取品牌信息
            if(brandEntity != null){
                skuEsModel.setBrandId(brandEntity.getBrandId());
                skuEsModel.setBrandName(brandEntity.getName());
                skuEsModel.setBrandImg(brandEntity.getLogo());
            }

            CategoryEntity categoryEntity = categoryDao.selectById(spuInfoEntity.getCatalogId());//获取分类信息
            if(categoryEntity != null){
                skuEsModel.setCatelogId(categoryEntity.getCatId());
                skuEsModel.setCatelogName(categoryEntity.getName());
            }

            skuEsModel.setAttrs(listSkuEsModelAttrs);
            log.error(skuEsModel.toString());
            listSkuModel.add(skuEsModel);
        }

        //将数据发送给es进行保存
        boolean result = esFeign.addSku(listSkuModel);

    }



}