package com.zwq.daoservice.dao;

import java.util.List;

/**
 * created by zwq on 2018/5/24
 */
public interface Dao<T> {

    T selectById(int id);

    List<T> listAll();

    int deleteById(int id);

    int update(T t);

    int add(T t);
}
