package com.example.remotedebug;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.remotedebug.dao")
public class RemoteDebugApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemoteDebugApplication.class, args);
	}
}
