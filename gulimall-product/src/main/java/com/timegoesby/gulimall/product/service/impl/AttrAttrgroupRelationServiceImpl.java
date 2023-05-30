package com.timegoesby.gulimall.product.service.impl;

import com.timegoesby.gulimall.product.vo.AttrGroupRelationVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timegoesby.common.utils.PageUtils;
import com.timegoesby.common.utils.Query;

import com.timegoesby.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.timegoesby.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.timegoesby.gulimall.product.service.AttrAttrgroupRelationService;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Autowired
    AttrAttrgroupRelationDao relationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<AttrAttrgroupRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void addRelation(List<AttrGroupRelationVo> relationVo) {
        List<AttrAttrgroupRelationEntity> entities = relationVo.stream().map((item)->{
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item,relationEntity);

            return relationEntity;
        }).collect(Collectors.toList());

        this.saveBatch(entities);
    }

}
