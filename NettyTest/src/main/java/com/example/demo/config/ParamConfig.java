package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/6/6
 */
@Service
public class ParamConfig {

    @Value("${msg.server.port}")
    private Integer port;

    @Value("${msg.server.host}")
    private String host;

    @Value("${msg.client.count}")
    private Integer count;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
