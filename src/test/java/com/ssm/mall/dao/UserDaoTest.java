package com.ssm.mall.dao;

import com.ssm.mall.dao.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserDaoTest {
    @Autowired
    SqlSessionFactory sessionFactory;
    @Test
    public void selectByPrimaryKey() {
        SqlSession sqlSession = sessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User u1 = userDao.selectByPrimaryKey(1);
        System.out.println(u1);
        User u2 = userDao.selectByPrimaryKey(1);
        System.out.println(u2);
    }
}