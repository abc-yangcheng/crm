package top.upstudy.crm.service;

import top.upstudy.crm.pojo.Module;
import com.baomidou.mybatisplus.extension.service.IService;
import top.upstudy.crm.vo.ZTree;

import java.util.List;
import java.util.Map;

public interface ModuleService extends IService<Module> {

    //查询传入角色拥有的权限，放回类型为z-tree
    public List<ZTree> queryAllModules(Integer roleId);

    //查询整张菜单表数据
    public Map<String,Object> queryModules();

}
