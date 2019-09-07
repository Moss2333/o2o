package cn.azzhu.o2o.service.impl;

import cn.azzhu.o2o.entity.Product;
import cn.azzhu.o2o.mapper.ProductMapper;
import cn.azzhu.o2o.service.ProductService;
import cn.azzhu.o2o.utils.DataGridView;
import cn.azzhu.o2o.vo.ProductVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-04 16:25:56
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public DataGridView getDataGridView(Integer pageNum, Integer pageSize) {
        //TODO 应该查询当前登录人能够看到的店铺下的商品列表
        //需要封装成一个PageInfo
        PageHelper.startPage(pageNum,pageSize);
        List<Product> products = productMapper.selectList(null);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        DataGridView<Product> dataGridView = new DataGridView<>();
        dataGridView.setCount(pageInfo.getTotal());
        dataGridView.setData(pageInfo.getList());
        return dataGridView;
    }

    /**
     * 带条件的分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param productVo
     * @return
     */
    @Override
    public PageInfo<Product> getPage(Integer pageNum, Integer pageSize, ProductVo productVo) {
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Product> queryWrapper=new QueryWrapper<>();
        //封装查询条件 // if(!StringUtils.isEmpty(productVo.getProductName())) { // queryWrapper.like("product_name", productVo.getProductName()); // } //上面的写法更好
        //查询商品价格
        if(productVo.getProductName()!=null&&!"".equals(productVo.getProductName())){
            queryWrapper.like("product_name",productVo.getProductName());
        }
        //时间和价格的判断：2个都填写 between...and 若只写一个 gt 或者 lt
        //查询时间,
        if(productVo.getStartTime()!=null &&!"".equals(productVo.getStartTime())
                &&productVo.getEndTime()!=null && !"".equals(productVo.getEndTime())){
            queryWrapper.between("create_time",productVo.getStartTime(),productVo.getEndTime());
        }
        //查询价格
        if(productVo.getStartPrice()!=null && !"".equals(productVo.getStartPrice())
                &&productVo.getEndPrice()!=null&&!"".equals(productVo.getEndPrice())){
            queryWrapper.between("promotion_price",productVo.getStartPrice(),productVo.getEndPrice());
        }
        ////查询全部，不包括删除的
        if(productVo.getEnableStatus()!=null&&productVo.getEnableStatus()==3){
            queryWrapper.in("enable_status",0,1);
        }
        //查询在架0或下架1的
        if(productVo.getEnableStatus()!=null&&productVo.getEnableStatus()!=3){
            queryWrapper.in("enable_status",productVo.getEnableStatus());
        }
        queryWrapper.in("enable_status",0,1);
        //当前登录人的所有shop下的商品
        queryWrapper.in("shop_id",productVo.getShopIds());
        //获取根据条件查询的商品信息
        List<Product> products=productMapper.selectList(queryWrapper);
        //返回每页的商品信息
        return new PageInfo<>(products);
    }

    @Override
    public void deleteProductById(Product product) {
        product.setEnableStatus(2);
        UpdateWrapper<Product> productUpdateWrapper = new UpdateWrapper<>();
        productUpdateWrapper.eq("product_id",product.getProductId());
        productMapper.update(product,productUpdateWrapper);
    }

    @Override
    public void downProductById(Product product) {
        product.setEnableStatus(1);
        UpdateWrapper<Product> productUpdateWrapper = new UpdateWrapper<>();
        productUpdateWrapper.eq("product_id",product.getProductId());
        productMapper.update(product,productUpdateWrapper);
    }

    @Override
    public void upProductById(Product product) {
        product.setEnableStatus(0);
        UpdateWrapper<Product> productUpdateWrapper = new UpdateWrapper<>();
        productUpdateWrapper.eq("product_id",product.getProductId());
        productMapper.update(product,productUpdateWrapper);
    }

    @Override
    public void deleteProductByIds(String[] ids) {
        Product product = new Product();
        product.setEnableStatus(2);
        UpdateWrapper<Product> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("product_id",ids);
        productMapper.update(product,updateWrapper);
    }

    /**
     * 新增、修改
     *
     * @param product
     * @return
     */
    @Override
    public void addProduct(Product product) {
         productMapper.insert(product);
    }

    @Override
    public void updateProduct(Product product) {
       productMapper.updateById(product);
    }
}
