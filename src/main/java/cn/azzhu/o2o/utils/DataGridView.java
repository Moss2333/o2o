package cn.azzhu.o2o.utils;

import lombok.Data;

import java.util.List;

/**
 * 专门用于表格处理的类，用于封装json数据
 * 该类用于封装表格需要的json数据
 * @author azzhu
 * @create 2019-09-04 16:12:46
 */
@Data
public class DataGridView<T> {
    private Integer code = 0;
    private String msg = "";
    private Long count;
    private List<T> data;
}
