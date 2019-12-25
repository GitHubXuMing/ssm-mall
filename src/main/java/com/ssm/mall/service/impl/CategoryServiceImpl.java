package com.ssm.mall.service.impl;

import com.ssm.mall.common.Result;
import com.ssm.mall.common.ServerRes;
import com.ssm.mall.dao.CategoryDao;
import com.ssm.mall.dao.pojo.Category;
import com.ssm.mall.service.iservice.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDao categoryDao;

    @Override
    public ServerRes addCategory(String categoryName,Integer parentId) {
        if(StringUtils.isBlank(categoryName) || parentId == null){
            return ServerRes.error(Result.ILLEAGLE_ARGUMENT);
        }
        Category category = new Category(categoryName,parentId);
        category.setStatus(true);
        int addFlag = categoryDao.insert(category);
        if(addFlag > 0){
            return ServerRes.success(Result.CATEGORY_ADD_SUCCESS);
        }
        return ServerRes.error(Result.CATEGORY_ADD_ERROR);
    }

    @Override
    public ServerRes updateCategory(Integer categoryId, String categoryName) {
        if(StringUtils.isBlank(categoryName) || categoryId == null){
            return ServerRes.error(Result.ILLEAGLE_ARGUMENT);
        }
        Category category = new Category(categoryId,categoryName);
        int addFlag = categoryDao.updateByPrimaryKeySelective(category);
        if(addFlag > 0){
            return ServerRes.success(Result.CATEGORY_UPDATE_SUCCESS);
        }
        return ServerRes.error(Result.CATEGORY_UPDATE_ERROR);
    }

    /**
     * 根据parentId获取所有子节点集合
     * @param parentId
     * @return
     */
    @Override
    public ServerRes<List<Category>> childrenCategory(Integer parentId) {
        if(parentId == null){
            return ServerRes.error(Result.ILLEAGLE_ARGUMENT);
        }
        List<Category> childrenCategory = categoryDao.selectByParentId(parentId);
        if(childrenCategory == null || childrenCategory.size() == 0){
            return ServerRes.error(Result.RESULT_ERROR);
        }
        return ServerRes.success(Result.RESULT_SUCCESS,childrenCategory);
    }


}
