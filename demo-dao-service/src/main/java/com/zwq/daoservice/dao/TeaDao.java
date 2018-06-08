package com.zwq.daoservice.dao;

import com.zwq.parent.domain.OrderItem;
import com.zwq.parent.domain.Tea;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * created by zwq on 2018/5/24
 */
@Mapper
public interface TeaDao extends Dao<Tea> {

    @Override
    Tea selectById(int id);

    @Override
    List<Tea> listAll();

    @Override
    int deleteById(int id);

    @Override
    int update(Tea tea);

    int selectStocks(int id);

    @Override
    int add(Tea tea);

    /**
     * 根据所有订单项信息更新商品信息
     * @param orderItems
     * @return
     */
    int updateStocksByList(List<OrderItem> orderItems);

    Tea selectByName(String name);
}
