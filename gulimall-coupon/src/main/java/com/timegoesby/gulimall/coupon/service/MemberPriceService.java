package com.timegoesby.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timegoesby.common.utils.PageUtils;
import com.timegoesby.gulimall.coupon.entity.MemberPriceEntity;

import java.util.Map;

/**
 * 商品会员价格
 *
 * @author ceaser
 * @email ceaser@gmail.com
 * @date 2023-04-27 15:57:31
 */
public interface MemberPriceService extends IService<MemberPriceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

