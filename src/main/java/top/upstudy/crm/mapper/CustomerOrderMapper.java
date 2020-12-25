package top.upstudy.crm.mapper;

import org.springframework.dao.DataAccessException;
import top.upstudy.base.BaseQuery;
import top.upstudy.crm.pojo.Customer;
import top.upstudy.crm.pojo.CustomerOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;


public interface CustomerOrderMapper extends BaseMapper<CustomerOrder> {

    //多条件查询
    public List<CustomerOrder> selectByParams(BaseQuery baseQuery) throws DataAccessException;

    //查询所有订单
    Map<String, Object> queryCustomerOrderByOrderId(Integer orderId);


}
