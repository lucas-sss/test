package com.example.XmlTest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
@MapperScan("com.example.XmlTest.dao")
public class XmlTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlTestApplication.class, args);
	}
}
