package com.timegoesby.gulimall.order.dao;

import com.timegoesby.gulimall.order.entity.RefundInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退款信息
 * 
 * @author ceaser
 * @email ceaser@gmail.com
 * @date 2023-04-27 16:21:19
 */
@Mapper
public interface RefundInfoDao extends BaseMapper<RefundInfoEntity> {
	
}
