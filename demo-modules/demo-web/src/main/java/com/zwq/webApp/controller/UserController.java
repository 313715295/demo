package com.zwq.webApp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zwq.dto.Result;
import com.zwq.moduleService.SignService;
import com.zwq.pojo.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * created by zwq on 2018/5/30
 */
@RestController
public class UserController {

    @Reference
    private SignService signService;


    @PostMapping("/login/clear")
    public boolean clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return true;
    }

    @PostMapping("/login/check")
    public Result check(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        Result<User> result = signService.logChecking(user);
        if (result.isResult()) {
            session.setAttribute("user", result.getData());
        }
        return result;
    }

    @PostMapping("/register")
    public void register(User user, HttpServletRequest request) {
        Result<User> result =signService.register(user);
        if (result.isResult()) {
            request.getSession().setAttribute("user", result.getData());
        }
    }

    @PostMapping("/register/{userName}/check")
    public Result registerCheck(@PathVariable("userName") String userName) {
        return signService.registerChecking(userName);
    }

    @PostMapping("/changePassword")
    public Result changePassword(User user, String newPassword, HttpServletRequest request) {
        user.setPassword(newPassword);
        Result result =signService.changePassword(user);
        if (result.isResult()) {
            request.getSession().removeAttribute("user");
        }
        return result;
    }


}
