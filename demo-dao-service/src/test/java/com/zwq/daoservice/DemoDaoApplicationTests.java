package com.zwq.daoservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zwq.daoservice.dao.redisClient.ProtostuffRedisClient;
import com.zwq.parent.domain.Order;
import com.zwq.parent.domain.OrderItem;
import com.zwq.parent.domain.Tea;
import com.zwq.parent.domain.User;
import com.zwq.parent.service.DaoService;
import com.zwq.parent.service.OrderSerivce;
import com.zwq.parent.service.TeaService;
import com.zwq.parent.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoDaoApplicationTests {

//	@Autowired
//	private OrderSerivce orderSerivce;
//	@Autowired
//    private UserService userService;
    @Autowired
    private TeaService teaService;
//

	@Test
	public void contextLoads() {
//        User user = userService.select(2);
//        Tea tea = teaService.select(4);
//        OrderItem orderItem = new OrderItem();
//        orderItem.setCount(5);
//        orderItem.setTea(tea);
//        List<OrderItem> orderItems = new ArrayList<>();
//        orderItems.add(orderItem);
//        Order order = new Order();
//        order.setCreatTime(new Date());
//        Instant instant = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));
//        System.out.println(instant);
//        order.setUser(user);
//        order.setOrderItems(orderItems);
//        order.setSum(199);
//        orderSerivce.addOrderWithAll(order);

	}

}
