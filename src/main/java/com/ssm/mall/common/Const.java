package com.ssm.mall.common;

import com.google.common.collect.Sets;

import java.util.Set;

public interface Const {
    String CURRENT_USER = "current_user";
    String TOKEN_PREFIX = "token_";//设定token令牌前缀
    String STANDARD_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
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
    interface Product{
        Integer ON_SALE = 1;
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_asc","price_desc");
    }
    interface Cart{
        Integer CHECKED = 1;//选中
        Integer UNCHECKED = 0;//未选中
        String QUANTITY_OUT_OF_STOCK = "购买数量超出库存数量";
        String QUANTITY_SUCCESS = "库存充足";
    }
}
