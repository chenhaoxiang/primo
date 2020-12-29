package wiki.primo.generator.primogeneratormocktestdemo.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import wiki.primo.generator.primogeneratormocktestdemo.entity.UserTemplate;
import java.util.Date;

/**
 * <p>
 * 用户-模板表
 * 查询条件类
 * </p>
 *
 * @author chenhx
 * @since 2020-12-29
 */
public class UserTemplateQueryBo{

	private QueryBoExt queryBoExt;

	/**
	 * 
	 */
	private Integer id;

	/**
	 * 模板内容
	 */
	private String ext;

	/**
	 * 
	 */
	private Date createTime;

	/**
	 * 
	 */
	private Date updateTime;

	/**
	 * 过期时间
	 */
	private Date pastTime;

	/**
	 * 用户id
	 */
	private Integer userId;

	/**
	 * 模板标识
	 */
	private Integer templateCode;

	/**
	 * 用户自定义域名前缀
	 */
	private String domain;

	/**
	 * 访问密码-为空则是未设置
	 */
	private String password;


	/**
	 * 构建查询
	 * @return
	 */
	public QueryWrapper<UserTemplate> buildQuery() {
		QueryWrapper<UserTemplate> query = new QueryWrapper<>();

		if(id!=null){
			query.eq(UserTemplate.ID,id);
		}
		if(ext!=null){
			query.eq(UserTemplate.EXT,ext);
		}
		if(createTime!=null){
			query.eq(UserTemplate.CREATE_TIME,createTime);
		}
		if(updateTime!=null){
			query.eq(UserTemplate.UPDATE_TIME,updateTime);
		}
		if(pastTime!=null){
			query.eq(UserTemplate.PAST_TIME,pastTime);
		}
		if(userId!=null){
			query.eq(UserTemplate.USER_ID,userId);
		}
		if(templateCode!=null){
			query.eq(UserTemplate.TEMPLATE_CODE,templateCode);
		}
		if(domain!=null){
			query.eq(UserTemplate.DOMAIN,domain);
		}
		if(password!=null){
			query.eq(UserTemplate.PASSWORD,password);
		}
	
		if(queryBoExt!=null){
			//设置排序
			if(queryBoExt.getOrderColumn()!=null){
				if(OrderType.asc.getValue().equalsIgnoreCase(queryBoExt.getOrderType())){
					query.orderByAsc(queryBoExt.getOrderColumn());
				}else if(OrderType.desc.getValue().equalsIgnoreCase(queryBoExt.getOrderType())){
					query.orderByDesc(queryBoExt.getOrderColumn());
				}
			}
		}
		return query;
	}

		
	public Integer getId() {
        return id;
	}

	public void setId(Integer id) {
        this.id = id;
    }
		
	public String getExt() {
        return ext;
	}

	public void setExt(String ext) {
        this.ext = ext;
    }
		
	public Date getCreateTime() {
        return createTime;
	}

	public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
		
	public Date getUpdateTime() {
        return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
		
	public Date getPastTime() {
        return pastTime;
	}

	public void setPastTime(Date pastTime) {
        this.pastTime = pastTime;
    }
		
	public Integer getUserId() {
        return userId;
	}

	public void setUserId(Integer userId) {
        this.userId = userId;
    }
		
	public Integer getTemplateCode() {
        return templateCode;
	}

	public void setTemplateCode(Integer templateCode) {
        this.templateCode = templateCode;
    }
		
	public String getDomain() {
        return domain;
	}

	public void setDomain(String domain) {
        this.domain = domain;
    }
		
	public String getPassword() {
        return password;
	}

	public void setPassword(String password) {
        this.password = password;
    }


	public QueryBoExt getQueryBoExt() {
		return queryBoExt;
	}

	public void setQueryBoExt(QueryBoExt queryBoExt) {
		this.queryBoExt = queryBoExt;
	}

	public static class QueryBoExt {
		/**
		 * 一般情况下只需要单个列的排序
		 */
		private String orderColumn;

		/**
		 * asc / desc
		 */
		private String orderType = OrderType.asc.getValue();

		public String getOrderColumn() {
			return orderColumn;
		}

		public void setOrderColumn(String orderColumn) {
			this.orderColumn = orderColumn;
		}

		public String getOrderType() {
			return orderType;
		}

		public void setOrderType(String orderType) {
			this.orderType = orderType;
		}
	}

	public enum OrderType{
		asc("正序","asc"),
		desc("倒序","desc");
		private String name;
		private String value;

		public String getName() {
			return name;
		}

		public String getValue() {
			return value;
		}

		OrderType(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}
}
