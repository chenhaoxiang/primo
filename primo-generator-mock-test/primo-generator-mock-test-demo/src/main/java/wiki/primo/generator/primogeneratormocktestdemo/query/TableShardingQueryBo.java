package wiki.primo.generator.primogeneratormocktestdemo.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import wiki.primo.generator.primogeneratormocktestdemo.entity.TableSharding;
import java.util.Date;

/**
 * <p>
 * 分片表1
 * 查询条件类
 * </p>
 *
 * @author chenhx
 * @since 2020-12-29
 */
public class TableShardingQueryBo{

	private QueryBoExt queryBoExt;

	/**
	 * 
	 */
	private Integer id;

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private Integer age;

	/**
	 * 
	 */
	private Date createTime;

	/**
	 * 
	 */
	private String token;


	/**
	 * 构建查询
	 * @return
	 */
	public QueryWrapper<TableSharding> buildQuery() {
		QueryWrapper<TableSharding> query = new QueryWrapper<>();

		if(id!=null){
			query.eq(TableSharding.ID,id);
		}
		if(name!=null){
			query.eq(TableSharding.NAME,name);
		}
		if(age!=null){
			query.eq(TableSharding.AGE,age);
		}
		if(createTime!=null){
			query.eq(TableSharding.CREATE_TIME,createTime);
		}
		if(token!=null){
			query.eq(TableSharding.TOKEN,token);
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
		
	public Integer getAge() {
        return age;
	}

	public void setAge(Integer age) {
        this.age = age;
    }
		
	public Date getCreateTime() {
        return createTime;
	}

	public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
		
	public String getToken() {
        return token;
	}

	public void setToken(String token) {
        this.token = token;
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
