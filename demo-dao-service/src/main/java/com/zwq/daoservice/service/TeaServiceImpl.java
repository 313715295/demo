package com.zwq.daoservice.service;

import com.zwq.daoservice.dao.TeaDao;
import com.zwq.parent.domain.OrderItem;
import com.zwq.parent.domain.Tea;
import com.zwq.parent.service.TeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * created by zwq on 2018/5/6
 */
@Service
public class TeaServiceImpl implements TeaService {

    private TeaDao teaDao;

    @Autowired
    public TeaServiceImpl(TeaDao teaDao) {
        this.teaDao = teaDao;
    }

    @Override
    public Tea select(int id) {
           Tea tea = teaDao.selectById(id);
        return tea;
    }

    @Override
    public Tea selectByName(String name) {
        return teaDao.selectByName(name);
    }

    @Override
    public List<Tea> listAll() {
        return teaDao.listAll();
    }

    @Override
    @Transactional
    public int delete(int id) {
        return teaDao.deleteById(id);
    }

    @Override
    @Transactional
    public Tea add(Tea tea) {
        teaDao.add(tea);
        return tea;
    }

    @Override
    @Transactional
    public int Update(Tea tea) {

        return teaDao.update(tea);
    }


    @Override
    public int selectStocks(int id) {
        return teaDao.selectStocks(id);
    }



}
