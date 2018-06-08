package com.zwq.daoservice.service;

import com.zwq.daoservice.dao.redisClient.ProtostuffRedisClient;
import com.zwq.daoservice.dao.UserDao;
import com.zwq.parent.domain.User;
import com.zwq.parent.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * created by zwq on 2018/5/10
 */
@Service
public class UserServiceImpl implements UserService{

    private UserDao userDao;
    private ProtostuffRedisClient redisClient;

    public UserServiceImpl(UserDao userDao, ProtostuffRedisClient redisClient) {
        this.userDao = userDao;
        this.redisClient = redisClient;
    }

    @Override
    public User select(int id) {
        String key = "user:" + id;
        User user = redisClient.get(key, User.class);
        if (user == null) {
            user = userDao.selectById(id);
            if (user != null) {
                redisClient.setWithExpire(key, user, 3600);
            }
        }
        return user;
    }

    @Override
    public List<User> listAll() {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public User add(User user) {
        int state = userDao.add(user);
        String key = "user:" + user.getName();
        redisClient.setWithExpire(key, user, 3600);
        return user;
    }

    @Override
    public int Update(User user) {
        return 0;
    }

    @Override
    public List<User> selectByAutho(int autho) {
        return userDao.selectByAutho(autho);
    }


    @Override
    public User selectByName(String name) {
        String key = "user:" + name;
        User user = redisClient.get(key, User.class);
        if (user == null) {
            user = userDao.selectByName(name);
            if (user != null) {
                redisClient.setWithExpire(key, user, 3600);
            }
        }
        return user;
    }
    @Override
    public int updatePassword(User user) {
        int state = userDao.updatePassword(user);
        if (state == 1) {
            String key = "user:" + user.getName();
            redisClient.setWithExpire(key, user, 3600);
        }
        return state;
    }


}
