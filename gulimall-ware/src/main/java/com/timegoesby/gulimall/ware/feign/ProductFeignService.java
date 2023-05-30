package com.timegoesby.gulimall.ware.feign;

import com.timegoesby.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("gulimall-product")
public interface ProductFeignService {

    /**
     * 远程调用的两种方法：
     * 1、直接让后台指定服务处理 @FeignClient("gulimall-product")+路径 /product/skuinfo/info/{skuId}
     * 2、让所有请求经过网关 @FeignClient("gulimall-gateway")+路径 /api/product/skuinfo/info/{skuId}
     * @param skuId
     * @return
     */
    @RequestMapping("/product/skuinfo/info/{skuId}")
    // @RequiresPermissions("product:skuinfo:info")
    public R info(@PathVariable("skuId") Long skuId);
}
