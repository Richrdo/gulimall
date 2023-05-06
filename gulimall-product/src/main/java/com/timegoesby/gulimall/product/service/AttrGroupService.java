package com.timegoesby.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timegoesby.common.utils.PageUtils;
import com.timegoesby.gulimall.product.entity.AttrGroupEntity;

import java.util.Map;

/**
 * 属性分组
 *
 * @author ceaser
 * @email ceaser@gmail.com
 * @date 2023-04-26 16:19:28
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long catelogId);

}

