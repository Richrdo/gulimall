package com.timegoesby.gulimall.product.service.impl;

import com.timegoesby.common.constant.ProductConstant;
import com.timegoesby.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.timegoesby.gulimall.product.dao.AttrDao;
import com.timegoesby.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.timegoesby.gulimall.product.entity.AttrEntity;
import com.timegoesby.gulimall.product.vo.AttrGroupWithAttrsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    @Autowired
    AttrAttrgroupRelationDao relationDao;

    @Autowired
    AttrDao attrDao;

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
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>();
        String key = (String) params.get("key");
        // select * from pms_attr_groups where catelog_id=? and ( attr_group_id=key or attr_group_name like %key% )
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("attr_group_id", key).or().like("attr_group_name", key);

            });
        }
        if (catelogId == 0) {
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),
                    wrapper);
            return new PageUtils(page);
        } else {
            wrapper.eq("catelog_id", catelogId);
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),
                    wrapper);
            return new PageUtils(page);
        }
    }

    /**
     * 根据分类ID查出所有的分组以及这些组里面的属性
     *
     * @param catelogId
     * @return
     */
    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {

        // 1、查询分组关系
        List<AttrGroupEntity> attrGroupEntityList = this.list(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));

        List<AttrGroupWithAttrsVo> attrsVos = attrGroupEntityList.stream().map(item -> {
            AttrGroupWithAttrsVo vo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(item,vo);

            // 2、根据属性分组id查询对应属性id
            List<AttrAttrgroupRelationEntity> relationEntityList = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id",item.getAttrGroupId()));

            // 3、根据id查询属性信息
            List<AttrEntity> attrEntities = relationEntityList.stream().map(attrAttrgroupRelationEntity -> {
                AttrEntity  entity = attrDao.selectById(attrAttrgroupRelationEntity.getAttrId());
                return entity;
            }).filter(attr->{
                return attr.getAttrType()==ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode();
            }).collect(Collectors.toList());

            vo.setAttrs(attrEntities);

            return vo;
        }).collect(Collectors.toList());

        return attrsVos;
    }

}
