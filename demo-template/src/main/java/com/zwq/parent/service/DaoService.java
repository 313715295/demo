package com.zwq.parent.service;

import com.zwq.parent.domain.Order;
import com.zwq.parent.domain.OrderItem;
import com.zwq.parent.domain.Tea;
import com.zwq.parent.domain.User;

import java.util.List;

/**
 * created by zwq on 2018/6/5
 * 负责封装各DaoService方法
 */

public interface DaoService {

    /* UserDao相关方法*/

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User selectUserById(int id);

    /**
     * 添加用户
     * @param user
     * @return
     */
    User addUser(User user);

    /**
     * 返回符合权限的所有用户
     * @param autho
     * @return
     */
    List<User> selectUsersByAutho(int autho);

    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    User selectUserByName(String name);

    /**
     * 更新用户密码
     * @param user
     * @return
     */
    int updateUserPassword(User user);

    /* OrderDao相关方法*/

    /**
     * 添加订单并改动订单关联的产品数据
     * @param order
     * @return
     */
    Order addOrderWithAll(Order order);

    /**
     * 查询订单，返回订单详细数据
     * @param id
     * @return
     */
    Order selectOrderByIdWithAll(int id);

    /**
     * 查询当前用户下的所有订单
     * @param uid
     * @return
     */
    List<Order> seletcOrdersByUser(int uid);



    /* TeaDao相关方法*/

    /**
     * 查询id对应商品数据
     * @param id
     * @return
     */
    Tea seletcProductById(int id);

    /**
     * 根据商品名查询商品
     * @param name
     * @return
     */
    Tea selectProductByName(String name);

    /**
     * 查询id对应商品库存
     * @param id
     * @return
     */
    int selectProductStocksById(int id);

    /**
     * 更新商品数据
     * @param tea
     * @return
     */
    int updateProduct(Tea tea);

    /**
     * 添加商品
     * @param tea
     * @return
     */
    Tea addProduct(Tea tea);

    /**
     * 获取所有商品
     * @return
     */
    List<Tea> getProducts();

    /**
     * 删除商品
     * @param id
     * @return
     */
    int deleteProductById(int id);


}
