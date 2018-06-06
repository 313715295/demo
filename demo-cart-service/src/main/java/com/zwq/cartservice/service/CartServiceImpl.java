package com.zwq.cartservice.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.zwq.parent.domain.Order;
import com.zwq.parent.domain.OrderItem;
import com.zwq.parent.domain.Tea;
import com.zwq.parent.domain.User;
import com.zwq.parent.dto.dto.OrderData;
import com.zwq.parent.dto.dto.Result;
import com.zwq.parent.dto.dto.TeaData;
import com.zwq.parent.enums.CartEnum;
import com.zwq.parent.service.CartService;
import com.zwq.parent.service.DaoService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * created by zwq on 2018/6/4
 */
@Component
@Service(interfaceClass = CartService.class)
public class CartServiceImpl implements CartService {

    @Reference
    private DaoService daoService;


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
        int stocks = daoService.selectProductStocksById(id);
        if (stocks < newCount) {
            result = new Result<>(false, CartEnum.STOCKOUT, null);
        } else {
            map.put(id, newCount);
            result = new Result<>(true, CartEnum.SUCCESS, map);

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
            Tea tea = daoService.seletcProductById(id);
            orderItem.setTea(tea);
            orderItem.setCount(count);
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    @Override
    public Order getOrderByOrderData(OrderData orderData) {
        List<TeaData> teaDatas = orderData.getTeaDatas();
        List<OrderItem> orderItems = new ArrayList<>();
        for (TeaData teaData : teaDatas) {
            OrderItem orderItem = new OrderItem();
            int count = teaData.getCount();
            int tid = teaData.getTid();
            Tea tea = daoService.seletcProductById(tid);
            orderItem.setTea(tea);
            orderItem.setCount(count);
            orderItems.add(orderItem);
        }
        int totalMoney = orderData.getTotalMoney();
        Order order = new Order();
        order.setSum(totalMoney);
        order.setOrderItems(orderItems);
        return order;

    }

    @Override
    @Transactional
    public Result<Order> submitOrder(User user, Order order) {

        if (user == null) {
            return new Result<>(false, "登录超时", null);
        }
        if (order == null) {
            return new Result<>(false, "数据失效", null);
        }
        order.setUser(user);
        order.setCreatTime(new Date());
        List<OrderItem> orderItems = order.getOrderItems();
        List<String> TeaNames = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            Tea tea = orderItem.getTea();
            int oldCount = tea.getStocks();
            int count = orderItem.getCount();
            int newCount = oldCount - count;
            tea.setStocks(newCount);
            if (newCount < 0) {
                TeaNames.add(tea.getName());
            }
        }
        if (TeaNames.size() > 0) {
            String message = TeaNames + "库存不足";
            return new Result<>(false, message, null);
        } else {
            Order order1 = daoService.addOrder(order);
            for (OrderItem orderItem : orderItems) {
                orderItem.setOid(order1.getId());
            }
            daoService.updateProductStocksByOrderItems(orderItems);
            daoService.addOrderItemByOrderItems(orderItems);
            return new Result<>(true, "成功提交订单", order1);

        }
    }

}
