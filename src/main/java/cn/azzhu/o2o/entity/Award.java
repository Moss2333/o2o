package cn.azzhu.o2o.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Award {
    private Integer awardId;

    private String awardName;

    private String awardDesc;

    private String awardImg;

    private Integer point;

    private Integer priority;

    private Date createTime;

    private Date expireTime;

    private Date lastEditTime;

    private Integer enableStatus;

    private Integer shopId;

}