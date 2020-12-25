package top.upstudy.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import top.upstudy.crm.mapper.PermissionMapper;
import top.upstudy.crm.pojo.Module;
import top.upstudy.crm.mapper.ModuleMapper;
import top.upstudy.crm.pojo.Permission;
import top.upstudy.crm.pojo.UserRole;
import top.upstudy.crm.service.ModuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.upstudy.crm.utils.AssertUtil;
import top.upstudy.crm.vo.ZTree;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements ModuleService {

    @Resource
    private ModuleMapper moduleMapper;

    @Resource
    private PermissionMapper permissionMapper;


    //查询所有已经给传入角色分配的资源
    public List<ZTree> queryAllModules(Integer roleId){
        //先查询所有资源
        List<ZTree> ZTrees = moduleMapper.queryAllModules();
        // 查询角色已分配的菜单id
        List<Integer> mids = permissionMapper.queryRoleAllModIds(roleId);
        /**
         * 如果角色已经拥有了某个资源的权限，则将z-tree的checked值置为true，
         * 这样就能让前端页面显示的时候可以显示已经打勾
         */
        if(null != mids && mids.size() > 0){
            ZTrees.forEach( tree ->{
                //查看当前ZTree对象的id是否包含在 用户已经被分配到的资源id集合中
                if(mids.contains(tree.getId())){
                    // 设置为已经被选择，用作前台展示
                    tree.setChecked(true);
                }
            });
        }
        return ZTrees;
    }


    /**
     * 菜单管理模块
     */

    //查询整张菜单表数据
    public Map<String,Object> queryModules(){
        Map<String,Object> result = new HashMap<String,Object>();
        //查询所有菜单的所有数据
        List<Module> modules = moduleMapper.selectList(null);
        result.put("count",modules.size());
        result.put("data",modules);
        result.put("code",0);
        result.put("msg","");
        return  result;
    }

}
