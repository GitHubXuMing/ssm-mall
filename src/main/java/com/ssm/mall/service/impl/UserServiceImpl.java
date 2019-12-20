package com.ssm.mall.service.impl;

import com.ssm.mall.common.Result;
import com.ssm.mall.common.ServerRes;
import com.ssm.mall.dao.UserDao;
import com.ssm.mall.dao.pojo.User;
import com.ssm.mall.service.iservice.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public ServerRes<User> login(String username, String password) {
        //判断用户名是否存在
        int countFlag = userDao.checkUsername(username);
        if(countFlag < 1){
            return ServerRes.error(Result.USER_NOT_EXISTS);
        }
        //判断密码是否匹配
        //TODO: 将获得的密码进行MD5加密
        User user = userDao.login(username,password);
        if(user == null){
            return ServerRes.error(Result.PASSWORD_ERROR);
        }
        //登录成功，返回用户信息(需要将用户密码清空)
        user.setPassword(StringUtils.EMPTY);
        return ServerRes.success(Result.LOGIN_SUCCESS,user);
    }
}
