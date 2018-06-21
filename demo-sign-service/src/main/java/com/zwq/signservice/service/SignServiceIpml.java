package com.zwq.signservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.zwq.commons.enums.ExceptionEnum;
import com.zwq.commons.enums.SuccessEnum;
import com.zwq.parent.ModulesService.SignService;
import com.zwq.parent.dto.Result;
import com.zwq.pojo.User;
import com.zwq.service.UserService;
import org.springframework.stereotype.Component;

/**
 * created by zwq on 2018/6/5
 */
@Component
@Service(interfaceClass = SignService.class)
public class SignServiceIpml implements SignService{

    private UserService userService;

    public SignServiceIpml(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Result<User> logChecking(User user) {

        User user1 = userService.selectByName(user.getName());
        if (user1 != null&&user.getPassword().equals(user1.getPassword())) {
            return new Result<>(true, SuccessEnum.LOGIN_SUCCESS,user1);
        }
        return new Result<>(false, ExceptionEnum.LOGIN_FAIL,null);

    }

    @Override
    public Result registerChecking(String name) {
        User user = userService.selectByName(name);
        if (user != null) {
            return new Result<>(false, ExceptionEnum.USERNAME_REPEAT, null);
        }
        return new Result<>(true, SuccessEnum.USERNAME_SUCCESS, null);

    }


    @Override
    public Result changePassword(User user) {

        int state = userService.updatePassword(user);
        if (state == 1) {
            return new Result<>(true, SuccessEnum.EDITOR_PASSWORD_SUCCESS, null);
        } else {
            return new Result<>(false, ExceptionEnum.EDITOR_PASSWORD_FAIL, null);
        }

    }

    @Override
    public Result<User> register(User user) {
        try {
            userService.add(user);
            return new Result<>(true, SuccessEnum.REGISTER_SUCCESS, user);
        } catch (Exception e) {
            return new Result<>(false, ExceptionEnum.INNER_ERROR, null);
        }
    }
}
