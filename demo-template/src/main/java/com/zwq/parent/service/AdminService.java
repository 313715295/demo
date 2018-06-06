package com.zwq.parent.service;

import com.zwq.parent.domain.Order;
import com.zwq.parent.domain.Tea;
import com.zwq.parent.domain.User;
import com.zwq.parent.dto.dto.ProductNameCheck;
import com.zwq.parent.dto.dto.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * created by zwq on 2018/6/4
 */
public interface AdminService {

    Result productNameCheck(ProductNameCheck productNameCheck);

    Result<Tea> productEditor(Tea tea, byte[] bytes,String fileName);

    Map<User, List<Order>> getAllOrders();

    List<Tea> getProducts();

    Result deleteProduct(int id);
}
