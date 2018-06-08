package com.zwq.daoservice.service;

import com.zwq.daoservice.dao.OrderItemDao;
import com.zwq.parent.domain.OrderItem;
import com.zwq.parent.service.OrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * created by zwq on 2018/5/6
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemDao orderItemDao;

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
    public OrderItem add(OrderItem orderItem) {
        return null;
    }

    @Override
    public int Update(OrderItem orderItem) {
        return 0;
    }


}
