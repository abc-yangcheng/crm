package top.upstudy.crm.mapper;

import org.springframework.dao.DataAccessException;
import top.upstudy.base.BaseQuery;
import top.upstudy.crm.pojo.CusDevPlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface CusDevPlanMapper extends BaseMapper<CusDevPlan> {

    //多条件查询
    public List<CusDevPlan> selectByParams(BaseQuery baseQuery) throws DataAccessException;


}
