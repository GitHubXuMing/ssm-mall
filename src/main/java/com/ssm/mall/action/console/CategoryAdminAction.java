package com.ssm.mall.action.console;

import com.ssm.mall.common.Const;
import com.ssm.mall.common.Result;
import com.ssm.mall.common.ServerRes;
import com.ssm.mall.dao.pojo.User;
import com.ssm.mall.service.iservice.CategoryService;
import com.ssm.mall.service.iservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
@Controller
@RequestMapping(value = "category")
public class CategoryAdminAction {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value = "add.do",method = RequestMethod.POST)
    public@ResponseBody  ServerRes addCategory(@RequestParam(value = "parentId",defaultValue = "0") Integer parentId,
            String categoryName,HttpSession session){
        //验证是否已登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){return ServerRes.error(Result.NEED_LOGIN);}
        //验证用户权限
        if(user.getRole() != Const.Role.ADMIN){return ServerRes.error(Result.ADMIN_LOGIN_ERROR);}
        //执行添加操作
        return categoryService.addCategory(categoryName,parentId);
    }
}
