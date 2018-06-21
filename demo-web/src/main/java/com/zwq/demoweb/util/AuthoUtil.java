package com.zwq.demoweb.util;


import com.zwq.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * created by zwq on 2018/5/18
 */

public class AuthoUtil {

    public static void checkAutho(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //为不是管理员用户执行跳转
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getAutho() != 0) {
            response.sendRedirect("/index");
        }
    }
}
