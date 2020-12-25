package top.upstudy.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import top.upstudy.crm.pojo.Province;

import java.util.List;

public interface EchartsService extends IService<Province> {
    public List<Province> selectAll();
}
