package top.upstudy.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.upstudy.crm.mapper.echartsMapper;
import top.upstudy.crm.pojo.Province;
import top.upstudy.crm.service.EchartsService;

import java.util.List;

@Service
public class EchartsImpl extends ServiceImpl<echartsMapper, Province> implements EchartsService {
    @Autowired
    echartsMapper pMapper;
    @Override
    public List<Province> selectAll(){
        return this.pMapper.selectAll();
    }
}
