package top.upstudy.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import top.upstudy.crm.pojo.CustomerOrder;
import top.upstudy.crm.mapper.CustomerOrderMapper;
import top.upstudy.crm.query.CustomerOrderQuery;
import top.upstudy.crm.service.CustomerOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerOrderServiceImpl extends ServiceImpl<CustomerOrderMapper, CustomerOrder> implements CustomerOrderService {

    @Resource
    private CustomerOrderMapper orderMapper;

    //查询所有订单
    public Map<String,Object> queryCustomerOrdersByParams(CustomerOrderQuery customerOrderQueryQuery){
        Map<String,Object> map=new HashMap<String,Object>();
        PageHelper.startPage(customerOrderQueryQuery.getPage(),customerOrderQueryQuery.getLimit());
        PageInfo<CustomerOrder> pageInfo=new PageInfo<CustomerOrder>(orderMapper.selectByParams(customerOrderQueryQuery));
        map.put("code",0);
        map.put("msg","");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return  map;
    }

    //查询单个订单
    public Map<String,Object> queryCustomerOrderByOrderId(Integer orderId) {
        return  orderMapper.queryCustomerOrderByOrderId(orderId);
    }
}
