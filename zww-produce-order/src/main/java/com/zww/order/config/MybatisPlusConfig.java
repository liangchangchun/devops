package com.zww.order.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.stylefeng.guns.core.datascope.DataScopeInterceptor;
import com.stylefeng.guns.core.datasource.DruidProperties;
import com.stylefeng.guns.core.mutidatasource.DSEnum;
import com.stylefeng.guns.core.mutidatasource.DynamicDataSource;
import com.stylefeng.guns.core.mutidatasource.config.MutiDataSourceProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * MybatisPlus配置
 *
 * @author stylefeng
 * @Date 2017/5/20 21:58
 */
@Configuration
@EnableTransactionManagement(order = 2)//由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
//@MapperScan(basePackages = {"com.stylefeng.guns.modular.*.dao", "com.stylefeng.guns.common.persistence.dao"})
@MapperScan(basePackages = {"com.zww.base.dao"})
public class MybatisPlusConfig {


    
    @Autowired
    ZwwProperties zwwProperties;

    /**
     * biz数据源
     */
    private DruidDataSource bizDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
      //  druidProperties.config(dataSource);
        zwwProperties.config(dataSource);
        return dataSource;
    }
    /**
     * zww数据源
     */
    private DruidDataSource zwwDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
      //  druidProperties.config(dataSource);
        zwwProperties.config(dataSource);
        return dataSource;
    }


    /**
     * 单数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "guns", name = "muti-datasource-open", havingValue = "true")
    public DruidDataSource singleDatasource() {
        return zwwDataSource();
    }

    /**
     * 多数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "guns", name = "muti-datasource-open", havingValue = "false")
    public DynamicDataSource mutiDataSource() {

        DruidDataSource bizDataSource = bizDataSource();
        DruidDataSource zwwDataSource = zwwDataSource();
        try {
            bizDataSource.init();
            zwwDataSource.init();
        }catch (SQLException sql){
            sql.printStackTrace();
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> hashMap = new HashMap();
        hashMap.put(DSEnum.DATA_SOURCE_BIZ, bizDataSource);
        hashMap.put(DSEnum.DATA_SOURCE_ZWW, zwwDataSource);
        dynamicDataSource.setTargetDataSources(hashMap);
        return dynamicDataSource;
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 数据范围mybatis插件
     */
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }
}
