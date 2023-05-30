package com.timegoesby.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timegoesby.common.utils.PageUtils;
import com.timegoesby.gulimall.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author ceaser
 * @email ceaser@gmail.com
 * @date 2023-04-26 16:19:28
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuInfo(SkuInfoEntity infoEntity);
}

