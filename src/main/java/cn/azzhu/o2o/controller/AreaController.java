package cn.azzhu.o2o.controller;


import cn.azzhu.o2o.entity.Area;
import cn.azzhu.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-02 15:43:42
 */
@Controller
@RequestMapping("shop")
public class AreaController {

    @Autowired
    private AreaService areaService;

    /**
     * 返回列表数据
     * @return
     */
    @GetMapping("test")
    @ResponseBody
    public Object getAreas() {
        List<Area> areas = areaService.getAreas();
        return areas;
    }
}
