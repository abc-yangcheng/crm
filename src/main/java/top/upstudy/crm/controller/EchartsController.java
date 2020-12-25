package top.upstudy.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.upstudy.base.BaseController;
import top.upstudy.crm.pojo.Province;
import top.upstudy.crm.service.EchartsService;

import java.util.ArrayList;
import java.util.List;

/**
 * 地区分布-客户所在地
 */
@Controller
public class EchartsController extends BaseController {
    @Autowired
    EchartsService pService;

    @RequestMapping("/getdata")
    @ResponseBody
    public List<Province> showData(){
        List<Province> provinceArrayList = new ArrayList<>();
        provinceArrayList = pService.selectAll();
        return provinceArrayList;
    }

    @GetMapping("echarts/echarts")
    public String echarts(){
        return "echarts/echarts";
    }

}
