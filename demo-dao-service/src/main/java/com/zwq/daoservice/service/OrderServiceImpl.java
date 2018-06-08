package com.zwq.daoservice.service;

import com.zwq.daoservice.dao.OrderItemDao;
import com.zwq.daoservice.dao.TeaDao;
import com.zwq.daoservice.dao.UserDao;
import com.zwq.daoservice.dao.redisClient.ProtostuffRedisClient;
import com.zwq.daoservice.dao.OrderDao;
import com.zwq.parent.domain.Order;
import com.zwq.parent.domain.OrderItem;
import com.zwq.parent.service.OrderSerivce;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * created by zwq on 2018/5/11
 */
@Service
public class OrderServiceImpl implements OrderSerivce {

    private OrderDao orderDao;
    private ProtostuffRedisClient redisClient;
    private OrderItemDao orderItemDao;
    private TeaDao teaDao;

    public OrderServiceImpl(OrderDao orderDao, ProtostuffRedisClient redisClient, OrderItemDao orderItemDao, TeaDao teaDao) {
        this.orderDao = orderDao;
        this.redisClient = redisClient;
        this.orderItemDao = orderItemDao;
        this.teaDao = teaDao;
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
    public Order addOrderWithAll(Order order) {
        orderDao.add(order);
        int id = order.getId();
        int uid = order.getUser().getId();
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setOid(id);
        }
        orderItemDao.addByList(orderItems);
        teaDao.updateStocksByList(orderItems);
        String key = "order:" + id;
        redisClient.setWithExpire(key, order, 3600);
        String key2 = "order_uid:" + uid;
        List<Order> orders = redisClient.getList(key2, Order.class);
        if (orders == null) {
            orders = orderDao.selectByUser(uid);
            if (orders.size()==1) {
                orders = new ArrayList<>();
            }
        }
        orders.add(order);
        redisClient.setListWithExpire(key2, orders, 3600);
        return order;
    }

    @Override
    public List<Order> selectByUser(int uid) {
        String key = "order_uid:" + uid;
        List<Order> orders = redisClient.getList(key, Order.class);
        if (orders == null) {
            orders = orderDao.selectByUser(uid);
            if (orders != null && orders.size() != 0) {
                redisClient.setListWithExpire(key, orders, 3600);
            }
        }
        return orders;
    }


    @Override
    public Order add(Order order) {
        return null;
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
