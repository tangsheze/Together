package com.dg.common.dataBase;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.Getter;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 数据库手动装配
 *
 * @Author TheFool
 */
@Getter
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = {"com.dg.**.dao"})
public class DataBaseConfig {

    @Value("${spring.datasource.url}")
    String mysqlUrl;

    @Value("${spring.datasource.username}")
    String mysqlUserName;

    @Value("${spring.datasource.password}")
    String mysqlPassword;

    @Value("${spring.datasource.driver-class-name}")
    String mysqlDriverClassName;

    private static final String MAPPER_LOCATION = "classpath*:mapper/**/*.xml";

    @Autowired
    private PaginationInterceptor paginationInterceptor;

    @Bean(name = "sqlSessionFactory")
    public MybatisSqlSessionFactoryBean sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(dataSource());
        mybatisPlus.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        Interceptor[] plugins = {paginationInterceptor};
        mybatisPlus.setPlugins(plugins);
        return mybatisPlus;
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(getMysqlUrl());
        dataSource.setUsername(getMysqlUserName());
        dataSource.setPassword(getMysqlPassword());
        dataSource.setDriverClassName(getMysqlDriverClassName());
        dataSource.setMaxActive(100);
        dataSource.setMaxWait(10000);
        dataSource.setMinIdle(0);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(false);
        dataSource.setTestOnReturn(false);
        dataSource.setInitialSize(1);
        dataSource.setMinEvictableIdleTimeMillis(1000 * 60 * 10);
        dataSource.setTimeBetweenEvictionRunsMillis(60 * 1000);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        dataSource.setValidationQuery("SELECT 1 FROM DUAL");
        return dataSource;
    }

    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
