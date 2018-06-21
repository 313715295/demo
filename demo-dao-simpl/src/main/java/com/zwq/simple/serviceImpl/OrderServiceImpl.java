package com.zwq.simple.serviceImpl;


import com.zwq.dao.mapper.OrderDao;
import com.zwq.dao.mapper.OrderItemDao;
import com.zwq.dao.mapper.TeaDao;
import com.zwq.pojo.Order;
import com.zwq.pojo.OrderItem;
import com.zwq.service.OrderSerivce;
import com.zwq.simple.redisClient.ProtostuffRedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * created by zwq on 2018/5/11
 */
@Service
public class OrderServiceImpl implements OrderSerivce {

    private OrderDao orderDao;
    private ProtostuffRedisClient redisClient;
    private OrderItemDao orderItemDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, ProtostuffRedisClient redisClient, OrderItemDao orderItemDao) {
        this.orderDao = orderDao;
        this.redisClient = redisClient;
        this.orderItemDao = orderItemDao;
    }

    @Override
    public int Update(Order order) {
        return 0;
    }

    @Override
    public Order selectByIdWithAll(int id) {
        String key = "order:" + id;
        Order order = redisClient.get(key, Order.class);
        if (order == null) {
            order = orderDao.selectByIdWithAll(id);
            if (order != null) {
                redisClient.setWithExpire(key, order, 3600);


            }
        }
        return order;
    }

    @Override
    @Transactional
    public int addOrderWithAll(Order order) {
        int state = orderDao.add(order);
        int id = order.getId();
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setOid(id);
        }
        orderItemDao.addByList(orderItems);
        //因为这个方法是事务方法，为了缩短事务时间，把更新缓存的逻辑放到业务模块中
        return state;
    }

    @Override
    public List<Order> selectByUser(int uid) {
        String key = "order_uid:" + uid;
        List<Order> orders = redisClient.getList(key, Order.class);
        if (orders == null) {
            orders = orderDao.selectByUser(uid);
            if (orders.size() != 0) {
                redisClient.setListWithExpire(key, orders, 3600);
            }
        }
        return orders;
    }


    @Override
    public int add(Order order) {

        return orderDao.add(order);
    }

    @Override
    public Order select(int id) {
        return null;
    }


    @Override
    public List<Order> listAll() {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

}
