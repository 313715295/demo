package com.zwq.dao.mapper;

import com.zwq.pojo.OrderItem;
import com.zwq.pojo.Tea;

import java.util.List;

/**
 * created by zwq on 2018/5/24
 */
public interface TeaDao extends Dao<Tea> {

    @Override
    Tea selectById(int id);

    @Override
    List<Tea> listAll();

    @Override
    int deleteById(int id);

    /**
     * 图标也更新
     * @param tea
     * @return
     */
    @Override
    int update(Tea tea);

    /**
     * 图标不更新
     * @param tea
     * @return
     */
    int updateNoImg(Tea tea);


    int selectStocks(int id);



    @Override
    int add(Tea tea);



    /**
     * 根据订单项更新产品库存
     * @param orderItem
     * @return
     */
    int updateStocksByOrderItem(OrderItem orderItem);

    Tea selectByName(String name);
}
