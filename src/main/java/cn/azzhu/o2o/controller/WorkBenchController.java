package cn.azzhu.o2o.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author azzhu
 * @create 2019-09-04 10:01:46
 */
@Controller
@RequestMapping("shop")
public class WorkBenchController {

    @GetMapping("workbench")
    public String workBench() {
        return "common/main";
    }
}
