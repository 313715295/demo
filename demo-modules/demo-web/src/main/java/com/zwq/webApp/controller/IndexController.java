package com.zwq.webApp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zwq.moduleService.AdminService;
import com.zwq.pojo.Tea;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * created by zwq on 2018/5/6
 */
@Controller
public class IndexController {
    @Reference
    private AdminService adminService;


    @GetMapping(value = {"/index","/"})
    public String showIndex(Model model) {
        List<Tea> teas = adminService.getProducts();
        model.addAttribute("teas", teas);
        return "index";
    }

}
