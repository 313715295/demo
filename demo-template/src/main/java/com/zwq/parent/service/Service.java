package com.zwq.parent.service;

import java.util.List;

/**
 * created by zwq on 2018/5/6
 */
public interface Service<T> {

    T select(int id);

    List<T> listAll();

    int delete(int id);

    T add(T t);

    int Update(T t);
}
