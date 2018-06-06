package com.zwq.daoservice.service;

import com.zwq.daoservice.dao.redisClient.ProtostuffRedisClient;
import com.zwq.daoservice.dao.OrderDao;
import com.zwq.parent.domain.Order;
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
public class OrderServiceImpl implements OrderSerivce,Serializable {

    private OrderDao orderDao;
    private ProtostuffRedisClient redisClient;

    public OrderServiceImpl(OrderDao orderDao, ProtostuffRedisClient redisClient) {
        this.orderDao = orderDao;
        this.redisClient = redisClient;
    }

    @Override
    @Transactional
    public Order add(Order order) {
        int state = orderDao.add(order);
        int id = order.getId();
        String key = "order:" + id;
        redisClient.setWithExpire(key, order, 3600);
        String key2 = "order_uid:" + order.getUser().getId();
        List<Order> orders = redisClient.getList(key2, Order.class);
        if (orders == null) {
            orders = orderDao.selectByUser(order.getUser().getId());
            if (orders == null) {
                orders = new ArrayList<>();
            }
        }
        orders.add(order);
        redisClient.setListWithExpire(key2, orders, 3600);
        return order;
    }


    @Override
    public int Update(Order order) {
        return 0;
    }

    @Override
    public Order selectByIdWithUser(int id) {
        String key = "orderWithUser:" + id;
        Order order = redisClient.get(key, Order.class);
        if (order == null) {
            order = orderDao.selectByIdWithUser(id);
            if (order != null) {
                redisClient.setWithExpire(key, order, 3600);
            }
        }
        return order;
    }

    @Override
    public List<Order> listAllWithUserAndTea() {
        String key = "orders";
        List<Order> orders = redisClient.getList(key, Order.class);
        if (orders == null) {
            orders = orderDao.listAllWithUserAndTea();
            if (orders != null&&orders.size()!=0) {
                redisClient.setListWithExpire(key, orders, 3600);
            }
        }
        return orders;
    }

    @Override
    public List<Order> selectByUser(int uid) {
        String key = "order_uid:" + uid;
        List<Order> orders = redisClient.getList(key, Order.class);
        if (orders == null) {
            orders = orderDao.selectByUser(uid);
            if (orders != null&&orders.size()!=0) {
                redisClient.setListWithExpire(key, orders, 3600);
            }
        }
        return orders;
    }


    @Override
    public Order select(int id) {
        String key = "order:" + id;
        Order order = redisClient.get(key, Order.class);
        if (order == null) {
            order = orderDao.selectById(id);
            if (order != null) {
                redisClient.setWithExpire(key, order, 3600);
            }
        }
        return order;
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
