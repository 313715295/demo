package com.zwq.daoservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.zwq.daoservice.dao.redisClient.ProtostuffRedisClient;
import com.zwq.parent.domain.Order;
import com.zwq.parent.domain.Tea;
import com.zwq.parent.domain.User;
import com.zwq.parent.service.DaoService;
import com.zwq.parent.service.OrderSerivce;
import com.zwq.parent.service.TeaService;
import com.zwq.parent.service.UserService;
import com.zwq.parent.util.ProtoStuffUtil;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * created by zwq on 2018/6/5
 */
@Component
@Service(interfaceClass = DaoService.class)
public class DaoServiceImpl implements DaoService {
    private OrderSerivce orderSerivce;
    private UserService userService;
    private TeaService teaService;
    private ProtostuffRedisClient redisClient;

    public DaoServiceImpl(OrderSerivce orderSerivce, UserService userService, TeaService teaService, ProtostuffRedisClient redisClient) {
        this.orderSerivce = orderSerivce;
        this.userService = userService;
        this.teaService = teaService;
        this.redisClient = redisClient;
    }

    @Override
    public User selectUserById(int id) {
        return userService.select(id);
    }

    @Override
    public User addUser(User user) {
        return userService.add(user);
    }

    @Override
    public List<User> selectUsersByAutho(int autho) {
        return userService.selectByAutho(autho);
    }

    @Override
    public User selectUserByName(String name) {
        return userService.selectByName(name);
    }

    @Override
    public int updateUserPassword(User user) {
        return userService.updatePassword(user);
    }


    @Override
    public Order addOrderWithAll(Order order) {
        Order order1 = orderSerivce.addOrderWithAll(order);
        String key = "order:" + order1.getId();
        redisClient.setWithExpire(key, order1, 3600);
        int uid = order1.getUser().getId();
        String key2 = "order_uid:" + uid;
        List<Order> orders = redisClient.getList(key2, Order.class);
        if (orders == null) {
            orderSerivce.selectByUser(uid);
        } else {
            orders.add(0,order1);
            redisClient.setListWithExpire(key2,orders,3600);
        }
        return order1;
    }

    @Override
    @JmsListener(destination = "addOrder.queue")
    public void addOrderWithMessage(byte[] data) {
        Order order = ProtoStuffUtil.deserialize(data, Order.class);
        addOrderWithAll(order);

    }

    @Override
    public Order selectOrderByIdWithAll(int id) {
        return orderSerivce.selectByIdWithAll(id);
    }


    @Override
    public List<Order> seletcOrdersByUser(int uid) {
        return orderSerivce.selectByUser(uid);
    }


    @Override
    public Tea seletcProductById(int id) {
        return teaService.select(id);
    }

    @Override
    public Tea selectProductByName(String name) {
        return teaService.selectByName(name);
    }


    @Override
    public int selectProductStocksById(int id) {
        return teaService.selectStocks(id);
    }

    @Override
    public int updateProduct(Tea tea) {
        return teaService.Update(tea);
    }

    @Override
    public Tea addProduct(Tea tea) {
        return teaService.add(tea);
    }

    @Override
    public List<Tea> getProducts() {
        return teaService.listAll();
    }

    @Override
    public int deleteProductById(int id) {
        return teaService.delete(id);
    }
}
