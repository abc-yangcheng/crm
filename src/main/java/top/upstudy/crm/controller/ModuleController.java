package top.upstudy.crm.controller;

/**
 * 菜单管理
 */

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import top.upstudy.base.BaseController;
import top.upstudy.base.ResultInfo;
import top.upstudy.crm.pojo.Module;
import top.upstudy.crm.service.ModuleService;
import top.upstudy.crm.vo.ZTree;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/module")
public class ModuleController extends BaseController {

    @Resource
    private ModuleService moduleService;


    @ApiOperation("菜单管理模块")
    @GetMapping("index")
    public String index(){
        return "module/module";
    }

    @ApiOperation("查询传入角色id查询所拥有的资源")
    @PostMapping("queryAllModules")
    @ResponseBody
    public List<ZTree> queryAllModules(Integer roleId){
        return moduleService.queryAllModules(roleId);
    }


    @ApiOperation("获取所有菜单")
    @GetMapping("list")
    @ResponseBody
    public Map<String,Object> queryModules(){
        return moduleService.queryModules();
    }


}
