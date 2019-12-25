package com.ssm.mall.service.iservice;

import com.ssm.mall.common.ServerRes;
import com.ssm.mall.dao.pojo.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 添加目录
     * @param categoryName
     * @param parentId
     * @return
     */
    public ServerRes addCategory(String categoryName,Integer parentId);

    /**
     * 更新目录
     * @param categoryId
     * @param categoryName
     * @return
     */
    ServerRes updateCategory(Integer categoryId, String categoryName);

    /**
     * 获得指定parentId的所有平级子目录
     * @param parentId
     * @return
     */
    ServerRes<List<Category>> childrenCategory(Integer parentId);

}
