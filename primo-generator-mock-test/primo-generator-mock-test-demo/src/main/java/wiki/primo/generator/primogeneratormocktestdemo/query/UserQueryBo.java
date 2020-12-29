package wiki.primo.generator.primogeneratormocktestdemo.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import wiki.primo.generator.primogeneratormocktestdemo.entity.User;
import java.util.Date;

/**
 * <p>
 * 
 * 查询条件类
 * </p>
 *
 * @author chenhx
 * @since 2020-12-29
 */
public class UserQueryBo{

	private QueryBoExt queryBoExt;

	/**
	 * 
	 */
	private Integer id;

	/**
	 * 用户名
	 */
	private String name;

	/**
	 * 密码
	 */
	private String password;

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
	 * 性别，0-女，1-男
	 */
	private Integer sex;

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 描述
	 */
	private String describe;

	/**
	 * 角色，0-用户，1-管理员
	 */
	private Integer role;


	/**
	 * 构建查询
	 * @return
	 */
	public QueryWrapper<User> buildQuery() {
		QueryWrapper<User> query = new QueryWrapper<>();

		if(id!=null){
			query.eq(User.ID,id);
		}
		if(name!=null){
			query.eq(User.NAME,name);
		}
		if(password!=null){
			query.eq(User.PASSWORD,password);
		}
		if(createTime!=null){
			query.eq(User.CREATE_TIME,createTime);
		}
		if(updateTime!=null){
			query.eq(User.UPDATE_TIME,updateTime);
		}
		if(pastTime!=null){
			query.eq(User.PAST_TIME,pastTime);
		}
		if(sex!=null){
			query.eq(User.SEX,sex);
		}
		if(phone!=null){
			query.eq(User.PHONE,phone);
		}
		if(email!=null){
			query.eq(User.EMAIL,email);
		}
		if(describe!=null){
			query.eq(User.DESCRIBE,describe);
		}
		if(role!=null){
			query.eq(User.ROLE,role);
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
		
	public String getName() {
        return name;
	}

	public void setName(String name) {
        this.name = name;
    }
		
	public String getPassword() {
        return password;
	}

	public void setPassword(String password) {
        this.password = password;
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
		
	public Integer getSex() {
        return sex;
	}

	public void setSex(Integer sex) {
        this.sex = sex;
    }
		
	public String getPhone() {
        return phone;
	}

	public void setPhone(String phone) {
        this.phone = phone;
    }
		
	public String getEmail() {
        return email;
	}

	public void setEmail(String email) {
        this.email = email;
    }
		
	public String getDescribe() {
        return describe;
	}

	public void setDescribe(String describe) {
        this.describe = describe;
    }
		
	public Integer getRole() {
        return role;
	}

	public void setRole(Integer role) {
        this.role = role;
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
