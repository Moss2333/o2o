package cn.azzhu.o2o.service;


import cn.azzhu.o2o.entity.Area;

import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-02 15:40:43
 */
public interface AreaService {
    /**
     * 返回所有的区域列表
     * @return
     */
    List<Area> getAreas();
}
