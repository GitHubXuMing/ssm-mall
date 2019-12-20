package com.ssm.mall.common;

public interface Const {
    String CURRENT_USER = "current_user";
    //确定验证类型是用户名，还是email
    interface ValidType{
        String USERNAME = "username";
        String EMAIL = "email";
    }
    //确定用户角色是否是管理员，默认为1-USER
    interface Role{
        Integer ADMIN = 0;
        Integer USER = 1;
    }
}
