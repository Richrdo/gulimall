package com.timegoesby.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timegoesby.common.utils.PageUtils;
import com.timegoesby.gulimall.ware.entity.PurchaseEntity;
import com.timegoesby.gulimall.ware.vo.MergeVo;
import com.timegoesby.gulimall.ware.vo.PurchaseDoneVo;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author ceaser
 * @email ceaser@gmail.com
 * @date 2023-04-27 16:26:29
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageUnreceive(Map<String, Object> params);

    void mergePurchase(MergeVo mergeVo);

    void receive(List<Long> ids);

    void done(PurchaseDoneVo doneVo);
}

