package com.timegoesby.product;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.timegoesby.gulimall.product.GulimallProductApplication;
import com.timegoesby.gulimall.product.entity.BrandEntity;
import com.timegoesby.gulimall.product.service.BrandService;
import com.timegoesby.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;


@Slf4j
@SpringBootTest(classes = GulimallProductApplication.class)
class GulimallProductApplicationTests {


    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;

    @Test
    void contextLoads() {
        Long[] catelogPath = categoryService.findCatelogPath(225L);
        log.info("完整路径:{}", Arrays.asList(catelogPath));
    }

}
