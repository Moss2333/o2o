package cn.azzhu.o2o.entity;

import lombok.Data;

import java.util.Date;
@Data
public class ShopAuth {
    private Integer shopAuthId;

    private Integer employeeId;

    private Integer shopId;

    private String name;

    private String title;

    private Integer titleFlag;

    private Date createTime;

    private Date lastEditTime;

    private Integer enableStatus;

}