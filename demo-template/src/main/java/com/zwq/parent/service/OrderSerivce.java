package com.zwq.parent.service;

import com.zwq.parent.domain.Order;

import java.util.List;

/**
 * created by zwq on 2018/5/11
 */
public interface OrderSerivce extends Service<Order> {

    Order add(Order order);

    Order select(int id);


    List<Order> listAll();

    int delete(int id);


    List<Order> selectByUser(int uid);

    int Update(Order order);

    Order selectByIdWithUser(int id);

    List<Order> listAllWithUserAndTea();
}
