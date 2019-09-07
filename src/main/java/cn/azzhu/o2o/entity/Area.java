package cn.azzhu.o2o.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Area {
    @TableId(value = "area_id",type = IdType.AUTO)
    private Integer areaId;

    private String areaName;

    private String areaDesc;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

}