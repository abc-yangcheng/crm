package top.upstudy.crm.mapper;

import org.springframework.dao.DataAccessException;
import top.upstudy.base.BaseQuery;
import top.upstudy.crm.pojo.SaleChance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface SaleChanceMapper extends BaseMapper<SaleChance> {

    //多条件查询
    public List<SaleChance> selectByParams(BaseQuery baseQuery) throws DataAccessException;

}
