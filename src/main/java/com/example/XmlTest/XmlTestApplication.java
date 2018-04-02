package com.example.XmlTest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import java.io.File;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
@MapperScan("com.example.XmlTest.dao")
public class XmlTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmlTestApplication.class, args);
    }

}
