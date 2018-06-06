package com.zwq.parent.service;


import com.zwq.parent.domain.OrderItem;
import com.zwq.parent.domain.Tea;

import java.util.List;
import java.util.Map;

/**
 * created by zwq on 2018/5/6
 */
public interface TeaService extends Service<Tea> {


    Tea select(int id);

    Tea selectByName(String name);

    List<Tea> listAll();

    int delete(int id);

    Tea add(Tea tea);

    int Update(Tea tea);

    void updateStocks(Tea tea);

    boolean updatePrice(Map<String, Object> map);

    int selectStocks(int id);

    int updateStocksByList(List<OrderItem> orderItems);


}
