package com.zwq.cartservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zwq.parent.domain.Order;
import com.zwq.parent.domain.User;
import com.zwq.parent.dto.dto.Result;
import com.zwq.parent.service.CartService;
import com.zwq.parent.service.DaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoCartServiceApplicationTests {

//    @Reference(interfaceClass = DaoService.class)
//    private DaoService daoService;
    @Test
    public void contextLoads() {

//        User user = daoService.selectUserById(2);
//        Order order = new Order();
//        order.setUser(user);
//        order.setSum(1000);
//        Order order1=daoService.addOrder(order);
//        System.out.println(order);
//        System.out.println(order1);
    }

}
