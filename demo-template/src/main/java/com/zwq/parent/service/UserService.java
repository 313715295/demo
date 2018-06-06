package com.zwq.parent.service;

import com.zwq.parent.domain.User;
import com.zwq.parent.dto.dto.Result;

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
    User add(User user);

    @Override
    int Update(User user);

    List<User> selectByAutho(int autho);

    User selectByName(String name);

    int updatePassword(User user);
}
