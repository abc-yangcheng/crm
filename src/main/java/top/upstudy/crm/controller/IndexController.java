package top.upstudy.crm.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.upstudy.base.BaseController;
import top.upstudy.crm.mapper.UserMapper;
import top.upstudy.crm.pojo.User;
import top.upstudy.crm.service.PermissionService;
import top.upstudy.crm.service.UserService;
import top.upstudy.crm.service.impl.EmailServiceImpl;
import top.upstudy.crm.utils.LoginUserUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * 登录控制
 */
@Controller
public class IndexController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private PermissionService permissionService;

    @Autowired
    private EmailServiceImpl emailService;

    //系统登录页
    @ApiOperation("登录页")
    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("up")
    public String upfile(){
        return "upfile";
    }

    @GetMapping("file")
    public String file(String email,String title,String content,String filepath){
//        System.out.println(email+title+content+filepath);
//        filepath.replaceAll("\\\\","/");

        File file=new File(filepath);
//        System.out.println(filepath);
        emailService.sendAttachmentsMail(email,title,content,file);
//        System.out.println(file);

        return "";
    }




    //系统欢迎页
    @ApiOperation("系统欢迎页")
    @GetMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    //后台管理页
    @ApiOperation("后台管理页")
    @GetMapping(value = {"/","main"})
    public String admin(HttpServletRequest request) {
        //通过id查询用户的信息
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.getById(userId);
        //得到用户信息后返回
        request.setAttribute("user",user);

        //通过用户id查询该用户所拥有得权限，将查到得权限放到session中，以作为权限控制（查询对应资源的授权码）
        List<String> permissions = permissionService.queryUserHasRoleIdsHasModuleIds(userId);
        request.getSession().setAttribute("permissions",permissions);
        return "main";
    }

}
