package com.example.emailtest;

import com.example.emailtest.utils.EmailHtmlUtil;
import com.example.emailtest.utils.EmailLauncher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailtestApplicationTests {


	@Test
	public void contextLoads() {
	}

	@Test
	public void sendTest() throws Exception {
		String test = EmailHtmlUtil.test();
		EmailLauncher.send(test);

	}


}
