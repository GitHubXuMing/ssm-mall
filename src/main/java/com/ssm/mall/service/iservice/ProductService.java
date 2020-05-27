package com.ssm.mall.service.iservice;

import com.github.pagehelper.PageInfo;
import com.ssm.mall.common.ServerRes;
import com.ssm.mall.dao.pojo.Product;
import com.ssm.mall.dao.vo.ProductDetailVo;

public interface ProductService {
    public ServerRes saveOrUpdate(Product product);
    public ServerRes setProductStatus(Integer id,Integer status);
    public ServerRes<ProductDetailVo> getManageDetail(Integer id);
    public ServerRes<PageInfo> getManageList(int pageNum, int pageSize);
    public ServerRes<PageInfo> searchByIdName(Integer id, String name, int pageNum, int pageSize);
}
