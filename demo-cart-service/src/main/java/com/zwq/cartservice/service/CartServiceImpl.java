package com.zwq.cartservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.zwq.commons.enums.ExceptionEnum;
import com.zwq.commons.enums.SuccessEnum;
import com.zwq.commons.exception.StockOutException;
import com.zwq.commons.serializer.ProtoStuffSerializer;
import com.zwq.parent.ModulesService.CartService;
import com.zwq.parent.dto.OrderDataDTO;
import com.zwq.parent.dto.Result;
import com.zwq.parent.dto.TeaData;
import com.zwq.pojo.Order;
import com.zwq.pojo.OrderItem;
import com.zwq.pojo.Tea;
import com.zwq.pojo.User;
import com.zwq.service.TeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;

/**
 * created by zwq on 2018/6/4
 */
@Component
@Service(interfaceClass = CartService.class)
public class CartServiceImpl implements CartService {
    private static final String ADD_ORDER_QUEUE = "addOrder.queue";

    private JmsMessagingTemplate jms;
    private TeaService teaService;

    @Autowired
    public CartServiceImpl(JmsMessagingTemplate jms, TeaService teaService) {
        this.jms = jms;
        this.teaService = teaService;
    }

    @Override
    public Result<Map<Integer, Integer>> addCartItem(Map<Integer, Integer> map, int id, int count) {
        Result<Map<Integer, Integer>> result;
        int oldCount;
        if (map == null) {
            map = new HashMap<>();
        }
        if (map.get(id) == null) {
            oldCount = 0;
        } else {
            oldCount = map.get(id);
        }
        int newCount = oldCount + count;
        int stocks = teaService.selectStocks(id);
        if (stocks < newCount) {
            result = new Result<>(false, ExceptionEnum.STOCKOUT, null);
        } else {
            map.put(id, newCount);
            result = new Result<>(true, SuccessEnum.ADD_CARTITEM_SUCCESS, map);

        }
        return result;
    }

    @Override
    public List<OrderItem> getCartItems(Map<Integer, Integer> map) {
        Set<Integer> ids = map.keySet();
        List<OrderItem> orderItems = new ArrayList<>();
        for (Integer id : ids) {
            OrderItem orderItem = new OrderItem();
            Integer count = map.get(id);
            Tea tea = teaService.select(id);
            orderItem.setTea(tea);
            orderItem.setCount(count);
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    @Override
    public Order getOrderByOrderData(OrderDataDTO orderDataDTO) {
        List<TeaData> teaDatas = orderDataDTO.getTeaDatas();
        List<OrderItem> orderItems = new ArrayList<>();
        for (TeaData teaData : teaDatas) {
            OrderItem orderItem = new OrderItem();
            int count = teaData.getCount();
            int tid = teaData.getTid();
            Tea tea = teaService.select(tid);
            orderItem.setTea(tea);
            orderItem.setCount(count);
            orderItems.add(orderItem);
        }
        int totalMoney = orderDataDTO.getTotalMoney();
        Order order = new Order();
        order.setSum(totalMoney);
        order.setOrderItems(orderItems);
        return order;

    }

    @Override
    public Result<Order> submitOrder(User user, Order order) {
        if (user == null) {
            return new Result<>(false, ExceptionEnum.LOGIN_TIMEOUT, null);
        }
        if (order == null) {
            return new Result<>(false, ExceptionEnum.DATA_FAILURE, null);
        }
        order.setUser(user);
        Instant instant = Instant.now();
        order.setCreatTime(instant);
        List<OrderItem> orderItems = order.getOrderItems();
        try {
            //把订单项数据先做入库处理，如果因为库存入库失败，捕捉该异常并抛出
            teaService.UpdateStocksByList(orderItems);
        } catch (StockOutException e) {
            return new Result<>(false, e.getMessage(), null);
        }
        //如果入库成功，则把订单数据发送至处理订单入库的消息队列中，返回下单成功提示
        sendMsgToAddOrder(ADD_ORDER_QUEUE, order);
        return new Result<>(true, SuccessEnum.ADD_ORDER_SUCCESS, null);


    }

    @Override
    public void sendMsgToAddOrder(String destinationName, Object o) {
        byte[] data = ProtoStuffSerializer.serialize(o);
        jms.convertAndSend(destinationName, data);
    }

}
