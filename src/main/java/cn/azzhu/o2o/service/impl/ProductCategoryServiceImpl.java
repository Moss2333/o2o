package cn.azzhu.o2o.service.impl;

import cn.azzhu.o2o.entity.ProductCategory;
import cn.azzhu.o2o.mapper.ProductCategoryMapper;
import cn.azzhu.o2o.service.ProductCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryMapper categoryMapper;
    @Override
    public PageInfo<ProductCategory> getPage(ProductCategory productCategory, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ProductCategory> list = categoryMapper.selectList(new QueryWrapper<>());
        return new PageInfo<>(list);
    }
}