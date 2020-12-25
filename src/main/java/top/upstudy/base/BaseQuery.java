package top.upstudy.base;


/**
 * 1、将表格查询的条件返回值加入，分页查询要的参数（page、limit）一起给前端
 */
public class BaseQuery {
    private Integer page=1;
    private Integer limit=10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
