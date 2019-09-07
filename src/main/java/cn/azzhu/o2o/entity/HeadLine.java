package cn.azzhu.o2o.entity;

import lombok.Data;

import java.util.Date;
@Data
public class HeadLine {
    private Integer lineId;

    private String lineName;

    private String lineLink;

    private String lineImg;

    private Integer priority;

    private Integer enableStatus;

    private Date createTime;

    private Date lastEditTime;


}