package com.zwq.parent.service;

import com.zwq.parent.domain.Order;
import com.zwq.parent.domain.OrderItem;
import com.zwq.parent.domain.Tea;
import com.zwq.parent.domain.User;

import java.util.List;

/**
 * created by zwq on 2018/6/5
 */

public interface DaoService {

    /* UserDao相关方法*/
    User selectUserById(int id);

    User addUser(User user);

    List<User> selectUsersByAutho(int autho);

    User selectUserByName(String name);

    int updateUserPassword(User user);

    /* OrderDao相关方法*/
    Order addOrder(Order order);

    Order selectOrderByIdWithUser(int id);

    List<Order> getOrdersWithAll();

    List<Order> seletcOrdersByUser(int uid);

    Order selectOrderById(int id);

    /* OrderItemDao相关方法*/
    int addOrderItemByOrderItems(List<OrderItem> orderItems);

    /* TeaDao相关方法*/
    Tea seletcProductById(int id);

    Tea selectProductByName(String name);

    int updateProductStocksByOrderItems(List<OrderItem> orderItems);

    int selectProductStocksById(int id);

    int updateProduct(Tea tea);

    Tea addProduct(Tea tea);

    List<Tea> getProducts();

    int deleteProductById(int id);


}
