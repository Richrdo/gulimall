package com.timegoesby.product;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.Protocol;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.timegoesby.gulimall.product.GulimallProductApplication;
import com.timegoesby.gulimall.product.entity.BrandEntity;
import com.timegoesby.gulimall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;


@SpringBootTest(classes = GulimallProductApplication.class)
class GulimallProductApplicationTests {


    @Autowired
    BrandService brandService;

    @Autowired
    OSS ossClient;

    @Test
    public void testUpload() throws FileNotFoundException {

        // 上传文件流
        InputStream inputStream = new FileInputStream("D:\\picture\\3.png");
        ossClient.putObject("gulimall-hello-lgh","test_3.png",inputStream);

// 关闭OSSClient。
        ossClient.shutdown();

        System.out.println("上传完成");

    }


    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();

        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id",1L));
        for (BrandEntity entity : list) {
            System.out.println(entity);
        }

    }

}
