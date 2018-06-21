package com.zwq.service;


import com.zwq.pojo.User;

import java.util.List;

/**
 * created by zwq on 2018/5/10
 */
public interface UserService extends Service<User> {
    @Override
    User select(int id);

    @Override
    List<User> listAll();

    @Override
    int delete(int id);

    @Override
    int add(User user);

    @Override
    int Update(User user);

    /**
     * 根据用户权限返回用户列表，0代表管理员权限，1为普通权限
     * @param autho
     * @return
     */
    List<User> selectByAutho(int autho);

    /**
     * 根据用户名查询用户信息
     * @param name
     * @return
     */
    User selectByName(String name);

    int updatePassword(User user);
}
