package com.zwq.daoservice;

import com.zwq.parent.domain.Order;
import com.zwq.parent.service.DaoService;
import com.zwq.parent.service.OrderSerivce;
import com.zwq.parent.util.DateFormatUtil;
import com.zwq.parent.util.ProtoStuffUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoDaoApplicationTests {
//	@Autowired
//	private OrderSerivce orderSerivce;


	@Test
	public void contextLoads() {
//		Instant instant = Instant.now();
//		Order order = new Order();
//		order.setCreatTime(instant);
//
//		System.out.println(orderSerivce.add(order));
	}

}
