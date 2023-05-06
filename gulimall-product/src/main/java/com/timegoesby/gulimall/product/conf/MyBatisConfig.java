package com.timegoesby.gulimall.product.conf;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement //开启事务支持
@MapperScan("com.timegoesby.gulimall.product.dao")
public class MyBatisConfig {

    // 引入分页插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();

        // 设置请求的页面大于最大页后操作，true调回到首页，false继续请求 默认false
        paginationInnerInterceptor.setOverflow(true);
        //设置最大单页限制数量，默认500条，-1不受限制
        paginationInnerInterceptor.setMaxLimit(500L);
        // 设置数据库类型
        paginationInnerInterceptor.setDbType(DbType.MYSQL);

        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);

        return mybatisPlusInterceptor;
    }


}
