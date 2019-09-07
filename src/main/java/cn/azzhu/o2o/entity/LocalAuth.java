package cn.azzhu.o2o.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class LocalAuth {
    @TableId(value = "local_auth_id",type = IdType.AUTO)
    private Integer localAuthId;

    private Long userId;

    private String userName;

    private String password;

    private Date createTime;

    private Date lastEditTime;

}