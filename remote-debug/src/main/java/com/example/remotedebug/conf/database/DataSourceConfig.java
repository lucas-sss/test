package com.example.remotedebug.conf.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 数据库连接池配置
 *
 * @author liuwei liuwei@flk.com
 * @version 1.0
 * @date 18-4-2
 */
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;


    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    /**
     * 配置自定义数据源
     *
     * @return
     */
    @Bean
    @Primary
    public DataSource createDruidDataSourceRoute(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(this.dbUrl);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);

        return druidDataSource;
    }

}
