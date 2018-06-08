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
    public String submitOrder(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
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
            } else if (!order.getUser().getId().equals(user.getId())) {
                response.sendRedirect("/index");
                return null;
            }
            model.addAttribute("Order", order);
            return "showOrder";
        }
    }


    @GetMapping("/{uid}/orderCenter")
    public String showOrder(@PathVariable("uid") int uid, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
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
