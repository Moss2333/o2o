package cn.azzhu.o2o.controller;

import cn.azzhu.o2o.entity.ProductCategory;
import cn.azzhu.o2o.service.ProductCategoryService;
import cn.azzhu.o2o.utils.DataGridView;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("shop/category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 跳转到商品列表页面
     * @return
     */
    @GetMapping("list.html")
    public String toList() {
        return "category/list";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object getList(ProductCategory productCategory,
                          @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                          @RequestParam(value = "limit",required = false,defaultValue = "5") Integer limit) {
        //应该查看当前登录人可以查看哪些店铺，进而查看其商品类目
        PageInfo<ProductCategory> pageInfo = productCategoryService.getPage(productCategory, page, limit);
        DataGridView<ProductCategory> pageResultDTO = new DataGridView<>();

        pageResultDTO.setCount(pageInfo.getTotal());
        pageResultDTO.setData(pageInfo.getList());

        return pageResultDTO;
    }
}
