package com.zwq.daoService;


import com.zwq.pojo.OrderItem;
import com.zwq.pojo.Tea;

import java.util.List;

/**
 * created by zwq on 2018/5/6
 */
public interface TeaService extends Service<Tea> {

    @Override
    Tea select(int id);

    Tea selectByName(String name);

    @Override
    List<Tea> listAll();

    @Override
    int delete(int id);

    @Override
    int add(Tea tea);

    /**
     * 更新产品信息，其中库存信息为旧库存+新库存，其它字段则是覆盖，图标更新
     * @param tea
     * @return
     */
    @Override
    int Update(Tea tea);

    /**
     * 更新产品信息，其中库存信息为旧库存+新库存，其它字段则是覆盖，图片不更新
     * @param tea
     * @return
     */
    int updateNoImg(Tea tea);

    /**
     * 根据订单数据更新产品库存
     * @param orderItems
     * @throws RuntimeException
     * 更新库存发生异常则抛出用以回滚事务
     */
    void UpdateStocksByList(List<OrderItem> orderItems) throws RuntimeException;

    int selectStocks(int id);


}
