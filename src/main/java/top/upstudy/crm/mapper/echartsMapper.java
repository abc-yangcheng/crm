package top.upstudy.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.upstudy.crm.pojo.Province;

import java.util.List;


public interface echartsMapper extends BaseMapper<Province> {
    public List<Province> selectAll();
}
