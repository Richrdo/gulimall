package com.timegoesby.gulimall.order.dao;

import com.timegoesby.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author ceaser
 * @email ceaser@gmail.com
 * @date 2023-04-27 16:21:19
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
