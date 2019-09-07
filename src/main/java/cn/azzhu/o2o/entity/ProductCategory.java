package cn.azzhu.o2o.entity;

import lombok.Data;

import java.util.Date;
@Data
public class ProductCategory {
    private Integer productCategoryId;

    private String productCategoryName;

    private String productCategoryDesc;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    private Integer shopId;

}