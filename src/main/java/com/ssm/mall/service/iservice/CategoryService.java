package com.ssm.mall.service.iservice;

import com.ssm.mall.common.ServerRes;

public interface CategoryService {
    public ServerRes addCategory(String categoryName,Integer parentId);
}
