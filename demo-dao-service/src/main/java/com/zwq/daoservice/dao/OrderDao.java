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

    /**
     * 查询用户所有订单
     * @param uid
     * @return
     */
    List<Order> selectByUser(int uid);

    /**
     * 查询订单，包含详细数据
     * @param id
     * @return
     */
    Order selectByIdWithUser(int id);

}
