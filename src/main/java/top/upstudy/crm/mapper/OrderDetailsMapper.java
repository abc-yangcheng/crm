package top.upstudy.crm.mapper;

import org.springframework.dao.DataAccessException;
import top.upstudy.base.BaseQuery;
import top.upstudy.crm.pojo.CusDevPlan;
import top.upstudy.crm.pojo.OrderDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface OrderDetailsMapper extends BaseMapper<OrderDetails> {

    //多条件查询
    public List<OrderDetails> selectByParams(BaseQuery baseQuery) throws DataAccessException;

}
