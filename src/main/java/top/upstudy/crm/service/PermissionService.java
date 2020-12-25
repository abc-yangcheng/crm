package top.upstudy.crm.service;

import top.upstudy.crm.pojo.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface PermissionService extends IService<Permission> {

    //通过用户id查询权限
    List<String> queryUserHasRoleIdsHasModuleIds(Integer userId);
}
