package com.timegoesby.gulimall.coupon.service.impl;

import com.timegoesby.common.to.MemberPrice;
import com.timegoesby.common.to.SkuReductionTo;
import com.timegoesby.gulimall.coupon.entity.MemberPriceEntity;
import com.timegoesby.gulimall.coupon.entity.SkuLadderEntity;
import com.timegoesby.gulimall.coupon.service.MemberPriceService;
import com.timegoesby.gulimall.coupon.service.SkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timegoesby.common.utils.PageUtils;
import com.timegoesby.common.utils.Query;

import com.timegoesby.gulimall.coupon.dao.SkuFullReductionDao;
import com.timegoesby.gulimall.coupon.entity.SkuFullReductionEntity;
import com.timegoesby.gulimall.coupon.service.SkuFullReductionService;


@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {

    @Autowired
    SkuLadderService skuLadderService;

    @Autowired
    MemberPriceService memberPriceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuReduction(SkuReductionTo reductionTo) {
        // 1、保存满减打折、会员价
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        skuLadderEntity.setSkuId(reductionTo.getSkuId());
        skuLadderEntity.setFullCount(reductionTo.getFullCount());
        skuLadderEntity.setDiscount(reductionTo.getDiscount());
        skuLadderEntity.setAddOther(reductionTo.getCountStatus());

        if (reductionTo.getFullCount() > 0) {
            skuLadderService.save(skuLadderEntity);
        }

        SkuFullReductionEntity reductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(reductionTo, reductionEntity);
        if (reductionEntity.getFullPrice().compareTo(new BigDecimal(0)) > 0) {
            this.save(reductionEntity);
        }

        // 3、保存会员价格
        List<MemberPrice> memberPrice = reductionTo.getMemberPrice();

        List<MemberPriceEntity> collect = memberPrice.stream().map(item -> {
            MemberPriceEntity priceEntity = new MemberPriceEntity();
            priceEntity.setSkuId(reductionTo.getSkuId());
            priceEntity.setMemberLevelId(item.getId());
            priceEntity.setMemberLevelName(item.getName());
            priceEntity.setMemberPrice(item.getPrice());
            priceEntity.setAddOther(1);

            return priceEntity;
        }).filter(item -> {
            return item.getMemberPrice().compareTo(new BigDecimal(0)) > 0;
        }).collect(Collectors.toList());

        memberPriceService.saveBatch(collect);

    }

}
