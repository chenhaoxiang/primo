package wiki.primo.generator.mybatis.plus.springbootdemo.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import wiki.primo.generator.mybatis.plus.springbootdemo.entity.UrlRuleProcessor311Config12;
import java.util.Date;
import java.util.List;

															
/**
 * <p>
 * 抓取URL的配置规则
 * 查询条件类
 * </p>
 *
 * @author chenhx
 * @since 2022-04-07 18:24:32
 */
public class UrlRuleProcessor311Config12QueryBo{
	/**
	 * 排序，批量查询
	 */
	private QueryBoExt queryBoExt;
	/**
	 * 模糊查询
	 */
	private List<QueryBoFuzzy> queryBoFuzzyList;
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


	/**
	 * 构建查询
	 * @return
	 */
	public QueryWrapper<UrlRuleProcessor311Config12> buildQuery() {
		QueryWrapper<UrlRuleProcessor311Config12> query = new QueryWrapper<>();

		if(id!=null){
			query.eq(UrlRuleProcessor311Config12.ID,id);
		}
		if(platformNameEn!=null){
			query.eq(UrlRuleProcessor311Config12.PLATFORM_NAME_EN,platformNameEn);
		}
		if(referer!=null){
			query.eq(UrlRuleProcessor311Config12.REFERER,referer);
		}
		if(urlPrefix!=null){
			query.eq(UrlRuleProcessor311Config12.URL_PREFIX,urlPrefix);
		}
		if(urlRuleJson!=null){
			query.eq(UrlRuleProcessor311Config12.URL_RULE_JSON,urlRuleJson);
		}
		if(titleRuleJson!=null){
			query.eq(UrlRuleProcessor311Config12.TITLE_RULE_JSON,titleRuleJson);
		}
		if(pulishTimeRuleJson!=null){
			query.eq(UrlRuleProcessor311Config12.PULISH_TIME_RULE_JSON,pulishTimeRuleJson);
		}
		if(authorRuleJson!=null){
			query.eq(UrlRuleProcessor311Config12.AUTHOR_RULE_JSON,authorRuleJson);
		}
		if(tagsRuleJson!=null){
			query.eq(UrlRuleProcessor311Config12.TAGS_RULE_JSON,tagsRuleJson);
		}
		if(dateDelete!=null){
			query.eq(UrlRuleProcessor311Config12.DATE_DELETE,dateDelete);
		}
		if(domain!=null){
			query.eq(UrlRuleProcessor311Config12.DOMAIN,domain);
		}
		if(blogsAuthorIndexUrlType!=null){
			query.eq(UrlRuleProcessor311Config12.BLOGS_AUTHOR_INDEX_URL_TYPE,blogsAuthorIndexUrlType);
		}
		if(createTime!=null){
			query.eq(UrlRuleProcessor311Config12.CREATE_TIME,createTime);
		}
		if(updateTime!=null){
			query.eq(UrlRuleProcessor311Config12.UPDATE_TIME,updateTime);
		}
		if(status!=null){
			query.eq(UrlRuleProcessor311Config12.STATUS,status);
		}
	
		//模糊查询
		if(queryBoFuzzyList!=null && queryBoFuzzyList.size()>0){
			for (QueryBoFuzzy queryBoFuzzy : queryBoFuzzyList) {
				if(queryBoFuzzy.getValue()==null || queryBoFuzzy.getValue().trim().length()==0){
					continue;
				}
				query.or(blogsQueryWrapper -> {
					blogsQueryWrapper.like(queryBoFuzzy.getColumn(),queryBoFuzzy.getValue().trim());
				});
			}
		}
		if(queryBoExt!=null){
			//设置排序
			if(queryBoExt.getOrderColumn()!=null && queryBoExt.getOrderColumn().trim().length()>0 ){
				if(queryBoExt.isAsc()){
					query.orderByAsc(queryBoExt.getOrderColumn());
				}else{
					query.orderByDesc(queryBoExt.getOrderColumn());
				}
			}
			//批量的查询条件进行查询
                        if(queryBoExt.getIdList()!=null && queryBoExt.getIdList().size()>0){
                query.in(UrlRuleProcessor311Config12.ID,queryBoExt.getIdList());
            }
                        if(queryBoExt.getPlatformNameEnList()!=null && queryBoExt.getPlatformNameEnList().size()>0){
                query.in(UrlRuleProcessor311Config12.PLATFORM_NAME_EN,queryBoExt.getPlatformNameEnList());
            }
                        if(queryBoExt.getRefererList()!=null && queryBoExt.getRefererList().size()>0){
                query.in(UrlRuleProcessor311Config12.REFERER,queryBoExt.getRefererList());
            }
                        if(queryBoExt.getUrlPrefixList()!=null && queryBoExt.getUrlPrefixList().size()>0){
                query.in(UrlRuleProcessor311Config12.URL_PREFIX,queryBoExt.getUrlPrefixList());
            }
                        if(queryBoExt.getUrlRuleJsonList()!=null && queryBoExt.getUrlRuleJsonList().size()>0){
                query.in(UrlRuleProcessor311Config12.URL_RULE_JSON,queryBoExt.getUrlRuleJsonList());
            }
                        if(queryBoExt.getTitleRuleJsonList()!=null && queryBoExt.getTitleRuleJsonList().size()>0){
                query.in(UrlRuleProcessor311Config12.TITLE_RULE_JSON,queryBoExt.getTitleRuleJsonList());
            }
                        if(queryBoExt.getPulishTimeRuleJsonList()!=null && queryBoExt.getPulishTimeRuleJsonList().size()>0){
                query.in(UrlRuleProcessor311Config12.PULISH_TIME_RULE_JSON,queryBoExt.getPulishTimeRuleJsonList());
            }
                        if(queryBoExt.getAuthorRuleJsonList()!=null && queryBoExt.getAuthorRuleJsonList().size()>0){
                query.in(UrlRuleProcessor311Config12.AUTHOR_RULE_JSON,queryBoExt.getAuthorRuleJsonList());
            }
                        if(queryBoExt.getTagsRuleJsonList()!=null && queryBoExt.getTagsRuleJsonList().size()>0){
                query.in(UrlRuleProcessor311Config12.TAGS_RULE_JSON,queryBoExt.getTagsRuleJsonList());
            }
                        if(queryBoExt.getDateDeleteList()!=null && queryBoExt.getDateDeleteList().size()>0){
                query.in(UrlRuleProcessor311Config12.DATE_DELETE,queryBoExt.getDateDeleteList());
            }
                        if(queryBoExt.getDomainList()!=null && queryBoExt.getDomainList().size()>0){
                query.in(UrlRuleProcessor311Config12.DOMAIN,queryBoExt.getDomainList());
            }
                        if(queryBoExt.getBlogsAuthorIndexUrlTypeList()!=null && queryBoExt.getBlogsAuthorIndexUrlTypeList().size()>0){
                query.in(UrlRuleProcessor311Config12.BLOGS_AUTHOR_INDEX_URL_TYPE,queryBoExt.getBlogsAuthorIndexUrlTypeList());
            }
                        if(queryBoExt.getCreateTimeList()!=null && queryBoExt.getCreateTimeList().size()>0){
                query.in(UrlRuleProcessor311Config12.CREATE_TIME,queryBoExt.getCreateTimeList());
            }
                        if(queryBoExt.getUpdateTimeList()!=null && queryBoExt.getUpdateTimeList().size()>0){
                query.in(UrlRuleProcessor311Config12.UPDATE_TIME,queryBoExt.getUpdateTimeList());
            }
                        if(queryBoExt.getStatusList()!=null && queryBoExt.getStatusList().size()>0){
                query.in(UrlRuleProcessor311Config12.STATUS,queryBoExt.getStatusList());
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


	public List<QueryBoFuzzy> getQueryBoFuzzyList() {
		return queryBoFuzzyList;
	}

	public void setQueryBoFuzzyList(List<QueryBoFuzzy> queryBoFuzzyList) {
		this.queryBoFuzzyList = queryBoFuzzyList;
	}

	public QueryBoExt getQueryBoExt() {
		return queryBoExt;
	}

	public void setQueryBoExt(QueryBoExt queryBoExt) {
		this.queryBoExt = queryBoExt;
	}

	/**
	* 模糊查询的字段 - 注意，是全模糊
	*/
	public static class QueryBoFuzzy {
		/**
		 * 字符名称 - 数据库的
		 */
		private String column;
		/**
		 * 值
		 */
		private String value;

		public String getColumn() {
			return column;
		}

		public void setColumn(String column) {
			this.column = column;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("QueryBoFuzzy{");
			sb.append(super.toString());
			sb.append(",");
			sb.append("                column='").append(column).append('\'');
			sb.append(",                 value='").append(value).append('\'');
			sb.append('}');
			return sb.toString();
		}
	}
    /**
    * 用于排序，和批量的查询
    */
    public static class QueryBoExt {
		/**
		 * 一般情况下只需要单个列的排序
		 */
		private String orderColumn;

		/**
		 * 默认正序
		 */
		private boolean asc = true;

		        /**
         * 
         * 集合
         */
        private List<Integer> idList;
		        /**
         * 平台名称-英文名称
         * 集合
         */
        private List<String> platformNameEnList;
		        /**
         * 访问时，来源链接
         * 集合
         */
        private List<String> refererList;
		        /**
         * 访问的前缀
         * 集合
         */
        private List<String> urlPrefixList;
		        /**
         * 抓取URL的规则
         * 集合
         */
        private List<String> urlRuleJsonList;
		        /**
         * 抓取标题的规则
         * 集合
         */
        private List<String> titleRuleJsonList;
		        /**
         * 发表时间的规则
         * 集合
         */
        private List<String> pulishTimeRuleJsonList;
		        /**
         * 作者的规则
         * 集合
         */
        private List<String> authorRuleJsonList;
		        /**
         * 标签的规则
         * 集合
         */
        private List<String> tagsRuleJsonList;
		        /**
         * 删除时间，0未删除
         * 集合
         */
        private List<Integer> dateDeleteList;
		        /**
         * 域名
         * 集合
         */
        private List<String> domainList;
		        /**
         * 对应表中的type数值
         * 集合
         */
        private List<Integer> blogsAuthorIndexUrlTypeList;
		        /**
         * 
         * 集合
         */
        private List<Date> createTimeList;
		        /**
         * 
         * 集合
         */
        private List<Date> updateTimeList;
		        /**
         * 状态，0-正常，1-禁用
         * 集合
         */
        private List<Integer> statusList;
		

                public List<Integer> getIdList() {
            return idList;
        }

        public void setIdList(List<Integer> idList) {
            this.idList = idList;
        }
                public List<String> getPlatformNameEnList() {
            return platformNameEnList;
        }

        public void setPlatformNameEnList(List<String> platformNameEnList) {
            this.platformNameEnList = platformNameEnList;
        }
                public List<String> getRefererList() {
            return refererList;
        }

        public void setRefererList(List<String> refererList) {
            this.refererList = refererList;
        }
                public List<String> getUrlPrefixList() {
            return urlPrefixList;
        }

        public void setUrlPrefixList(List<String> urlPrefixList) {
            this.urlPrefixList = urlPrefixList;
        }
                public List<String> getUrlRuleJsonList() {
            return urlRuleJsonList;
        }

        public void setUrlRuleJsonList(List<String> urlRuleJsonList) {
            this.urlRuleJsonList = urlRuleJsonList;
        }
                public List<String> getTitleRuleJsonList() {
            return titleRuleJsonList;
        }

        public void setTitleRuleJsonList(List<String> titleRuleJsonList) {
            this.titleRuleJsonList = titleRuleJsonList;
        }
                public List<String> getPulishTimeRuleJsonList() {
            return pulishTimeRuleJsonList;
        }

        public void setPulishTimeRuleJsonList(List<String> pulishTimeRuleJsonList) {
            this.pulishTimeRuleJsonList = pulishTimeRuleJsonList;
        }
                public List<String> getAuthorRuleJsonList() {
            return authorRuleJsonList;
        }

        public void setAuthorRuleJsonList(List<String> authorRuleJsonList) {
            this.authorRuleJsonList = authorRuleJsonList;
        }
                public List<String> getTagsRuleJsonList() {
            return tagsRuleJsonList;
        }

        public void setTagsRuleJsonList(List<String> tagsRuleJsonList) {
            this.tagsRuleJsonList = tagsRuleJsonList;
        }
                public List<Integer> getDateDeleteList() {
            return dateDeleteList;
        }

        public void setDateDeleteList(List<Integer> dateDeleteList) {
            this.dateDeleteList = dateDeleteList;
        }
                public List<String> getDomainList() {
            return domainList;
        }

        public void setDomainList(List<String> domainList) {
            this.domainList = domainList;
        }
                public List<Integer> getBlogsAuthorIndexUrlTypeList() {
            return blogsAuthorIndexUrlTypeList;
        }

        public void setBlogsAuthorIndexUrlTypeList(List<Integer> blogsAuthorIndexUrlTypeList) {
            this.blogsAuthorIndexUrlTypeList = blogsAuthorIndexUrlTypeList;
        }
                public List<Date> getCreateTimeList() {
            return createTimeList;
        }

        public void setCreateTimeList(List<Date> createTimeList) {
            this.createTimeList = createTimeList;
        }
                public List<Date> getUpdateTimeList() {
            return updateTimeList;
        }

        public void setUpdateTimeList(List<Date> updateTimeList) {
            this.updateTimeList = updateTimeList;
        }
                public List<Integer> getStatusList() {
            return statusList;
        }

        public void setStatusList(List<Integer> statusList) {
            this.statusList = statusList;
        }
        
        public String getOrderColumn() {
			return orderColumn;
		}

		public void setOrderColumn(String orderColumn) {
			this.orderColumn = orderColumn;
		}

		public boolean isAsc() {
			return asc;
		}

		public void setAsc(boolean asc) {
			this.asc = asc;
		}
	}


}
