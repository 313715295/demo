package com.zwq.daoservice.dao;

import com.zwq.parent.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * created by zwq on 2018/5/24
 */
@Mapper
public interface UserDao extends Dao<User> {
    @Override
    User selectById(int id);


    @Override
    List<User> listAll();

    @Override
    int deleteById(int id);

    @Override
    int update(User user);

    int updatePassword(User user);

    @Override
    int add(User user);

    User selectByName(String name);

    List<User> selectByAutho(int autho);
}
