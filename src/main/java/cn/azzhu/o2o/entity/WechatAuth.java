package cn.azzhu.o2o.entity;

import lombok.Data;

import java.util.Date;
@Data
public class WechatAuth {
    private Integer wechatAuthId;

    private Integer userId;

    private String openId;

    private Date createTime;

}