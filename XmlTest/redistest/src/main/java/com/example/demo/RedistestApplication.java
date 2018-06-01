package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 启动类
 *
 * 	EnableTransactionManagement 启动mybatis事务注解功能
 * 	EnableScheduling 启动定时任务注解
 *
 * @author liuwei
 * @version 1.0
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@MapperScan("com.example.demo.dao")
public class RedistestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedistestApplication.class, args);
	}
}
