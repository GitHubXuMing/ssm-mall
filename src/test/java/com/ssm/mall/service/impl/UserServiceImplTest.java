package com.ssm.mall.service.impl;

import com.ssm.mall.common.ServerRes;
import com.ssm.mall.dao.pojo.User;
import com.ssm.mall.service.iservice.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    public void login() {
        ServerRes<User> sr1 = userService.login("scott","tiger");
        System.err.println("********************"+sr1);
        ServerRes<User> sr2 = userService.login("scott11","tiger");
        System.err.println("********************"+sr2);
        ServerRes<User> sr3 = userService.login("scott","tiger11");
        System.err.println("********************"+sr3);

    }
}