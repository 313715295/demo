package com.zwq.parent.ModulesService;

import com.zwq.pojo.Order;

import java.util.List;

/**
 * created by zwq on 2018/6/20
 */
public interface OrderModulesService {
    /**
     * 添加订单至数据库，低层交由dao层的orderservice处理
     * @param order
     */
    void addOrder(Order order);

    /**
     * 监听处理订单消息队列，获取消息并反序列获得订单数据
     * @param data
     */
    void addOrderWithMessage(byte[] data);

    /**
     * 根据id查找订单详细数据
     * @param id
     * @return
     */
    Order selectOrderByIdWithAll(int id);

    /**
     * 根据用户id查找订单详细数据
     * @param uid
     * @return
     */
    List<Order> seletcOrdersByUser(int uid);
}
