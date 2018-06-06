package com.zwq.daoservice.config;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;
import com.zwq.parent.domain.Order;
import com.zwq.parent.domain.OrderItem;
import com.zwq.parent.domain.Tea;
import com.zwq.parent.domain.User;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * created by zwq on 2018/6/5
 */
public class SerializationOptimizerImpl implements SerializationOptimizer{
    @Override
    public Collection<Class> getSerializableClasses() {
        List<Class> classes = new LinkedList<>();
        classes.add(Tea.class);
        classes.add(User.class);
        classes.add(Order.class);
        classes.add(OrderItem.class);
        return classes;
    }
}
