package wiki.primo.generator.primogeneratormocktestdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 分片表1
 * </p>
 *
 * @author chenhx
 * @since 2020-12-29
 */
@TableName("`table_sharding`")
public class TableSharding implements Serializable {

private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
    @TableId(type = IdType.AUTO)
	@TableField(value="`id`")
	private Integer id;

	/**
	 * 
	 */
	@TableField(value="`name`")
	private String name;

	/**
	 * 
	 */
	@TableField(value="`age`")
	private Integer age;

	/**
	 * 
	 */
	@TableField(value="`create_time`")
	private Date createTime;

	/**
	 * 
	 */
	@TableField(value="`token`")
	private String token;


	public static final String ID="id";
	public static final String NAME="name";
	public static final String AGE="age";
	public static final String CREATE_TIME="create_time";
	public static final String TOKEN="token";

		
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

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("TableSharding{").append(super.toString());

	sb.append(",id=").append(id);
		sb.append(",name='").append(name).append('\'');
		sb.append(",age=").append(age);
		sb.append(",createTime=").append(createTime);
		sb.append(",token='").append(token).append('\'');
	
		sb.append('}');
		return sb.toString();
	}
}
