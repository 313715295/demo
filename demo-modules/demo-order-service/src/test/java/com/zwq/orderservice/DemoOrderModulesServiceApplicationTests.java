package com.zwq.orderservice;

import com.zwq.dao.mapper.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoOrderModulesServiceApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Autowired
	private UserDao userDao;
	@Test
	public void test() {
		Integer ud = userDao.selectIdByName("zwq1111");
		System.out.println(ud);

	}
}
