package com.zwq.adminservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.zwq.commons.enums.ExceptionEnum;
import com.zwq.commons.enums.SuccessEnum;
import com.zwq.commons.util.FileUtil;
import com.zwq.parent.ModulesService.AdminService;
import com.zwq.parent.dto.ProductNameCheck;
import com.zwq.parent.dto.Result;
import com.zwq.pojo.Order;
import com.zwq.pojo.Tea;
import com.zwq.pojo.User;
import com.zwq.service.OrderSerivce;
import com.zwq.service.TeaService;
import com.zwq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by zwq on 2018/6/5
 */
@Component
@Service(interfaceClass = AdminService.class)
public class AdminServiceImpl implements AdminService {
    private TeaService teaService;
    private UserService userService;
    private OrderSerivce orderSerivce;
    @Autowired
    public AdminServiceImpl(TeaService teaService, UserService userService, OrderSerivce orderSerivce) {
        this.teaService = teaService;
        this.userService = userService;
        this.orderSerivce = orderSerivce;
    }

    @Override
    public Result productNameCheck(ProductNameCheck product) {
        Integer id = product.getProductId();
        String name = product.getProductName();
        Tea tea = teaService.selectByName(name);
        if (tea == null) {
            return new Result<>(true, SuccessEnum.PRODUCT_NAME_SUCCESS, null);
        } else {
            if (id == null) {
                return new Result<>(false, ExceptionEnum.PRODUCT_REPEAT, null);
            } else {
                if (id.equals(tea.getId())) {
                    return new Result<>(true, SuccessEnum.PRODUCT_NAME_SUCCESS, null);
                }
                return new Result<>(false, ExceptionEnum.PRODUCT_REPEAT, null);
            }
        }
    }

    @Override
    public Result<Tea> productEditor(Tea tea, byte[] bytes) {

        String img = tea.getName() + System.currentTimeMillis() + ".jpg";
        Integer id = tea.getId();
//        String targetFilePath = "D:\\resourcesfile\\images\\";
        //linux的路径
        String targetFilePath = "/usr/local/nginx/resourcesfile/images/";
        try {
            //id不为空，代表老商品，前端不做图片强制上传要求，走更新商品程序
            if (id != null) {
                //如果有上传图片，则做图片上传操作，并且删除之前图片
                // todo 这个删除图片会导致缓存里存储的订单对应产品数据图片显示异常，等缓存更新后就可以
                if (bytes.length != 0) {
                    FileUtil.uploadFile(bytes, targetFilePath, img);
                    tea.setImg(img);
                    teaService.Update(tea);
                    //如果老图片先暂时不删除可以省略下面步骤~也不会产生缓存不同时问题
//                    Tea oldTea = teaService.select(id);
//                    String oldImg = oldTea.getImg();
//                    File oldImage = new File(targetFilePath + oldImg);
//                    oldImage.delete();
                } else {
                    teaService.updateNoImg(tea);
//
                }

                return new Result<>(true, SuccessEnum.PRODUCT_EDITOR_SUCCESS, tea);
            } else {
                //id为空则代表新增商品，前端强制上传图片，走新增商品程序
                FileUtil.uploadFile(bytes, targetFilePath, img);
                tea.setImg(img);
                teaService.add(tea);
                return new Result<>(true, SuccessEnum.PRODUCT_EDITOR_SUCCESS, tea);
            }
        } catch (DataIntegrityViolationException e) {
            return new Result<>(false, tea.getName()+ExceptionEnum.STOCKOUT.getStateInfo(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(false, ExceptionEnum.INNER_ERROR, null);
        }
    }

    @Override
    public Map<User, List<Order>> getAllOrders() {
        //筛选出普通用户
        List<User> users = userService.selectByAutho(1);
        Map<User, List<Order>> dates = new HashMap<>();
        for (User user : users) {
            List<Order> orders = orderSerivce.selectByUser(user.getId());
            if (orders.size() != 0) {
                dates.put(user, orders);
            }
        }
        return dates;
    }

    @Override
    public List<Tea> getProducts() {
        return teaService.listAll();
    }

    @Override
    public Result deleteProduct(int id) {
        Tea tea = teaService.select(id);
        String img = tea.getImg();
//        File file = new File("D:\\resourcesfile\\images\\", img);
        //linux的路径
      File file = new File("/usr/local/nginx/resourcesfile/images/", img);
        int state = teaService.delete(id);
        if (state == 0) {
            return new Result<>(false, ExceptionEnum.DELETE_FAIL, null);
        }
        file.delete();
        return new Result<>(true, SuccessEnum.DELETE_SUCCESS, null);


    }
}
