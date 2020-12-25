package top.upstudy.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.upstudy.crm.mapper.CustomerOrderMapper;
import top.upstudy.crm.pojo.Customer;
import top.upstudy.crm.mapper.CustomerMapper;
import top.upstudy.crm.query.CustomerQuery;
import top.upstudy.crm.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.upstudy.crm.utils.AssertUtil;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;


    @Resource
    private CustomerOrderMapper customerOrderMapper;

    //通过参数查询用户
    public Map<String,Object> queryCustomersByParams(CustomerQuery customerQuery){
        Map<String,Object> map=new HashMap<String,Object>();
        PageHelper.startPage(customerQuery.getPage(),customerQuery.getLimit());
        PageInfo<Customer> pageInfo=new PageInfo<Customer>(customerMapper.selectByParams(customerQuery));
        map.put("code",0);
        map.put("msg","");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return  map;
    }

    //添加用户
    public  void saveCustomer(Customer customer){

        AssertUtil.isTrue(null != customerMapper.selectOne(new QueryWrapper<Customer>().eq("name",customer.getName())),"该客户已存在!");

        /**
         * 参数默认值
         *     isValid
         *     createDate
         *     updateDate
         */
        customer.setIsValid(1);
        customer.setCreateDate(new Date());
        customer.setUpdateDate(new Date());

        /**
         * 设置客户编号(当前时间（微妙）+ 0-10000内的一个随机数)
         * 客户编号格式：KH+时间戳
         * 系统生成，唯一（uuid | 时间戳 | 年月日时分秒 | 雪花算法）
          */

        String customerId ="KH"+ System.currentTimeMillis() + new Random().nextInt(10000) ;
        customer.setCustomerId(customerId);
        //添加数据
        AssertUtil.isTrue(customerMapper.insert(customer) < 1,"客户记录添加失败!");

    }



    //更新用户
    public  void updateCustomer(Customer customer){

        //查找是否存在
        Customer temp = customerMapper.selectById(customer.getId());
        AssertUtil.isTrue(null == temp,"待更新的客户记录不存在!");
        //名字不允许重复
        temp = customerMapper.selectOne(new QueryWrapper<Customer>().eq("name",customer.getName()));
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(customer.getId())),"该客户已存在!");
        customer.setUpdateDate(new Date());

        AssertUtil.isTrue(customerMapper.updateById(customer) < 1,"客户记录更新失败!");
    }

    //删除用户
    public void deleteCustomer(Integer id) {
        Customer customer = customerMapper.selectById(id);
        AssertUtil.isTrue(null == customer,"待删除的客户记录不存在!");
        AssertUtil.isTrue(customerMapper.deleteById(customer) < 1,"客户记录删除失败!");

    }

}
