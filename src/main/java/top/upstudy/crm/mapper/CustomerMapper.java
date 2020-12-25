package top.upstudy.crm.mapper;

import org.springframework.dao.DataAccessException;
import top.upstudy.base.BaseQuery;
import top.upstudy.crm.pojo.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.upstudy.crm.pojo.User;
import top.upstudy.crm.query.CustomerQuery;

import java.util.List;
import java.util.Map;


public interface CustomerMapper extends BaseMapper<Customer> {

    //多条件查询
    public List<Customer> selectByParams(BaseQuery baseQuery) throws DataAccessException;


}
