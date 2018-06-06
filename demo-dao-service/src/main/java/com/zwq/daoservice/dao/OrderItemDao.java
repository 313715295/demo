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

    int addByList(List<OrderItem> orderItems);

    List<OrderItem> selectByTea(int tid);

    OrderItem selectByIdWithTea(int id);


}
