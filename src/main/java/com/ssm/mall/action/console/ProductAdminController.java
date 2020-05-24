package com.ssm.mall.action.console;

import com.ssm.mall.common.Const;
import com.ssm.mall.common.Result;
import com.ssm.mall.common.ServerRes;
import com.ssm.mall.dao.pojo.Product;
import com.ssm.mall.dao.pojo.User;
import com.ssm.mall.service.iservice.ProductService;
import com.ssm.mall.dao.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/product")
public class ProductAdminController {
    @Autowired
    ProductService productService;
    @ResponseBody
    @RequestMapping("/save.do")
    public ServerRes save(HttpSession session, Product product){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //判断是否已经登录并具有管理员权限
        if(user == null || user.getRole() != Const.Role.ADMIN){
            return ServerRes.error(Result.NEED_ADMIN_LOGIN);
        }
        return productService.saveOrUpdate(product);
    }
    @RequestMapping("/update.do")
    @ResponseBody
    public ServerRes update(HttpSession session, Product product){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //判断是否已经登录并具有管理员权限
        if(user == null || user.getRole() != Const.Role.ADMIN){
            return ServerRes.error(Result.NEED_ADMIN_LOGIN);
        }
        return productService.saveOrUpdate(product);
    }
    @RequestMapping("/updateStatus.do")
    @ResponseBody
    public ServerRes updateProductStatus(HttpSession session,Integer id, Integer status){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //判断是否已经登录并具有管理员权限
        if(user == null || user.getRole() != Const.Role.ADMIN){
            return ServerRes.error(Result.NEED_ADMIN_LOGIN);
        }
        if (id == null || status == null) {
            return ServerRes.error(Result.ILLEAGLE_ARGUMENT);
        }
        return productService.setProductStatus(id,status);
    }

    @RequestMapping("/productDetail.do")
    @ResponseBody
    public ServerRes<ProductDetailVo> getManageDetail(HttpSession session, Integer id){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //判断是否已经登录并具有管理员权限
        if(user == null || user.getRole() != Const.Role.ADMIN){
            return ServerRes.error(Result.NEED_ADMIN_LOGIN);
        }
        if (id == null) {
            return ServerRes.error(Result.ILLEAGLE_ARGUMENT);
        }
        return productService.getManageDetail(id);
    }
}
