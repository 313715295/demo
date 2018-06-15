package com.zwq.demoshow.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zwq.parent.domain.Order;
import com.zwq.parent.domain.User;
import com.zwq.parent.service.DaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * created by zwq on 2018/5/11
 */
@Controller
public class OrderController {

    @Reference
    private DaoService daoService;



    @GetMapping("/order/{id}/show")
    @SuppressWarnings("unchecked")
    public String showOrder(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/index");
            return null;
        } else {
            Order order = daoService.selectOrderByIdWithAll(id);
            if (order == null ) {
                response.sendRedirect("/index");
                return null;
//                因为注入order的User属性的时候只注入了用户名，所以对比用户名
            } else if (!order.getUser().getName().equals(user.getName())) {
                response.sendRedirect("/index");
                return null;
            }
            model.addAttribute("Order", order);
            return "showOrder";
        }
    }


    @GetMapping("/{uid}/orderCenter")
    public String showOrderCenter(@PathVariable("uid") int uid, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/index");
            return null;
        } else if (!user.getId().equals(uid)) {
            response.sendRedirect("/index");
            return null;
        } else {
            List<Order> orders = daoService.seletcOrdersByUser(uid);
            model.addAttribute("orders", orders);
            return "orderCenter";
        }
    }


}
