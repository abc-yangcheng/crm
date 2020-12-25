package top.upstudy.crm.vo;

import lombok.Data;

/**
 * 封装user表的一部分数据成 loginUser (存入cookie)
 */
//登录数据
@Data
public class loginUser {
    //id(加密)，加密为字符串
    private String userId;
    //姓名
    private String userName;
    //真实姓名
    private String trueName;
}
