package cn.azzhu.o2o.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author azzhu
 * @create 2019-09-04 08:48:39
 */
@Data
public class LogInfo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String loginname;
    private String loginip;
    private Date logintime;
}
