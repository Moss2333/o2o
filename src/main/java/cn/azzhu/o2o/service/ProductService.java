package cn.azzhu.o2o.service;


import cn.azzhu.o2o.entity.Product;
import cn.azzhu.o2o.utils.DataGridView;
import cn.azzhu.o2o.vo.ProductVo;
import com.github.pagehelper.PageInfo;

/**
 * 封装Product相关操作的接口
 * @author azzhu
 * @create 2019-09-04 16:20:50
 */
public interface ProductService {


    /**
     * 不带查询条件的分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    DataGridView getDataGridView(Integer pageNum, Integer pageSize);
    /**
     * 带条件的分页查询
     * @param pageNum
     * @param pageSize
     * @param productVo
     * @return
     */
    PageInfo<Product> getPage(Integer pageNum, Integer pageSize, ProductVo productVo);

    /**
     * 删除、下架、上架、批量删
     * @param product
     */
   public void deleteProductById(Product product);
    public void downProductById(Product product);
    public void upProductById(Product product);
    public  void deleteProductByIds(String[] ids);

    /**
     * 新增、修改
     * @param product
     * @return
     */
   public void addProduct(Product product);
    public void updateProduct(Product product);


}

