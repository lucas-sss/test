package com.example.demo;

import com.example.demo.domain.User;
import com.example.demo.service.IUserservice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.example.demo.dao")
public class RedistestApplicationTests {

	@Autowired
	private IUserservice userservice;


	@Test
	public void contextLoads() {

		User oneById = userservice.findOneById(1L);
		System.out.println(oneById);
	}

}
