package top.upstudy.crm.mapper;

import org.springframework.dao.DataAccessException;
import top.upstudy.base.BaseQuery;
import top.upstudy.crm.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    //查询所有销售人员
    public List<Map<String,Object>> queryAllSales();

    //多条件查询（搜索）
    public List<User> selectByParams(BaseQuery baseQuery) throws DataAccessException;

    //通过名字查询用户
    User  queryUserByUserName(String userName);


}
