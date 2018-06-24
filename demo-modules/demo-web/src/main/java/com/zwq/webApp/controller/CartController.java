package com.zwq.webApp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zwq.dto.OrderDataDTO;
import com.zwq.dto.Result;
import com.zwq.moduleService.CartService;
import com.zwq.webApp.util.MapUtil;
import com.zwq.pojo.Order;
import com.zwq.pojo.OrderItem;
import com.zwq.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * created by zwq on 2018/5/7
 */
@Controller
@RequestMapping("/cart")
public class CartController {


    @Reference
    private CartService cartService;


    @PostMapping("/{id}/{count}/cartItem")
    @ResponseBody
    public Result<Map<Integer, Integer>> addCartItem(HttpServletRequest request, @PathVariable("id") Integer id, @PathVariable("count") Integer count) {
        Map<Integer, Integer> map = MapUtil.getMap(request);
        Result<Map<Integer, Integer>> result = cartService.addCartItem(map,id,count);
        if (result.isResult()) {
            request.getSession().setAttribute("map",result.getData());
        }
        return result;
    }

    @GetMapping("")
    public String showCart(Model model, HttpServletRequest request) {
        Map<Integer, Integer> map = MapUtil.getMap(request);
        if (map == null || map.size() == 0) {
            String message = "您的购物车还是空的，赶紧行动吧！";
            model.addAttribute("message", message);
            return "cart";
        }
        List<OrderItem> orderItems = cartService.getCartItems(map);
        model.addAttribute("ois", orderItems);
        return "cart";
    }

    @PostMapping("/{id}/delete")
    @ResponseBody
    public boolean deleteCartItem(@PathVariable("id") Integer id, HttpServletRequest request) {
        Map<Integer, Integer> map =MapUtil.getMap(request);
        map.remove(id);
        HttpSession session = request.getSession();
        session.setAttribute("map",map);
        return true;
    }

    /*
    confirmOrder页面传值这里采取的是seesion传值，还可以考虑用ajax传值。
     */
    @PostMapping("/submitOrder")
    public String confirmOrder(OrderDataDTO orderDataDTO, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Order order = cartService.getOrderByOrderData(orderDataDTO);
        model.addAttribute("sum", order.getSum());
        model.addAttribute("ois", order.getOrderItems());
        session.setAttribute("order", order);
        return "confirmOrder";

    }



    @PostMapping("/confirmOrder/check")
    @SuppressWarnings("unchecked")
    @ResponseBody
    public Result<Order> submitOrder(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Order order = (Order) session.getAttribute("order");
        Result<Order> result = cartService.submitOrder(user,order);
        if (result.isResult()) {
            session.removeAttribute("order");
            session.removeAttribute("map");
        }
        return result;
    }

}
