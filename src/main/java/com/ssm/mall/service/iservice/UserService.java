package com.ssm.mall.service.iservice;

import com.ssm.mall.common.ServerRes;
import com.ssm.mall.dao.pojo.User;

public interface UserService {
    /**
     *
     * @param username
     * @param password
     * @return
     */
    public ServerRes<User> login(String username,String password);
}
