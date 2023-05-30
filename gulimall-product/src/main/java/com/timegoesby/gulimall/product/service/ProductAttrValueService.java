package com.timegoesby.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timegoesby.common.utils.PageUtils;
import com.timegoesby.gulimall.product.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author ceaser
 * @email ceaser@gmail.com
 * @date 2023-04-26 16:19:28
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveProductAttr(List<ProductAttrValueEntity> collect);
}

