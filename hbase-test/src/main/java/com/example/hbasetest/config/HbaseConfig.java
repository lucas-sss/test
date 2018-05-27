package com.example.hbasetest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;


@Configuration
public class HbaseConfig {

    @Bean
    public HbaseTemplate getHbaseTemplate(){
        HbaseTemplate hbaseTemplate = new HbaseTemplate();
        org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
        configuration.set("hbase.zookeeper.quorum","127.0.0.1");
        configuration.set("hbase.zookeeper.port","2181");
        hbaseTemplate.setConfiguration(configuration);
        return hbaseTemplate;
    }


} 