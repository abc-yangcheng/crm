package top.upstudy.crm.utils;

import top.upstudy.crm.exceptions.ParamsException;

/**
 * 自定义抛出不匹配异常信息
 */
public class AssertUtil {


    public  static void isTrue(Boolean flag,String msg){
        if(flag){
            throw  new ParamsException(msg);
        }
    }

}
