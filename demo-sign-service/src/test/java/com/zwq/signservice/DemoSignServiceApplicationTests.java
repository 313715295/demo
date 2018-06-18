package com.zwq.signservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zwq.parent.domain.User;
import com.zwq.parent.dto.Result;
import com.zwq.parent.service.SignService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSignServiceApplicationTests {

	@Reference
	private SignService signService;
	@Test
	public void contextLoads() {
		User user = new User();
		user.setName("zwq");
		user.setPassword("910810");
		Result<User> result =signService.logChecking(user);
		System.out.println(result.getData());
	}

}
