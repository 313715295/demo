package com.zwq.parent.service;


import com.zwq.parent.domain.Order;
import com.zwq.parent.domain.OrderItem;
import com.zwq.parent.domain.User;
import com.zwq.parent.dto.dto.OrderData;
import com.zwq.parent.dto.dto.Result;

import java.util.List;
import java.util.Map;

/**
 * created by zwq on 2018/6/4
 */
public interface CartService {

    Result<Map<Integer, Integer>> addCartItem(Map<Integer, Integer> map, int id, int count);

    List<OrderItem> getCartItems(Map<Integer, Integer> map);

   Order getOrderByOrderData(OrderData orderData);

    Result<Order> submitOrder(User user, Order order);
}
