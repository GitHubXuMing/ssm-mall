package com.ssm.mall.action;

import com.ssm.mall.common.Const;
import com.ssm.mall.common.Result;
import com.ssm.mall.common.ServerRes;
import com.ssm.mall.dao.pojo.User;
import com.ssm.mall.service.iservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserAction {
    @Autowired
    UserService userService;
    //1.1登录
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public @ResponseBody  ServerRes<User> login(
            @RequestParam String username,
            @RequestParam(value = "password",required = true) String password, HttpSession session){
        ServerRes<User> result = userService.login(username,password);
        if(result.getStatus() == Result.LOGIN_SUCCESS.getStatus()){
            session.setAttribute(Const.CURRENT_USER,result.getData());
        }
        return result;
    }
    //1.2注销
    @RequestMapping(value = "logout.do",method = RequestMethod.GET)
    public @ResponseBody ServerRes logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerRes.success(Result.LOGOUT_SUCCESS);
    }

    //1.3用户注册
    @RequestMapping(value = "regist.do",method = RequestMethod.POST)
    public @ResponseBody ServerRes registUser(User user){
        return userService.registry(user);
    }

    //1.4获取已登录的用户信息
    @RequestMapping(value = "getUserInfo.do",method = RequestMethod.POST)
    public @ResponseBody ServerRes<User> getLoginedUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user != null){
            //如果session中存在CURRENT_USER，表示用户已登录，返回session中的用户信息即可
            return ServerRes.success(Result.RESULT_SUCCESS,user);
        }
        return ServerRes.error(Result.NEED_LOGIN);
    }

    //1.5用户忘记密码，获得密码重置的预设问题


}
