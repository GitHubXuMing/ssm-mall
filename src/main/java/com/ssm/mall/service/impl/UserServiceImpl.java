package com.ssm.mall.service.impl;

import com.ssm.mall.common.Const;
import com.ssm.mall.common.Result;
import com.ssm.mall.common.ServerRes;
import com.ssm.mall.dao.UserDao;
import com.ssm.mall.dao.pojo.User;
import com.ssm.mall.service.iservice.UserService;
import com.ssm.mall.util.MD5Util;
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
        if (countFlag < 1) {
            return ServerRes.error(Result.USER_NOT_EXISTS);
        }
        //判断密码是否匹配
        User user = userDao.login(username, MD5Util.MD5EncodeUtf8(password));
        if (user == null) {
            return ServerRes.error(Result.PASSWORD_ERROR);
        }
        //登录成功，返回用户信息(需要将用户密码清空)
        user.setPassword(StringUtils.EMPTY);
        return ServerRes.success(Result.LOGIN_SUCCESS, user);
    }

    @Override
    public ServerRes checkValid(String validName, String type) {
        if (StringUtils.isNotBlank(type)) {
            if (type.equals(Const.ValidType.USERNAME)) {
                int uflag = userDao.checkUsername(validName);
                if (uflag > 0) {
                    return ServerRes.error(Result.USER_ALREADY_EXIST);
                }
            } else if (type.equals(Const.ValidType.EMAIL)) {
                int eflag = userDao.checkEmail(validName);
                if (eflag > 0) {
                    return ServerRes.error(Result.EMAIL_ALREADY_EXIST);
                }
            } else {
                return ServerRes.error(Result.REGISTRY_ILLEAGEL_ARGUMENT);
            }
        }
        return ServerRes.success(Result.REGISTRY_VALID_SUCCESS);
    }

    @Override
    public ServerRes registry(User user) {
        //判断用户名是否已存在
        ServerRes uRes = this.checkValid(user.getUsername(), Const.ValidType.USERNAME);
        if (uRes.getStatus() == Result.USER_ALREADY_EXIST.getStatus()) {
            return uRes; }
        //判断EMAIL是否已存在
        ServerRes eRes = this.checkValid(user.getEmail(), Const.ValidType.EMAIL);
        if (eRes.getStatus() == Result.EMAIL_ALREADY_EXIST.getStatus()) {
            return eRes; }
        //如果用户名和EMAIL都不存在，开始执行注册
        user.setRole(Const.Role.USER);//1-设置用户的默认权限是普通用户
        String originPassword = user.getPassword();
        user.setPassword(MD5Util.MD5EncodeUtf8(originPassword));//2-对用户密码进行MD5加密
        //3-注册
        int regCount = userDao.insert(user);//思考：此处是否可以使用动态insert方法？）
        return regCount > 0 ? ServerRes.success(Result.REGISTRY_SUCCESS) : ServerRes.error(Result.REGISTRY_ERROR);
    }

    @Override
    public ServerRes<String> getQuestionByUsername(String username) {
        //判断用户名是否存在
        int uFlag = userDao.checkUsername(username);
        if(uFlag < 1){
            return ServerRes.error(Result.USER_NOT_EXISTS);
        }else{
            //如果存在，取出预设问题
            String question = userDao.getQuestionByUsername(username);
            //根据预设问题是否为空，反馈不同信息
            if(StringUtils.isNotBlank(question)) {
                return ServerRes.success(Result.RESULT_SUCCESS, question);
            }else{
                return ServerRes.error(Result.NO_PASSWORD_RESET_QUESTION);
            }
        }
    }
}
