package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2020/4/6
 */

@Configuration
@MapperScan(basePackages = {"com.example.demo.application.query.proforma.invoice.mapper", "com.example.demo.infrastructure.database.proforma.invoice"}, sqlSessionFactoryRef = "baseSqlSessionFactory", sqlSessionTemplateRef = "baseSqlSessionTemplate")
public class DruidOneConfig {

    @Primary
    @Bean(initMethod = "init", name = "dataSourceOne")
    @ConfigurationProperties("spring.datasource.druid.one")
    public DruidDataSource dataSourceOne(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name="baseSqlSessionFactory")
    @Primary
    public SqlSessionFactory baseSqlSessionFactory(@Qualifier("dataSourceOne")DruidDataSource dataSource) throws Exception{
        // 创建Mybatis的连接会话工厂实例
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**/*Mapper*.xml"));
        bean.setDataSource(dataSource);
        GlobalConfiguration globalConfiguration = new GlobalConfiguration();
        globalConfiguration.setDbColumnUnderline(true);
        globalConfiguration.setIdType(IdType.AUTO.getKey());
//        globalConfiguration.setMetaObjectHandler(new MetaHandler());
        bean.setGlobalConfig(globalConfiguration);
        MybatisConfiguration configuration = new MybatisConfiguration ();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        configuration.setCacheEnabled(false);
        configuration.setCallSettersOnNulls(true);
        bean.setConfiguration(configuration);
        return bean.getObject();

    }

    @Bean(name="baseSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate baseSqlSessionTemplate(@Qualifier("baseSqlSessionFactory")SqlSessionFactory sqlSessionFactory) throws Exception{
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return  template;
    }

    @Bean(name = "transactionManagerOne")
    @Primary
    public DataSourceTransactionManager transactionManagerOne(@Qualifier("dataSourceOne")DruidDataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
