package com.ssm.mall.service.iservice;

import com.ssm.mall.common.ServerRes;
import com.ssm.mall.dao.pojo.Product;
import com.ssm.mall.dao.vo.ProductDetailVo;

public interface ProductService {
    public ServerRes saveOrUpdate(Product product);
    public ServerRes setProductStatus(Integer id,Integer status);
    public ServerRes<ProductDetailVo> getManageDetail(Integer id);
}
