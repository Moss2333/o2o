package cn.azzhu.o2o.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class ShopAuthMap {
    @TableId(value = "shop_auth_id",type = IdType.AUTO)
    private Integer shopAuthId;

    private Integer employeeId;

    private Long shopId;

    private String name;

    private String title;

    private Integer titleFlag;

    private Date createTime;

    private Date lastEditTime;

    private Integer enableStatus;

}