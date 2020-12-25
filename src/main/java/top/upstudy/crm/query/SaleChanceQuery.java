package top.upstudy.crm.query;

import lombok.Data;
import top.upstudy.base.BaseQuery;

@Data
public class SaleChanceQuery extends BaseQuery {

    //营销机会条件查询：客户名，创建人，分配状态
    //客户开发条件查询：客户名，创建人，开发状态，分配人（销售人员）==当前登陆用户
    // 客户名
    private String customerName;

    // 创建人
    private String createMan;

    // 分配状态
    private String state;

    //分配人
    private Integer assignMan;

    // 开发状态
    private Integer devResult;

}
