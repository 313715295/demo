package com.zwq.moduleService;



import com.zwq.dto.ProductNameCheck;
import com.zwq.dto.Result;
import com.zwq.pojo.Order;
import com.zwq.pojo.Tea;
import com.zwq.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * created by zwq on 2018/6/4
 */
public interface AdminService {
    /**
     * 用作商品名校验，传入商品名和当前是否有商品id来判断是新增商品还是编辑商品
     * @param productNameCheck
     * @return
     */
    Result productNameCheck(ProductNameCheck productNameCheck);

    /**
     * 传入编辑的商品信息，根据是否有id判断是新增还是编辑。
     * 根据传入文件数组的长度来判断是否有修改图标，新增商品前端强制传入图标
     *
     * @param tea
     * @param bytes 上传文件的数组
     * @return
     */
    Result<Tea> productEditor(Tea tea, byte[] bytes);

    /**
     * 返回一个map存储的键值对为用户所对应的所有订单
     * @return
     */
    Map<User, List<Order>> getAllOrders();

    /**
     * 返回所有商品信息
     * @return
     */
    List<Tea> getProducts();


    Result deleteProduct(int id);
}
