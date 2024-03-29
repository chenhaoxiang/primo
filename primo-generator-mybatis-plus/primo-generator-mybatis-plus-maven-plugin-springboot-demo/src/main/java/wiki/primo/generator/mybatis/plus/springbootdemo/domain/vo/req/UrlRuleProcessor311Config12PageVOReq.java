package wiki.primo.generator.mybatis.plus.springbootdemo.domain.vo.req;

import wiki.primo.generator.mybatis.plus.springbootdemo.query.UrlRuleProcessor311Config12QueryBo;
import java.io.Serializable;

/**
 * 分页数据-请求
 * @author chenhx
 * @since 2022-08-22 21:17:00
 */
public class UrlRuleProcessor311Config12PageVOReq implements Serializable {
    private static final long serialVersionUID = -3324441151421460917L;
    /**
     * 查询条件
     */
    private UrlRuleProcessor311Config12QueryBo queryBo;
    /**
     * 当前页
     */
    private Integer pageNo;
    /**
     * 每页数据量
     */
    private Integer pageSize;
    /**
     * 表格需要
     */
    private Long draw;

    public UrlRuleProcessor311Config12QueryBo getQueryBo() {
        return queryBo;
    }

    public void setQueryBo(UrlRuleProcessor311Config12QueryBo queryBo) {
        this.queryBo = queryBo;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getDraw() {
        return draw;
    }

    public void setDraw(Long draw) {
        this.draw = draw;
     }

    @Override
    public String toString() {
    final StringBuilder sb = new StringBuilder("BlogsPageVOReq{");
        sb.append(super.toString());
        sb.append(",");
        sb.append("                queryBo=").append(queryBo);
        sb.append(",                 pageNo=").append(pageNo);
        sb.append(",                 pageSize=").append(pageSize);
        sb.append(",                 draw=").append(draw);
        sb.append('}');
        return sb.toString();
    }


}
