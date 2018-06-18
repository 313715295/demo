package com.zwq.signservice.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.zwq.parent.domain.User;
import com.zwq.parent.dto.Result;
import com.zwq.parent.service.DaoService;
import com.zwq.parent.service.SignService;
import org.springframework.stereotype.Component;

/**
 * created by zwq on 2018/6/5
 */
@Component
@Service(interfaceClass = SignService.class)
public class SignServiceIpml implements SignService{

    @Reference
    private DaoService daoService;


    @Override
    public Result<User> logChecking(User user) {

        User user1 = daoService.selectUserByName(user.getName());
        if (user1 != null&&user.getPassword().equals(user1.getPassword())) {
            return new Result<>(true,"登录成功",user1);
        }
        return new Result<>(false,"账号或密码错误",null);

    }

    @Override
    public Result<User> registerChecking(String name) {
        User user = daoService.selectUserByName(name);
        if (user != null) {
            return new Result<>(false, "账号已存在", null);
        }
        return new Result<>(true, "账号可以使用", null);

    }


    @Override
    public Result<User> changePassword(User user) {

        int state = daoService.updateUserPassword(user);
        if (state == 1) {
            return new Result<>(true, "修改密码成功", user);
        } else {
            return new Result<>(false, "修改密码失败", null);
        }

    }

    @Override
    public Result<User> register(User user) {
        try {
            User user1 = daoService.addUser(user);
            return new Result<>(true, "注册成功", user1);
        } catch (Exception e) {
            return new Result<>(false, "系统异常", null);
        }
    }
}
