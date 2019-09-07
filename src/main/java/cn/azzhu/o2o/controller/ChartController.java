package cn.azzhu.o2o.controller;

import cn.azzhu.o2o.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("shop")
public class ChartController {

    @Autowired
    private ChartService chartService;
    @GetMapping("top5.html")
    public String toList() {
        return "top5";
    }

    @GetMapping("top5")
    @ResponseBody
    public Object getTop5Product(@RequestParam(value = "month",required = false) String month,
                                 @RequestParam(value = "startTime",required = false) String startTime,
                                 @RequestParam(value = "endTime",required = false) String endTime){
        Map<String,String> map=new HashMap<>();
        map.put("month",month);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        List<Map<String, Object>> top5Product = chartService.getTop5Product(map);
        return top5Product;
    }

    @GetMapping("orderStatus.html")
    public String toOrderStatus() {
        return "orderStatus";
    }
    @GetMapping("orderStatus")
    @ResponseBody
    public Object getOrderStatus(@RequestParam(value = "month",required = false) String month,
                                 @RequestParam(value = "startTime",required = false) String startTime,
                                 @RequestParam(value = "endTime",required = false) String endTime){
        Map<String,String> map=new HashMap<>();
        map.put("month",month);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        Map<String,Integer>  orderStatus = chartService.getOrderStatus(map);
        return orderStatus;
    }
    @GetMapping("money.html")
    public String toMoney() {
        return "money";
    }
    @GetMapping("money")
    @ResponseBody
    public Object getMoney(@RequestParam(value = "month",required = false) String month,
                                 @RequestParam(value = "startTime",required = false) String startTime,
                                 @RequestParam(value = "endTime",required = false) String endTime){
        Map<String,String> map=new HashMap<>();
        map.put("month",month);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        List<Map<String,Object>>  money = chartService.getMoney(map);
        return money;
    }
}
