package top.upstudy.crm.service;

import top.upstudy.crm.pojo.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import top.upstudy.crm.query.CustomerQuery;

import java.util.Map;

public interface CustomerService extends IService<Customer> {

    //通过参数查询客户
    public Map<String,Object> queryCustomersByParams(CustomerQuery customerQuery);

    //添加用户
    public  void saveCustomer(Customer customer);

    //更新用户
    public  void updateCustomer(Customer customer);

    //删除用户
    public void deleteCustomer(Integer id);


}
