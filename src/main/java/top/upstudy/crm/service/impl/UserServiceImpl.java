package top.upstudy.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.upstudy.crm.mapper.UserRoleMapper;
import top.upstudy.crm.pojo.User;
import top.upstudy.crm.mapper.UserMapper;
import top.upstudy.crm.pojo.UserRole;
import top.upstudy.crm.query.UserQuery;
import top.upstudy.crm.service.UserRoleService;
import top.upstudy.crm.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.upstudy.crm.utils.AssertUtil;
import top.upstudy.crm.utils.Md5Util;
import top.upstudy.crm.utils.UserIDBase64;
import top.upstudy.crm.vo.loginUser;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private UserRoleService userRoleService;

    /**
     * 用户登录的实现
     */
    //登录逻辑
    public loginUser login(String userName, String userPassword){
        //查找用户是否存在
        User user = userMapper.queryUserByUserName(userName);
        AssertUtil.isTrue(null == user,"用户不存在！");
        //判断密码是否正确
        AssertUtil.isTrue(!(user.getUserPassword().equals(Md5Util.encode(userPassword))),"密码错误！请检查是否输入正确");

        //通过后向前端返回用户信息
        return buildUserInfo(user);

    }

    //获取user中的加密的id、姓名、真实姓名
    private loginUser buildUserInfo(User user) {
        loginUser loginUser = new loginUser();
        loginUser.setUserId(UserIDBase64.encoderUserID(user.getId()));
        loginUser.setUserName(user.getUserName());
        loginUser.setTrueName(user.getTrueName());
        return loginUser;
    }

    /**
     * 用户修改密码的功能
     */
    //修改用户信息逻辑
    public void updateUserInfo(Integer userId,String userOldPassword,String newPassword,String confirmPassword) {
        //判断确认密码是否一致
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)), "确认密码与新密码不一致！");

        //如果新旧密码一致
        AssertUtil.isTrue(newPassword.equals(userOldPassword), "新旧密码一致，不必更新哦！");

        //进行更新密码操作
        User user = userMapper.selectById(userId);
        user.setUserPassword(Md5Util.encode(newPassword));

        //如果数据库中用户表没有更新至少一行的操作，抛出异常
        AssertUtil.isTrue((userMapper.updateById(user) < 1),"用户密码更新失败！");

    }

    //查询销售人员
    public List<Map<String,Object>> queryAllSales(){
        return userMapper.queryAllSales();
    }


    /**
     * 用户管理模块
     */
    //查询用户
    public Map<String,Object> queryUsersByParams(UserQuery userQuery){
        Map<String,Object> map=new HashMap<String,Object>();
        //开启分页
        PageHelper.startPage(userQuery.getPage(),userQuery.getLimit());
        PageInfo<User> pageInfo=new PageInfo<User>(userMapper.selectByParams(userQuery));
        map.put("code",0);
        map.put("msg","");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return  map;
    }

    //添加用户以及角色信息
    public  void saveUser(User user){

        AssertUtil.isTrue(null != userMapper.queryUserByUserName(user.getUserName()),"用户名不能重复!");
        //逻辑存在  设置时间
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        //默认密码
        user.setUserPassword(Md5Util.encode("123456"));
        //添加记录
        AssertUtil.isTrue(userMapper.insert(user) < 1,"用户记录添加失败!");

        // 添加成功后，获取添加的用户id 主键
        Integer userId = userMapper.queryUserByUserName(user.getUserName()).getId();
        // 获取传入的该用户的角色字符串
        String roleIds = user.getRoleIds();
        /**
         * 批量添加用户角色记录到用户角色表
         */
        relationUserRoles(userId,roleIds);

    }

    //用户以及角色更新操作
    public  void updateUser(User user){

        //获取将要更新的用户信息
        User temp = userMapper.selectById(user.getId());
        //判断用户是否存在
        AssertUtil.isTrue(null == temp,"待更新的用户记录不存在!");
        //查询名字是否已存在
        temp = userMapper.queryUserByUserName(user.getUserName());
        //如果名字已存在，但是id与数据库存在的id相对比不一致，则拒绝改为与该id的人同名
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(user.getId())),"该用户已存在!");
        user.setUpdateDate(new Date());
        //更新信息
        AssertUtil.isTrue(userMapper.updateById(user) < 1,"用户记录更新失败!");

        /**
         * 批量添加用户角色记录到用户角色表
         */
        relationUserRoles(user.getId(),user.getRoleIds());
    }

    //批量删除用户
    public void deleteUserByIds(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length == 0,"请选择待删除的用户记录!");
        AssertUtil.isTrue(userMapper.deleteBatchIds(Arrays.asList(ids))!=ids.length,"用户记录删除失败!");
    }

    @Override
    public User queryUsersByUserName(String userName) {
        return userMapper.queryUserByUserName(userName);
    }


    /**
     * 批量添加用户角色记录到用户角色表
     */
    private void relationUserRoles(Integer userId, String roleIds) {

        //mybatis-plus的条件构造器（所有与user_id字段相等的项）
        QueryWrapper<UserRole> wrapper = new QueryWrapper<UserRole>().eq("user_id", userId);
        //查询角色表中，当前用户有多少个角色项
        int total = userRoleMapper.selectCount(wrapper);

        //如果大于0，则为更新操作，需要将之前的角色赋值删除，然后执行往后的角色赋值操作
        if(total>0){
            AssertUtil.isTrue(userRoleMapper.delete(wrapper) != total,"用户角色记录设置失败!");
        }
        //如果设置的角色不为空
        if(StringUtils.isNotBlank(roleIds)){
            //使用List存储每一个角色列信息，然后插入角色表中
            List<UserRole> userRoles = new ArrayList<UserRole>();
            //使用split进行字符串切割，每一次循环都得到一个角色项
            for(String roleId : roleIds.split(",")){
                //新建一个角色
                UserRole userRole=new UserRole();

                userRole.setCreateDate(new Date());
                userRole.setUpdateDate(new Date());
                userRole.setRoleId(Integer.parseInt(roleId));
                userRole.setUserId(userId);

                userRoles.add(userRole);
            }
            //批量插入角色表
            AssertUtil.isTrue(!(userRoleService.saveBatch(userRoles)),"用户角色记录添加失败!");
        }
    }

}
