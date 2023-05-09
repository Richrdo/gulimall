package com.timegoesby.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timegoesby.common.utils.PageUtils;
import com.timegoesby.gulimall.product.entity.AttrEntity;
import com.timegoesby.gulimall.product.vo.AttrRespVo;
import com.timegoesby.gulimall.product.vo.AttrVo;

import java.util.Map;

/**
 * 商品属性
 *
 * @author ceaser
 * @email ceaser@gmail.com
 * @date 2023-04-26 16:19:28
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attrVo);
}

