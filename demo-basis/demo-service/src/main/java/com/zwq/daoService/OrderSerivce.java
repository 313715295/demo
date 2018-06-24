package com.zwq.daoService;


import com.zwq.pojo.Order;

import java.util.List;

/**
 * created by zwq on 2018/5/11
 *
 */
public interface OrderSerivce extends Service<Order> {

    @Override
    int add(Order order);

    Order select(int id);


    List<Order> listAll();

    int delete(int id);

    /**
     * 根据用户id查找订单详细数据
     * @param uid
     * @return
     */
    List<Order> selectByUser(int uid);

    int Update(Order order);

    Order selectByIdWithAll(int id);

    /**
     * 把订单数据更新至数据库，联动更新订单项数据
     * @param order
     * @return
     */
    int addOrderWithAll(Order order);
}
