package com.zww.activity.config;

import com.alibaba.druid.pool.DruidDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * flow的配置
 *
 * @author fengshuonan
 * @date 2017-12-02 23:18
 */
@Configuration
@ConfigurationProperties(prefix = ZwwProperties.GUNS_ZWW_DATASOURCE)
public class ZwwProperties {

    public static final String GUNS_ZWW_DATASOURCE = "guns.zww.datasource";

    //默认多数据源的链接
    @Value("${guns.zww.datasource.url}")
    private String url = "jdbc:mysql://rm-uf6528juob1nalin3o.mysql.rds.aliyuncs.com:3306/zww?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";

    //默认多数据源的数据库账号
    @Value("${guns.zww.datasource.username}")
    private String username = "zww_app_user";

    //默认多数据源的数据库密码
    @Value("${guns.zww.datasource.password}")
    private String password = "nxCpVYcnqkrsA6dbqtZV8NUIr3gvtVEk";

    public void config(DruidDataSource dataSource) {
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
