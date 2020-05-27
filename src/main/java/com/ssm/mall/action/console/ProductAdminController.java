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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/product")
public class ProductAdminController {
    @Autowired
    ProductService productService;
    @ResponseBody
    @RequestMapping(value = "/save.do",method= RequestMethod.POST) //新增商品
    public ServerRes save(HttpSession session, Product product){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //判断是否已经登录并具有管理员权限
        if(user == null || user.getRole() != Const.Role.ADMIN){
            return ServerRes.error(Result.NEED_ADMIN_LOGIN);
        }
        return productService.saveOrUpdate(product);
    }
    @RequestMapping(value="/update.do",method=RequestMethod.POST)//更新商品
    @ResponseBody
    public ServerRes update(HttpSession session, Product product){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //判断是否已经登录并具有管理员权限
        if(user == null || user.getRole() != Const.Role.ADMIN){
            return ServerRes.error(Result.NEED_ADMIN_LOGIN);
        }
        return productService.saveOrUpdate(product);
    }
    @RequestMapping(value="/updateStatus.do",method = RequestMethod.POST)//更新商品状态（上架或下架）
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

    @RequestMapping(value="/productDetail.do",method=RequestMethod.POST)//管理页面：根据商品id查询产品详情
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

    @RequestMapping(value = "/list.do",method=RequestMethod.POST)//管理界面：分页查询商品列表
    @ResponseBody
    public ServerRes getManageList(HttpSession session,
                                   @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                   @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //判断是否已经登录并具有管理员权限
        if(user == null || user.getRole() != Const.Role.ADMIN){
            return ServerRes.error(Result.NEED_ADMIN_LOGIN);
        }
        return productService.getManageList(pageNum,pageSize);
    }


    @RequestMapping(value="/search.do",method = RequestMethod.POST)//管理界面：根据id、name搜索商品(name为模糊查询 )
    @ResponseBody
    public ServerRes search(HttpSession session,
                                   @RequestParam(value = "id",required = false) Integer id,
                                   @RequestParam(value = "name",required = false) String name,
                                   @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                   @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //判断是否已经登录并具有管理员权限
        if(user == null || user.getRole() != Const.Role.ADMIN){
            return ServerRes.error(Result.NEED_ADMIN_LOGIN);
        }
        return productService.searchByIdName(id,name,pageNum,pageSize);
    }
}
