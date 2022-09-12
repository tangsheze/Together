package com.dg.common.datasource;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus手动装配
 *
 * @author ty
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setMapUnderscoreToCamelCase(true);
    }

    @Bean
    public MybatisConfiguration mybatisConfiguration() {
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        mybatisConfiguration.setMapUnderscoreToCamelCase(true);
        return mybatisConfiguration;
    }
}
