package wiki.primo.generator.mybatis.plus.springbootdemo.domain.req;

import java.io.Serializable;
import java.util.Date;
															
/**
 * <p>
 * 抓取URL的配置规则
 * </p>
 *
 * @author chenhx
 * @since 2022-04-08 14:34:43
 */
public class UrlRuleProcessorConfigReq implements Serializable {

private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;

	/**
	 * 平台名称-英文名称
	 */
	private String platformNameEn;

	/**
	 * 访问时，来源链接
	 */
	private String referer;

	/**
	 * 访问的前缀
	 */
	private String urlPrefix;

	/**
	 * 抓取URL的规则
	 */
	private String urlRuleJson;

	/**
	 * 抓取标题的规则
	 */
	private String titleRuleJson;

	/**
	 * 发表时间的规则
	 */
	private String pulishTimeRuleJson;

	/**
	 * 作者的规则
	 */
	private String authorRuleJson;

	/**
	 * 标签的规则
	 */
	private String tagsRuleJson;

	/**
	 * 删除时间，0未删除
	 */
	private Integer dateDelete;

	/**
	 * 域名
	 */
	private String domain;

	/**
	 * 对应表中的type数值
	 */
	private Integer blogsAuthorIndexUrlType;

	/**
	 * 
	 */
	private Date createTime;

	/**
	 * 
	 */
	private Date updateTime;

	/**
	 * 状态，0-正常，1-禁用
	 */
	private Integer status;


		
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
		final StringBuilder sb = new StringBuilder("UrlRuleProcessorConfig{").append(super.toString());

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
