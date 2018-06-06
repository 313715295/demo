package com.zwq.daoservice.dao;

import com.zwq.parent.domain.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * created by zwq on 2018/5/24
 */
@Mapper
public interface OrderDao extends Dao<Order>{

    @Override
    Order selectById(int id);

    @Override
    List<Order> listAll();

    @Override
    int deleteById(int id);

    @Override
    int update(Order order);

    @Override
    int add(Order order);

    List<Order> selectByUser(int uid);

    Order selectByIdWithUser(int id);

    List<Order> listAllWithUserAndTea();

}
