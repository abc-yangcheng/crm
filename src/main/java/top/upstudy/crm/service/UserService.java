package top.upstudy.crm.service;

import top.upstudy.crm.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import top.upstudy.crm.query.UserQuery;
import top.upstudy.crm.vo.loginUser;

import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {

    //登录逻辑
    public loginUser login(String userName, String userPwd);

    //修改密码
    public void updateUserInfo(Integer userId,String userOldPwd,String newPwd,String confirmPwd);

    //查询所有销售人员
    public List<Map<String,Object>> queryAllSales();

    //查询用户
    public Map<String,Object> queryUsersByParams(UserQuery userQuery);

    //添加用户
    public  void saveUser(User user);

    //更新用户
    public  void updateUser(User user);

    //批量删除用户
    public void deleteUserByIds(Integer[] ids);

    public User queryUsersByUserName(String userName);

}
