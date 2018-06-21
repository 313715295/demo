package com.zwq.simple.serviceImpl;


import com.zwq.commons.enums.ExceptionEnum;
import com.zwq.commons.exception.StockOutException;
import com.zwq.dao.mapper.TeaDao;
import com.zwq.pojo.OrderItem;
import com.zwq.pojo.Tea;
import com.zwq.service.TeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public int add(Tea tea) {

        return teaDao.add(tea);
    }

    @Override
    @Transactional
    public int Update(Tea tea) {

        return teaDao.update(tea);
    }

    @Override
    @Transactional
    public int updateNoImg(Tea tea) {
        return teaDao.updateNoImg(tea);
    }

    @Override
    @Transactional
    public void UpdateStocksByList(List<OrderItem> orderItems) throws StockOutException {
        //更新库存失败商品名存入此集合
        List<String> productNames = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            String productName = orderItem.getTea().getName();
            try {
                teaDao.updateStocksByOrderItem(orderItem);
            //库存设定为非负数，如果更新后库存为负，会抛出这个异常，ignore好像无效~有待研究
            } catch (DataIntegrityViolationException e) {
                productNames.add(productName);
            }
        }
        if (productNames.size() > 0) {
            throw new StockOutException(productNames + ExceptionEnum.STOCKOUT.getStateInfo());
        }
    }


    @Override
    public int selectStocks(int id) {
        return teaDao.selectStocks(id);
    }


}
