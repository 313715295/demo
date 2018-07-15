package com.zwq.orderService;

import com.alibaba.dubbo.config.annotation.Service;
import com.zwq.commons.serializer.ProtoStuffSerializer;
import com.zwq.moduleService.OrderModulesService;
import com.zwq.pojo.Order;
import com.zwq.daoService.OrderSerivce;
import com.zwq.simple.redisClient.ProtostuffRedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * created by zwq on 2018/6/21
 */
@Component
@Service(interfaceClass = OrderModulesService.class)
public class OrderModulesServiceImpl implements OrderModulesService {
    private OrderSerivce orderSerivce;
    private ProtostuffRedisClient redisClient;
    @Autowired
    public OrderModulesServiceImpl(OrderSerivce orderSerivce, ProtostuffRedisClient redisClient) {
        this.orderSerivce = orderSerivce;
        this.redisClient = redisClient;
    }

    @Override
    public void addOrder(Order order) {
        orderSerivce.addOrderWithAll(order);
        //暂时先不考虑订单入库中的异常了~ 订单添加后开始做缓存更新
        String key = "order:" + order.getId();
        redisClient.setWithExpire(key, order, 3600);
        int uid = order.getUser().getId();
        String key2 = "order_uid:" + uid;
        List<Order> orders = redisClient.getList(key2, Order.class);
        if (orders == null) {
            orderSerivce.selectByUser(uid);
        } else {
            //最新订单添加至最前
            orders.add(0,order);
            //这个方式是更新key的缓存数据
            redisClient.setListWithExpire(key2,orders,3600);
            //redisClient.lPush(key,order); todo 这个是把数据插入缓存最前，不用更新该key所有数据。有待研究，
        }
    }

    @Override
    @JmsListener(destination = "addOrder.queue")
    public void addOrderWithMessage(byte[] data) {
       Order order=  ProtoStuffSerializer.deserialize(data, Order.class);
       addOrder(order);
    }
    @Override
    public Order selectOrderByIdWithAll(int id) {
        return orderSerivce.selectByIdWithAll(id);
    }
    @Override
    public List<Order> seletcOrdersByUser(int uid) {
        return orderSerivce.selectByUser(uid);
    }
}
