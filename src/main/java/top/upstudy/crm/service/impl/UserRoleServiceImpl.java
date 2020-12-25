package top.upstudy.crm.service.impl;

import top.upstudy.crm.pojo.UserRole;
import top.upstudy.crm.mapper.UserRoleMapper;
import top.upstudy.crm.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
