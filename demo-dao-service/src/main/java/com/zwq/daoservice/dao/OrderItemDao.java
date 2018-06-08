package com.zwq.daoservice.dao;

import com.zwq.parent.domain.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * created by zwq on 2018/5/24
 */
@Mapper
public interface OrderItemDao extends Dao<OrderItem> {
    @Override
    OrderItem selectById(int id);

    @Override
    List<OrderItem> listAll();

    @Override
    int deleteById(int id);

    @Override
    int update(OrderItem orderItem);

    @Override
    int add(OrderItem orderItem);

    /**
     * 把list数据添加至orderitem数据表中
     * @param orderItems
     * @return
     */
    int addByList(List<OrderItem> orderItems);



}
