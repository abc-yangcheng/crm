package top.upstudy.crm.controller;


/**
 * 用户管理
 */

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import top.upstudy.base.BaseController;
import top.upstudy.base.ResultInfo;
import top.upstudy.crm.pojo.User;
import top.upstudy.crm.query.UserQuery;
import top.upstudy.crm.service.UserService;
import top.upstudy.crm.utils.CookieUtil;
import top.upstudy.crm.utils.LoginUserUtil;
import top.upstudy.crm.vo.loginUser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     */
    @ApiOperation("用户登录")
    @PostMapping("/user/login")
    @ResponseBody
    public ResultInfo login(String userName,String userPassword){
        //用于放置结果信息
        ResultInfo resultInfo = new ResultInfo();
        //获取结果
        loginUser loginResult = userService.login(userName, userPassword);
        //放置结果
        resultInfo.setResult(loginResult);
        //返回操作结果
        return resultInfo;
    }

    /**
     * 更新密码功能
     */
    //点击转发到对应密码更新界面
    @ApiOperation("更新密码页面")
    @GetMapping("/user/toPasswordPage")
    public String toPasswordPage(){
        return "user/password";
    }

    @ApiOperation("用户更新密码操作")
    @PostMapping("/user/updatePassword")
    @ResponseBody
    //用户更新密码，提交表单格式
    public ResultInfo updateUserInfo(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword){
        //用于放置结果信息
        ResultInfo resultInfo = new ResultInfo();

        //更新密码操作
        userService.updateUserInfo(LoginUserUtil.releaseUserIdFromCookie(request),oldPassword,newPassword,confirmPassword);

        //返回操作结果
        return resultInfo;
    }

    @ApiOperation("基本资料")
    @GetMapping("user/basic")
    public  String basic( Model model,HttpServletRequest request){
        String userName = CookieUtil.getCookieValue(request, "userName");
        model.addAttribute("user",userService.queryUsersByUserName(userName));
        return "user/add_update";
    }

    @ApiOperation("查询所有销售人员列表")
    @PostMapping("user/queryAllSales")
    @ResponseBody
    public List<Map<String,Object>> queryAllSales(){
        return userService.queryAllSales();
    }


    /**
     * 用户管理功能模块
     */
    @ApiOperation("用户管理页面")
    @GetMapping("user/index")
    public String index(){
        return "user/user";
    }

    @ApiOperation("弹出层— 编辑/添加")
    @GetMapping("user/addOrUpdateUserPage")
    public  String addOrUpdateUserPage(Integer id, Model model){

        if(id != null){
            //如果是点击编辑按钮，回显数据
            model.addAttribute("user",userService.getById(id));
        }

        return "user/add_update";
    }

    @ApiOperation("查询用户")
    @GetMapping("user/list")
    @ResponseBody
    public Map<String,Object> queryUsersByParams(UserQuery userQuery){
        return  userService.queryUsersByParams(userQuery);
    }

    @ApiOperation("添加用户")
    @PostMapping("user/save")
    @ResponseBody
    public  ResultInfo saveUser(User user){
        userService.saveUser(user);
        return success("用户记录添加成功");
    }

    @ApiOperation("更新用户信息")
    @PostMapping("user/update")
    @ResponseBody
    public  ResultInfo updateUser(User user){
        userService.updateUser(user);
        return success("用户记录更新成功");
    }

    @ApiOperation("删除用户")
    @PostMapping("user/delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids){
        userService.deleteUserByIds(ids);
        return success("用户记录删除成功");
    }

}
