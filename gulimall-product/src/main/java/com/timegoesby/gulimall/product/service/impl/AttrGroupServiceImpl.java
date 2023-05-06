package com.timegoesby.gulimall.product.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timegoesby.common.utils.PageUtils;
import com.timegoesby.common.utils.Query;

import com.timegoesby.gulimall.product.dao.AttrGroupDao;
import com.timegoesby.gulimall.product.entity.AttrGroupEntity;
import com.timegoesby.gulimall.product.service.AttrGroupService;
import org.springframework.util.StringUtils;


@Slf4j
@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId);
        if (catelogId == 0) {
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),
                    wrapper);
            log.error("query is "+wrapper.getSqlSelect());
            return new PageUtils(page);
        } else {
            String key = (String) params.get("key");
            // select * from pms_attr_groups where catelog_id=? and ( attr_group_id=key or attr_group_name like %key% )
            if (!StringUtils.isEmpty(key)) {
                wrapper.and((obj) -> {
                    obj.eq("attr_group_id", key).or().like("attr_group_name", key);

                });
            }
            log.debug("query is "+wrapper.getSqlSelect());
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),
                    wrapper);
            return new PageUtils(page);
        }
    }

}
