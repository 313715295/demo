package com.zwq.daoService;


import com.zwq.pojo.OrderItem;

import java.util.List;

/**
 * created by zwq on 2018/5/6
 */
public interface OrderItemService extends Service<OrderItem> {


    OrderItem select(int id);

    List<OrderItem> listAll();

    int delete(int id);

    int add(OrderItem orderItem);

    int Update(OrderItem orderItem);





}
