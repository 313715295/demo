package com.zwq.demoweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zwq.demoweb.util.AuthoUtil;
import com.zwq.parent.ModulesService.AdminService;
import com.zwq.parent.dto.ProductNameCheck;
import com.zwq.parent.dto.Result;
import com.zwq.pojo.Order;
import com.zwq.pojo.Tea;
import com.zwq.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * created by zwq on 2018/5/18
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Reference
    private AdminService adminService;


    @PostMapping("/productAdmin/productNameCheck")
    @ResponseBody
    public Result productNameCheck(ProductNameCheck product) {
        return adminService.productNameCheck(product);
    }

    @PostMapping("/productAdmin/productEditor")
    @ResponseBody
    public Result<Tea> productEditor(Tea tea, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();

        return adminService.productEditor(tea, bytes);
    }

    @GetMapping("/ordersAdmin")
    public String getAllOrders(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        AuthoUtil.checkAutho(request, response);
        Map<User, List<Order>> dates = adminService.getAllOrders();
        model.addAttribute("dates", dates);
        return "orderAdmin";
    }

    @GetMapping("/productAdmin")
    public String getProducts(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        AuthoUtil.checkAutho(request, response);
        List<Tea> teas = adminService.getProducts();
        model.addAttribute("teas", teas);
        return "productAdmin";
    }

    @GetMapping("")
    public String admin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AuthoUtil.checkAutho(request, response);
        return "admin";
    }


    @PostMapping("/productAdmin/{id}/delete")
    @ResponseBody
    public Result deleteProduct(@PathVariable("id") int id) {
        return adminService.deleteProduct(id);
    }


}
