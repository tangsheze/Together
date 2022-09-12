package com.dg.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 数据库手动装配
 *
 * @author ty
 */
@Configuration
public class DataBaseConfig {

    @Value("${spring.datasource.url}")
    private String mysqlUrl;

    @Value("${spring.datasource.username}")
    private String mysqlUserName;

    @Value("${spring.datasource.password}")
    private String mysqlPassword;

    @Value("${spring.datasource.driver-class-name}")
    private String mysqlDriverClassName;

    private static final String MAPPER_LOCATION = "classpath*:mapper/**/*.xml";

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(mysqlUrl);
        dataSource.setUsername(mysqlUserName);
        dataSource.setPassword(mysqlPassword);
        dataSource.setDriverClassName(mysqlDriverClassName);
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

    @Bean(name = "sqlSessionFactory")
    @Primary
    @DependsOn("dataSource")
    public SqlSessionFactory dataSourceFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        GlobalConfig config = new GlobalConfig();
        config.setMetaObjectHandler(new MybatisObjectHandler());
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        config.setDbConfig(dbConfig);
        factoryBean.setGlobalConfig(config);
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return factoryBean.getObject();

    }

    /**
     * 模板
     */
    @Bean("sqlSessionTemplate")
    @Primary
    @DependsOn("sqlSessionFactory")
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("sqlSessionFactory") SqlSessionFactory sessionFactory) {
        return new SqlSessionTemplate(sessionFactory);
    }

    /**
     * 事务
     */
    @Bean(name = "TransactionManager")
    @Primary
    @DependsOn("dataSource")
    public DataSourceTransactionManager fawkesTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
