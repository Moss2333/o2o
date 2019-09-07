package cn.azzhu.o2o.service;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
@ResponseBody
public interface ChartService {

    public List<Map<String,Object>> getTop5Product(Map<String,String> map);

    Map<String,Integer> getOrderStatus(Map<String,String> map);

    List<Map<String,Object>> getMoney(Map<String,String> map);

}
