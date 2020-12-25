package top.upstudy.base;

/**
 * 1、返回到前端的数据封装code 和 msg
 */
public class ResultInfo {
    private Integer code=200;
    private String msg="success";

    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
