package com.zwq.daoservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.zwq.parent.domain.Order;
import com.zwq.parent.domain.Tea;
import com.zwq.parent.domain.User;
import com.zwq.parent.service.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * created by zwq on 2018/6/5
 */
@Component
@Service(interfaceClass = DaoService.class)
public class DaoServiceImpl implements DaoService {
    private OrderSerivce orderSerivce;
    private UserService userService;
    private TeaService teaService;

    public DaoServiceImpl(OrderSerivce orderSerivce, UserService userService, TeaService teaService) {
        this.orderSerivce = orderSerivce;
        this.userService = userService;
        this.teaService = teaService;
    }

    @Override
    public User selectUserById(int id) {
        return userService.select(id);
    }

    @Override
    public User addUser(User user) {
        return  userService.add(user);
    }

    @Override
    public List<User> selectUsersByAutho(int autho) {
        return userService.selectByAutho(autho);
    }

    @Override
    public User selectUserByName(String name) {
        return userService.selectByName(name);
    }

    @Override
    public int updateUserPassword(User user) {
        return userService.updatePassword(user);
    }



    @Override
    @Transactional
    public Order addOrderWithAll(Order order) {
        return orderSerivce.addOrderWithAll(order);
    }

    @Override
    public Order selectOrderByIdWithUser(int id) {
        return orderSerivce.selectByIdWithUser(id);
    }


    @Override
    public List<Order> seletcOrdersByUser(int uid) {
        return orderSerivce.selectByUser(uid);
    }




    @Override
    public Tea seletcProductById(int id) {
        return teaService.select(id);
    }

    @Override
    public Tea selectProductByName(String name) {
        return teaService.selectByName(name);
    }


    @Override
    public int selectProductStocksById(int id) {
        return teaService.selectStocks(id);
    }

    @Override
    public int updateProduct(Tea tea) {
        return teaService.Update(tea);
    }

    @Override
    public Tea addProduct(Tea tea) {
        return teaService.add(tea);
    }

    @Override
    public List<Tea> getProducts() {
        return teaService.listAll();
    }

    @Override
    public int deleteProductById(int id) {
        return teaService.delete(id);
    }
}
