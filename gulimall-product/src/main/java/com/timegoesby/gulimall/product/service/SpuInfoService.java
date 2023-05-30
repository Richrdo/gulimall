package com.timegoesby.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timegoesby.common.utils.PageUtils;
import com.timegoesby.gulimall.product.entity.SpuInfoEntity;
import com.timegoesby.gulimall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author ceaser
 * @email ceaser@gmail.com
 * @date 2023-04-26 16:19:28
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVo spuSaveVo);
}

