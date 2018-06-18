package com.zwq.adminservice.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.zwq.parent.domain.Order;
import com.zwq.parent.domain.Tea;
import com.zwq.parent.domain.User;
import com.zwq.parent.dto.ProductNameCheck;
import com.zwq.parent.dto.Result;
import com.zwq.parent.service.AdminService;
import com.zwq.parent.service.DaoService;
import com.zwq.parent.util.FileUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by zwq on 2018/6/5
 */
@Component
@Service(interfaceClass = AdminService.class)
public class AdminServiceImpl implements AdminService {
    @Reference
    private DaoService daoService;
    @Override
    public Result productNameCheck(ProductNameCheck product) {
        Integer id = product.getProductId();
        String name = product.getProductName();
        Tea tea = daoService.selectProductByName(name);
        if (tea == null) {
            return new Result<>(true, "", null);
        } else {
            if (id == null) {
                return new Result<>(false, "商品已存在", null);
            } else {
                if (id.equals(tea.getId())) {
                    return new Result<>(true, "", null);
                }
                return new Result<>(false, "商品已存在", null);
            }
        }
    }

    @Override
    @Transactional
    public Result<Tea> productEditor(Tea tea, byte[] bytes) {

        String img = tea.getName() + System.currentTimeMillis() + ".jpg";
        Integer id = tea.getId();
        int addStocks = tea.getStocks();
        String targetFilePath = "D:\\resourcesfile\\images\\";
        //linux的路径
//        String targetFilePath = "/usr/local/nginx/resourcesfile/images/";
        try {
            if (id != null) {
                Tea oldTea = daoService.seletcProductById(id);
                int oldStocks = oldTea.getStocks();
                tea.setStocks(oldStocks + addStocks);
                String oldImg = oldTea.getImg();
                if (bytes.length != 0) {
                    tea.setImg(img);
                    FileUtil.uploadFile(bytes, targetFilePath, img);
                    File oldImage = new File(targetFilePath + oldImg);
                    oldImage.delete();
                } else {
                    tea.setImg(oldImg);
                }
                daoService.updateProduct(tea);
                return new Result<>(true, "提交成功", tea);
            } else {
                tea.setImg(img);
                Tea tea1=daoService.addProduct(tea);
                FileUtil.uploadFile(bytes, targetFilePath, img);
                return new Result<>(true, "提交成功", tea1);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<>(false, "提交失败", tea);
        }
    }

    @Override
    public Map<User, List<Order>> getAllOrders() {
        //筛选出普通用户
        List<User> users = daoService.selectUsersByAutho(1);
        Map<User, List<Order>> dates = new HashMap<>();
        for (User user1 : users) {
            List<Order> orders = daoService.seletcOrdersByUser(user1.getId());
            if (orders.size() != 0) {
                dates.put(user1, orders);
            }
        }
        return dates;
    }

    @Override
    public List<Tea> getProducts() {
        return daoService.getProducts();
    }

    @Override
    @Transactional
    public Result deleteProduct(int id) {
        Tea tea = daoService.seletcProductById(id);
        String img = tea.getImg();
        File file = new File("D:\\resourcesfile\\images\\", img);
        //linux的路径
//      File file = new File("/usr/local/nginx/resourcesfile/images/", img);
        int state = daoService.deleteProductById(id);
        if (state == 0) {
            return new Result<>(false, "删除失败", null);
        }
        if (file.delete()) {
            return new Result<>(true, "删除成功", null);
        }
        return  new Result<>(false, "删除失败", null);

    }
}
