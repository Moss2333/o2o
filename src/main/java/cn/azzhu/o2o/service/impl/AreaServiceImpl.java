package cn.azzhu.o2o.service.impl;

import cn.azzhu.o2o.entity.Area;
import cn.azzhu.o2o.mapper.AreaMapper;
import cn.azzhu.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-02 15:41:44
 */
@Service
@Transactional
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaMapper areaMapper;
    @Override
    public List<Area> getAreas() {
        //传入条件
        return areaMapper.selectList(null);
    }
}
