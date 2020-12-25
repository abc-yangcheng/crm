package top.upstudy.crm.mapper;

import top.upstudy.crm.pojo.Module;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.upstudy.crm.vo.ZTree;

import java.util.List;

public interface ModuleMapper extends BaseMapper<Module> {

    //查询所有可授权资源
    public List<ZTree> queryAllModules();
}
