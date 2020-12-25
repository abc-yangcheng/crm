package top.upstudy.crm.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import top.upstudy.base.BaseController;
import top.upstudy.base.ResultInfo;
import top.upstudy.crm.pojo.CusDevPlan;
import top.upstudy.crm.query.CusDevPlanQuery;
import top.upstudy.crm.service.CusDevPlanService;
import top.upstudy.crm.service.SaleChanceService;

import javax.annotation.Resource;
import java.util.Map;

/**
 *营销管理--客户开发计划
 */

@RequestMapping("/cus_dev_plan")
@Controller
public class CusDevPlanController extends BaseController {

    @Resource
    private SaleChanceService saleChanceService;

    @Resource
    private CusDevPlanService cusDevPlanService;


    @ApiOperation("营销开发页")
    @GetMapping("index")
    public String index(){
        return "cusDevPlan/cus_dev_plan";
    }


    //获取当前用户的分配项的数据
    @ApiOperation("点击(详情/完成)弹出显示框的数据")
    @GetMapping("toCusDevPlanDataPage")
    public String toCusDevPlanDataPage(Integer sid,Model model){
        model.addAttribute("saleChance",saleChanceService.getById(sid));
        return "cusDevPlan/cus_dev_plan_data";
    }

    @ApiOperation("条件查询")
    @GetMapping("list")
    @ResponseBody
    public Map<String,Object> queryCusDevPlansByParams(CusDevPlanQuery cusDevPlanQuery){
        return cusDevPlanService.queryCusDevPlansByParams(cusDevPlanQuery);
    }


    @ApiOperation("添加计划工作")
    @PostMapping("save")
    @ResponseBody
    public ResultInfo saveCusDevPlan(CusDevPlan cusDevPlan){
        cusDevPlanService.saveCusDevPlan(cusDevPlan);
        return success("计划项数据添加成功");
    }

    @ApiOperation("更新计划项")
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateCusDevPlan(CusDevPlan cusDevPlan){
        cusDevPlanService.updateCusDevPlan(cusDevPlan);
        return success("计划项数据更新成功");
    }

    @ApiOperation("删除计划项")
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteCusDevPlan(Integer id){
        cusDevPlanService.deleteCusDevPlan(id);
        return success("计划项数据删除成功");
    }


    @ApiOperation("获得当前用户的计划数据（详情/开发选项）")
    @GetMapping("addOrUpdateCusDevPlanPage")
    public String addOrUpdateCusDevPlanPage(Integer sid, Integer id, Model model){
        //将营销机会id设置到请求域中，给计划项页面获取
        model.addAttribute("sid",sid);
        //通过计划项id获取记录
        model.addAttribute("cusDevPlan",cusDevPlanService.getById(id));
        return "cusDevPlan/add_update";
    }

}
