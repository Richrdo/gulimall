package com.timegoesby.gulimall.product.dao;

import com.timegoesby.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author ceaser
 * @email ceaser@gmail.com
 * @date 2023-04-26 16:19:28
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
