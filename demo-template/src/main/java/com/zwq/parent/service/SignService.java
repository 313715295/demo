package com.zwq.parent.service;

import com.zwq.parent.domain.User;
import com.zwq.parent.dto.dto.Result;

/**
 * created by zwq on 2018/6/5
 */
public interface SignService {

    Result<User> logChecking(User user);

    Result<User> registerChecking(String name);

    Result<User> changePassword(User user);

    Result<User> register(User user);
}
