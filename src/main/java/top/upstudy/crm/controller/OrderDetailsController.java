package top.upstudy.crm.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.upstudy.base.BaseController;
import top.upstudy.crm.query.OrderDetailsQuery;
import top.upstudy.crm.service.OrderDetailsService;

import javax.annotation.Resource;
import java.util.Map;

/**
 *客户信息管理-订单查看-订单详情
 */
@Controller
@RequestMapping("order_details")
public class OrderDetailsController extends BaseController {

    @Resource
    private OrderDetailsService orderDetailsService;

    @ApiOperation("订单详情")
    @GetMapping("list")
    @ResponseBody
    public Map<String,Object> queryDetailsByParams(OrderDetailsQuery orderDetailsQuery){
        return  orderDetailsService.queryOrderDetailsByParams(orderDetailsQuery);
    }
}
