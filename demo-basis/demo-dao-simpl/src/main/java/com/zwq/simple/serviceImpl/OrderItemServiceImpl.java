package com.zwq.simple.serviceImpl;


import com.zwq.dao.mapper.OrderItemDao;
import com.zwq.pojo.OrderItem;
import com.zwq.daoService.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by zwq on 2018/5/6
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemDao orderItemDao;
    @Autowired
    public OrderItemServiceImpl(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    @Override
    public OrderItem select(int id) {
        return null;
    }

    @Override
    public List<OrderItem> listAll() {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int add(OrderItem orderItem) {
        return 0;
    }

    @Override
    public int Update(OrderItem orderItem) {
        return 0;
    }


}
