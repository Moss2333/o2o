package cn.azzhu.o2o.service;

import cn.azzhu.o2o.entity.ProductCategory;
import com.github.pagehelper.PageInfo;

public interface ProductCategoryService {

    /**
     * 带条件的分页查询
     * @param productCategory
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<ProductCategory> getPage(ProductCategory productCategory, Integer pageNum, Integer pageSize);
}