package wiki.primo.generator.mybatis.plus.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
															
/**
 * <p>
 * 抓取URL的配置规则
 * </p>
 *
 * @author chenhx
 * @since 2022-04-07 18:53:48
 */
@TableName("`url_rule_processor_311_config_12`")
public class UrlRuleProcessor311Config12 implements Serializable {

private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
    @TableId(type = IdType.AUTO)
	@TableField(value="`id`")
	private Integer id;

	/**
	 * 平台名称-英文名称
	 */
	@TableField(value="`platform_name_en`")
	private String platformNameEn;

	/**
	 * 访问时，来源链接
	 */
	@TableField(value="`referer`")
	private String referer;

	/**
	 * 访问的前缀
	 */
	@TableField(value="`url_prefix`")
	private String urlPrefix;

	/**
	 * 抓取URL的规则
	 */
	@TableField(value="`url_rule_json`")
	private String urlRuleJson;

	/**
	 * 抓取标题的规则
	 */
	@TableField(value="`title_rule_json`")
	private String titleRuleJson;

	/**
	 * 发表时间的规则
	 */
	@TableField(value="`pulish_time_rule_json`")
	private String pulishTimeRuleJson;

	/**
	 * 作者的规则
	 */
	@TableField(value="`author_rule_json`")
	private String authorRuleJson;

	/**
	 * 标签的规则
	 */
	@TableField(value="`tags_rule_json`")
	private String tagsRuleJson;

	/**
	 * 删除时间，0未删除
	 */
	@TableField(value="`date_delete`")
	private Integer dateDelete;

	/**
	 * 域名
	 */
	@TableField(value="`domain`")
	private String domain;

	/**
	 * 对应表中的type数值
	 */
	@TableField(value="`blogs_author_index_url_type`")
	private Integer blogsAuthorIndexUrlType;

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
	 * 状态，0-正常，1-禁用
	 */
	@TableField(value="`status`")
	private Integer status;


	public static final String ID="id";
	public static final String PLATFORM_NAME_EN="platform_name_en";
	public static final String REFERER="referer";
	public static final String URL_PREFIX="url_prefix";
	public static final String URL_RULE_JSON="url_rule_json";
	public static final String TITLE_RULE_JSON="title_rule_json";
	public static final String PULISH_TIME_RULE_JSON="pulish_time_rule_json";
	public static final String AUTHOR_RULE_JSON="author_rule_json";
	public static final String TAGS_RULE_JSON="tags_rule_json";
	public static final String DATE_DELETE="date_delete";
	public static final String DOMAIN="domain";
	public static final String BLOGS_AUTHOR_INDEX_URL_TYPE="blogs_author_index_url_type";
	public static final String CREATE_TIME="create_time";
	public static final String UPDATE_TIME="update_time";
	public static final String STATUS="status";

		
	public Integer getId() {
        return id;
	}

	public void setId(Integer id) {
        this.id = id;
    }
		
	public String getPlatformNameEn() {
        return platformNameEn;
	}

	public void setPlatformNameEn(String platformNameEn) {
        this.platformNameEn = platformNameEn;
    }
		
	public String getReferer() {
        return referer;
	}

	public void setReferer(String referer) {
        this.referer = referer;
    }
		
	public String getUrlPrefix() {
        return urlPrefix;
	}

	public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }
		
	public String getUrlRuleJson() {
        return urlRuleJson;
	}

	public void setUrlRuleJson(String urlRuleJson) {
        this.urlRuleJson = urlRuleJson;
    }
		
	public String getTitleRuleJson() {
        return titleRuleJson;
	}

	public void setTitleRuleJson(String titleRuleJson) {
        this.titleRuleJson = titleRuleJson;
    }
		
	public String getPulishTimeRuleJson() {
        return pulishTimeRuleJson;
	}

	public void setPulishTimeRuleJson(String pulishTimeRuleJson) {
        this.pulishTimeRuleJson = pulishTimeRuleJson;
    }
		
	public String getAuthorRuleJson() {
        return authorRuleJson;
	}

	public void setAuthorRuleJson(String authorRuleJson) {
        this.authorRuleJson = authorRuleJson;
    }
		
	public String getTagsRuleJson() {
        return tagsRuleJson;
	}

	public void setTagsRuleJson(String tagsRuleJson) {
        this.tagsRuleJson = tagsRuleJson;
    }
		
	public Integer getDateDelete() {
        return dateDelete;
	}

	public void setDateDelete(Integer dateDelete) {
        this.dateDelete = dateDelete;
    }
		
	public String getDomain() {
        return domain;
	}

	public void setDomain(String domain) {
        this.domain = domain;
    }
		
	public Integer getBlogsAuthorIndexUrlType() {
        return blogsAuthorIndexUrlType;
	}

	public void setBlogsAuthorIndexUrlType(Integer blogsAuthorIndexUrlType) {
        this.blogsAuthorIndexUrlType = blogsAuthorIndexUrlType;
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
		
	public Integer getStatus() {
        return status;
	}

	public void setStatus(Integer status) {
        this.status = status;
    }

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("UrlRuleProcessor311Config12{").append(super.toString());

	sb.append(",id=").append(id);
		sb.append(",platformNameEn='").append(platformNameEn).append('\'');
		sb.append(",referer='").append(referer).append('\'');
		sb.append(",urlPrefix='").append(urlPrefix).append('\'');
		sb.append(",urlRuleJson='").append(urlRuleJson).append('\'');
		sb.append(",titleRuleJson='").append(titleRuleJson).append('\'');
		sb.append(",pulishTimeRuleJson='").append(pulishTimeRuleJson).append('\'');
		sb.append(",authorRuleJson='").append(authorRuleJson).append('\'');
		sb.append(",tagsRuleJson='").append(tagsRuleJson).append('\'');
		sb.append(",dateDelete=").append(dateDelete);
		sb.append(",domain='").append(domain).append('\'');
		sb.append(",blogsAuthorIndexUrlType=").append(blogsAuthorIndexUrlType);
		sb.append(",createTime=").append(createTime);
		sb.append(",updateTime=").append(updateTime);
		sb.append(",status=").append(status);
	
		sb.append('}');
		return sb.toString();
	}
}
