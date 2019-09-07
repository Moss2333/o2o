package cn.azzhu.o2o.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class Product {
    @TableId(value = "product_id",type = IdType.AUTO)
    private Integer productId;

    private String productName;

    private String productDesc;

    private String imgAddr;

    private String normalPrice;

    private String promotionPrice;

    private Integer priority;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    @JsonIgnore //返回json数据可以忽略该属性值
    private Date lastEditTime;

    private Integer enableStatus;

    private Integer point;

    private Integer productCategoryId;

    private Integer shopId;


}