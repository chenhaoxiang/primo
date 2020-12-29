package wiki.primo.generator.primogeneratormocktestdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户-模板表
 * </p>
 *
 * @author chenhx
 * @since 2020-12-29
 */
@TableName("`user_template`")
public class UserTemplate implements Serializable {

private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
    @TableId(type = IdType.AUTO)
	@TableField(value="`id`")
	private Integer id;

	/**
	 * 模板内容
	 */
	@TableField(value="`ext`")
	private String ext;

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
	 * 用户id
	 */
	@TableField(value="`user_id`")
	private Integer userId;

	/**
	 * 模板标识
	 */
	@TableField(value="`template_code`")
	private Integer templateCode;

	/**
	 * 用户自定义域名前缀
	 */
	@TableField(value="`domain`")
	private String domain;

	/**
	 * 访问密码-为空则是未设置
	 */
	@TableField(value="`password`")
	private String password;


	public static final String ID="id";
	public static final String EXT="ext";
	public static final String CREATE_TIME="create_time";
	public static final String UPDATE_TIME="update_time";
	public static final String PAST_TIME="past_time";
	public static final String USER_ID="user_id";
	public static final String TEMPLATE_CODE="template_code";
	public static final String DOMAIN="domain";
	public static final String PASSWORD="password";

		
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

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("UserTemplate{").append(super.toString());

	sb.append(",id=").append(id);
		sb.append(",ext='").append(ext).append('\'');
		sb.append(",createTime=").append(createTime);
		sb.append(",updateTime=").append(updateTime);
		sb.append(",pastTime=").append(pastTime);
		sb.append(",userId=").append(userId);
		sb.append(",templateCode=").append(templateCode);
		sb.append(",domain='").append(domain).append('\'');
		sb.append(",password='").append(password).append('\'');
	
		sb.append('}');
		return sb.toString();
	}
}
