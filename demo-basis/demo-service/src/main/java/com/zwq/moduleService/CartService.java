package com.zwq.moduleService;


import com.zwq.dto.OrderDataDTO;
import com.zwq.dto.Result;
import com.zwq.pojo.Order;
import com.zwq.pojo.OrderItem;
import com.zwq.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * created by zwq on 2018/6/4
 */
public interface CartService {
    /**
     * 把加入购物车的商品信息加入map中存入session
     * @param map
     * @param id
     * @param count
     * @return
     */
    Result<Map<Integer, Integer>> addCartItem(Map<Integer, Integer> map, int id, int count);

    /**
     * 获取session中存储的所有加入购物车的商品数据
     * @param map
     * @return
     */
    List<OrderItem> getCartItems(Map<Integer, Integer> map);

    /**
     * 根据总金额以及包含的购买信息，返回一个订单数据
     * @param orderDataDTO
     * @return
     */
   Order getOrderByOrderData(OrderDataDTO orderDataDTO);

    /**
     * 传入用户信息判断是否登录超时；
     * 提交订单数据并存入数据库中
     * @param user
     * @param order
     * @return
     */
    Result<Order> submitOrder(User user, Order order);

    /**
     * 向消息队列传输一个对象。
     * @param destinationName
     * @param o
     */
    void sendMsgToAddOrder(String destinationName, Object o);
}
