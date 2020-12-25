package top.upstudy.crm.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import top.upstudy.base.BaseController;
import top.upstudy.crm.query.CustomerOrderQuery;
import top.upstudy.crm.service.CustomerOrderService;

import javax.annotation.Resource;
import java.util.Map;

/**
 *客户信息管理-订单查看
 */
@Controller
@RequestMapping("order")
public class CustomerOrderController extends BaseController {

    @Resource
    private CustomerOrderService customerOrderService;

    @ApiOperation("顾客所有订单")
    @GetMapping("list")
    @ResponseBody
    public Map<String,Object> queryCustomerOrdersByParams(CustomerOrderQuery customerOrderQuery){
        return customerOrderService.queryCustomerOrdersByParams(customerOrderQuery);
    }
}
