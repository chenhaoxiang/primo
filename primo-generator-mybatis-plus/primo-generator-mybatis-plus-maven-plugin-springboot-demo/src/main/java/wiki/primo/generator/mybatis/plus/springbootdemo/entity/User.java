package wiki.primo.generator.mybatis.plus.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenhx
 * @since 2020-12-29
 */
public class User implements Serializable {

private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
    @TableId(type = IdType.AUTO)
	@TableField(value="`id`")
	private Integer id;

	/**
	 * 用户名
	 */
	@TableField(value="`name`")
	private String name;

	/**
	 * 密码
	 */
	@TableField(value="`password`")
	private String password;

	/**
	 * 
	 */
	@TableField(value="`create_time`")
	private Date createTime;

	/**
	 * 
	 */
	@TableField(value="`update_time`")
	private Date updateTime;

	/**
	 * 过期时间
	 */
	@TableField(value="`past_time`")
	private Date pastTime;

	/**
	 * 性别，0-女，1-男
	 */
	@TableField(value="`sex`")
	private Integer sex;

	/**
	 * 手机号
	 */
	@TableField(value="`phone`")
	private String phone;

	/**
	 * 邮箱
	 */
	@TableField(value="`email`")
	private String email;

	/**
	 * 描述
	 */
	@TableField(value="`describe`")
	private String describe;

	/**
	 * 角色，0-用户，1-管理员
	 */
	@TableField(value="`role`")
	private Integer role;


	public static final String ID="id";
	public static final String NAME="name";
	public static final String PASSWORD="password";
	public static final String CREATE_TIME="create_time";
	public static final String UPDATE_TIME="update_time";
	public static final String PAST_TIME="past_time";
	public static final String SEX="sex";
	public static final String PHONE="phone";
	public static final String EMAIL="email";
	public static final String DESCRIBE="describe";
	public static final String ROLE="role";

		
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

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("User{").append(super.toString());

	sb.append(",id=").append(id);
		sb.append(",name='").append(name).append('\'');
		sb.append(",password='").append(password).append('\'');
		sb.append(",createTime=").append(createTime);
		sb.append(",updateTime=").append(updateTime);
		sb.append(",pastTime=").append(pastTime);
		sb.append(",sex=").append(sex);
		sb.append(",phone='").append(phone).append('\'');
		sb.append(",email='").append(email).append('\'');
		sb.append(",describe='").append(describe).append('\'');
		sb.append(",role=").append(role);
	
		sb.append('}');
		return sb.toString();
	}
}
