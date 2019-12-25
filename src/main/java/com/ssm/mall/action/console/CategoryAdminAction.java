package com.ssm.mall.action.console;

import com.ssm.mall.common.Const;
import com.ssm.mall.common.Result;
import com.ssm.mall.common.ServerRes;
import com.ssm.mall.dao.pojo.Category;
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
import java.util.List;

@Controller
@RequestMapping(value = "category")
public class CategoryAdminAction {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 判断管理员权限并添加商品种类
     *
     * @param parentId
     * @param categoryName
     * @param session
     * @return
     */
    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    public @ResponseBody
    ServerRes addCategory(@RequestParam(value = "parentId", defaultValue = "0") Integer parentId,
                          String categoryName, HttpSession session) {
        //验证是否已登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerRes.error(Result.NEED_LOGIN);
        }
        //验证用户权限
        if (user.getRole() != Const.Role.ADMIN) {
            return ServerRes.error(Result.ADMIN_LOGIN_ERROR);
        }
        //执行添加操作
        return categoryService.addCategory(categoryName, parentId);
    }

    /**
     * 判断管理员权限，修改品类的名称
     * @param categoryId
     * @param categoryName
     * @param session
     * @return
     */
    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    public @ResponseBody
    ServerRes updateCategory(Integer categoryId, String categoryName, HttpSession session) {
        //验证是否已登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerRes.error(Result.NEED_LOGIN);
        }
        //验证用户权限
        if (user.getRole() != Const.Role.ADMIN) {
            return ServerRes.error(Result.ADMIN_LOGIN_ERROR);
        }
        //执行更新操作
        return categoryService.updateCategory(categoryId, categoryName);
    }

    /**
     * 获得指定parentId的所有平级子节点
     * @param parentId
     * @param session
     * @return
     */
    @RequestMapping(value = "children.do", method = RequestMethod.POST)
    public @ResponseBody
    ServerRes<List<Category>> childrenCategory(Integer parentId,HttpSession session) {
        //验证是否已登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerRes.error(Result.NEED_LOGIN);
        }
        //验证用户权限
        if (user.getRole() != Const.Role.ADMIN) {
            return ServerRes.error(Result.ADMIN_LOGIN_ERROR);
        }
        //执行查询操作
        return categoryService.childrenCategory(parentId);
    }


}
