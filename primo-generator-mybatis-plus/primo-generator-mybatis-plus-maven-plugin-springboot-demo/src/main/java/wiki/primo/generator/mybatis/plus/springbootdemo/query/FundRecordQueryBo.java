package wiki.primo.generator.mybatis.plus.springbootdemo.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import wiki.primo.generator.mybatis.plus.springbootdemo.entity.FundRecord;
import java.util.Date;
import java.util.List;

							import java.math.BigDecimal;
		

/**
 * <p>
 * 基金净值与相对盈亏记录
 * 查询条件类
 * </p>
 *
 * @author chenhx
 * @since 2021-10-23 00:20:25
 */
public class FundRecordQueryBo{

	private QueryBoExt queryBoExt;

	/**
	 * 
	 */
	private Integer id;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 基金公司代码
	 */
	private String code;

	/**
	 * 基金公司名称
	 */
	private String name;

	/**
	 * 当天的净值
	 */
	private BigDecimal worth;

	/**
	 * 当天的估值，不一定有。单位都统一的
	 */
	private BigDecimal appraisement;

	/**
	 * 昨天的净值，当为起始时，昨天净值为0
	 */
	private BigDecimal yesterdayWorth;

	/**
	 * 净值日期，格式：2021-06-28
	 */
	private String time;

	/**
	 * 相对于昨天净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
	 */
	private BigDecimal upsAndDownsDay1;

	/**
	 * 相对于7天前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
	 */
	private BigDecimal upsAndDownsDay7;

	/**
	 * 相对于30天前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
	 */
	private BigDecimal upsAndDownsDay30;

	/**
	 * 相对于365天前净值的涨跌百分，负数为-。例如为10时，涨10%，当为-10时，跌10%
	 */
	private BigDecimal upsAndDownsDay365;

	/**
	 * 相对于3年前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
	 */
	private BigDecimal upsAndDownsDay1095;

	/**
	 * 相对于5年前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
	 */
	private BigDecimal upsAndDownsDay1825;

	/**
	 * 从始至今开始的涨跌
	 */
	private BigDecimal upsAndDowns;

	/**
	 * 昨天净值的id,当该值为0时，说明是起始.如果为1，说明没有填，需要补好
	 */
	private Integer yesterdayFundRecordId;


	/**
	 * 构建查询
	 * @return
	 */
	public QueryWrapper<FundRecord> buildQuery() {
		QueryWrapper<FundRecord> query = new QueryWrapper<>();

		if(id!=null){
			query.eq(FundRecord.ID,id);
		}
		if(createTime!=null){
			query.eq(FundRecord.CREATE_TIME,createTime);
		}
		if(updateTime!=null){
			query.eq(FundRecord.UPDATE_TIME,updateTime);
		}
		if(code!=null){
			query.eq(FundRecord.CODE,code);
		}
		if(name!=null){
			query.eq(FundRecord.NAME,name);
		}
		if(worth!=null){
			query.eq(FundRecord.WORTH,worth);
		}
		if(appraisement!=null){
			query.eq(FundRecord.APPRAISEMENT,appraisement);
		}
		if(yesterdayWorth!=null){
			query.eq(FundRecord.YESTERDAY_WORTH,yesterdayWorth);
		}
		if(time!=null){
			query.eq(FundRecord.TIME,time);
		}
		if(upsAndDownsDay1!=null){
			query.eq(FundRecord.UPS_AND_DOWNS_DAY1,upsAndDownsDay1);
		}
		if(upsAndDownsDay7!=null){
			query.eq(FundRecord.UPS_AND_DOWNS_DAY7,upsAndDownsDay7);
		}
		if(upsAndDownsDay30!=null){
			query.eq(FundRecord.UPS_AND_DOWNS_DAY30,upsAndDownsDay30);
		}
		if(upsAndDownsDay365!=null){
			query.eq(FundRecord.UPS_AND_DOWNS_DAY365,upsAndDownsDay365);
		}
		if(upsAndDownsDay1095!=null){
			query.eq(FundRecord.UPS_AND_DOWNS_DAY1095,upsAndDownsDay1095);
		}
		if(upsAndDownsDay1825!=null){
			query.eq(FundRecord.UPS_AND_DOWNS_DAY1825,upsAndDownsDay1825);
		}
		if(upsAndDowns!=null){
			query.eq(FundRecord.UPS_AND_DOWNS,upsAndDowns);
		}
		if(yesterdayFundRecordId!=null){
			query.eq(FundRecord.YESTERDAY_FUND_RECORD_ID,yesterdayFundRecordId);
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
                query.in(FundRecord.ID,queryBoExt.getIdList());
            }
                        if(queryBoExt.getCreateTimeList()!=null && queryBoExt.getCreateTimeList().size()>0){
                query.in(FundRecord.CREATE_TIME,queryBoExt.getCreateTimeList());
            }
                        if(queryBoExt.getUpdateTimeList()!=null && queryBoExt.getUpdateTimeList().size()>0){
                query.in(FundRecord.UPDATE_TIME,queryBoExt.getUpdateTimeList());
            }
                        if(queryBoExt.getCodeList()!=null && queryBoExt.getCodeList().size()>0){
                query.in(FundRecord.CODE,queryBoExt.getCodeList());
            }
                        if(queryBoExt.getNameList()!=null && queryBoExt.getNameList().size()>0){
                query.in(FundRecord.NAME,queryBoExt.getNameList());
            }
                        if(queryBoExt.getWorthList()!=null && queryBoExt.getWorthList().size()>0){
                query.in(FundRecord.WORTH,queryBoExt.getWorthList());
            }
                        if(queryBoExt.getAppraisementList()!=null && queryBoExt.getAppraisementList().size()>0){
                query.in(FundRecord.APPRAISEMENT,queryBoExt.getAppraisementList());
            }
                        if(queryBoExt.getYesterdayWorthList()!=null && queryBoExt.getYesterdayWorthList().size()>0){
                query.in(FundRecord.YESTERDAY_WORTH,queryBoExt.getYesterdayWorthList());
            }
                        if(queryBoExt.getTimeList()!=null && queryBoExt.getTimeList().size()>0){
                query.in(FundRecord.TIME,queryBoExt.getTimeList());
            }
                        if(queryBoExt.getUpsAndDownsDay1List()!=null && queryBoExt.getUpsAndDownsDay1List().size()>0){
                query.in(FundRecord.UPS_AND_DOWNS_DAY1,queryBoExt.getUpsAndDownsDay1List());
            }
                        if(queryBoExt.getUpsAndDownsDay7List()!=null && queryBoExt.getUpsAndDownsDay7List().size()>0){
                query.in(FundRecord.UPS_AND_DOWNS_DAY7,queryBoExt.getUpsAndDownsDay7List());
            }
                        if(queryBoExt.getUpsAndDownsDay30List()!=null && queryBoExt.getUpsAndDownsDay30List().size()>0){
                query.in(FundRecord.UPS_AND_DOWNS_DAY30,queryBoExt.getUpsAndDownsDay30List());
            }
                        if(queryBoExt.getUpsAndDownsDay365List()!=null && queryBoExt.getUpsAndDownsDay365List().size()>0){
                query.in(FundRecord.UPS_AND_DOWNS_DAY365,queryBoExt.getUpsAndDownsDay365List());
            }
                        if(queryBoExt.getUpsAndDownsDay1095List()!=null && queryBoExt.getUpsAndDownsDay1095List().size()>0){
                query.in(FundRecord.UPS_AND_DOWNS_DAY1095,queryBoExt.getUpsAndDownsDay1095List());
            }
                        if(queryBoExt.getUpsAndDownsDay1825List()!=null && queryBoExt.getUpsAndDownsDay1825List().size()>0){
                query.in(FundRecord.UPS_AND_DOWNS_DAY1825,queryBoExt.getUpsAndDownsDay1825List());
            }
                        if(queryBoExt.getUpsAndDownsList()!=null && queryBoExt.getUpsAndDownsList().size()>0){
                query.in(FundRecord.UPS_AND_DOWNS,queryBoExt.getUpsAndDownsList());
            }
                        if(queryBoExt.getYesterdayFundRecordIdList()!=null && queryBoExt.getYesterdayFundRecordIdList().size()>0){
                query.in(FundRecord.YESTERDAY_FUND_RECORD_ID,queryBoExt.getYesterdayFundRecordIdList());
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
		
	public String getCode() {
        return code;
	}

	public void setCode(String code) {
        this.code = code;
    }
		
	public String getName() {
        return name;
	}

	public void setName(String name) {
        this.name = name;
    }
		
	public BigDecimal getWorth() {
        return worth;
	}

	public void setWorth(BigDecimal worth) {
        this.worth = worth;
    }
		
	public BigDecimal getAppraisement() {
        return appraisement;
	}

	public void setAppraisement(BigDecimal appraisement) {
        this.appraisement = appraisement;
    }
		
	public BigDecimal getYesterdayWorth() {
        return yesterdayWorth;
	}

	public void setYesterdayWorth(BigDecimal yesterdayWorth) {
        this.yesterdayWorth = yesterdayWorth;
    }
		
	public String getTime() {
        return time;
	}

	public void setTime(String time) {
        this.time = time;
    }
		
	public BigDecimal getUpsAndDownsDay1() {
        return upsAndDownsDay1;
	}

	public void setUpsAndDownsDay1(BigDecimal upsAndDownsDay1) {
        this.upsAndDownsDay1 = upsAndDownsDay1;
    }
		
	public BigDecimal getUpsAndDownsDay7() {
        return upsAndDownsDay7;
	}

	public void setUpsAndDownsDay7(BigDecimal upsAndDownsDay7) {
        this.upsAndDownsDay7 = upsAndDownsDay7;
    }
		
	public BigDecimal getUpsAndDownsDay30() {
        return upsAndDownsDay30;
	}

	public void setUpsAndDownsDay30(BigDecimal upsAndDownsDay30) {
        this.upsAndDownsDay30 = upsAndDownsDay30;
    }
		
	public BigDecimal getUpsAndDownsDay365() {
        return upsAndDownsDay365;
	}

	public void setUpsAndDownsDay365(BigDecimal upsAndDownsDay365) {
        this.upsAndDownsDay365 = upsAndDownsDay365;
    }
		
	public BigDecimal getUpsAndDownsDay1095() {
        return upsAndDownsDay1095;
	}

	public void setUpsAndDownsDay1095(BigDecimal upsAndDownsDay1095) {
        this.upsAndDownsDay1095 = upsAndDownsDay1095;
    }
		
	public BigDecimal getUpsAndDownsDay1825() {
        return upsAndDownsDay1825;
	}

	public void setUpsAndDownsDay1825(BigDecimal upsAndDownsDay1825) {
        this.upsAndDownsDay1825 = upsAndDownsDay1825;
    }
		
	public BigDecimal getUpsAndDowns() {
        return upsAndDowns;
	}

	public void setUpsAndDowns(BigDecimal upsAndDowns) {
        this.upsAndDowns = upsAndDowns;
    }
		
	public Integer getYesterdayFundRecordId() {
        return yesterdayFundRecordId;
	}

	public void setYesterdayFundRecordId(Integer yesterdayFundRecordId) {
        this.yesterdayFundRecordId = yesterdayFundRecordId;
    }


	public QueryBoExt getQueryBoExt() {
		return queryBoExt;
	}

	public void setQueryBoExt(QueryBoExt queryBoExt) {
		this.queryBoExt = queryBoExt;
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
         * 创建时间
         * 集合
         */
        private List<Date> createTimeList;
		        /**
         * 修改时间
         * 集合
         */
        private List<Date> updateTimeList;
		        /**
         * 基金公司代码
         * 集合
         */
        private List<String> codeList;
		        /**
         * 基金公司名称
         * 集合
         */
        private List<String> nameList;
		        /**
         * 当天的净值
         * 集合
         */
        private List<BigDecimal> worthList;
		        /**
         * 当天的估值，不一定有。单位都统一的
         * 集合
         */
        private List<BigDecimal> appraisementList;
		        /**
         * 昨天的净值，当为起始时，昨天净值为0
         * 集合
         */
        private List<BigDecimal> yesterdayWorthList;
		        /**
         * 净值日期，格式：2021-06-28
         * 集合
         */
        private List<String> timeList;
		        /**
         * 相对于昨天净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
         * 集合
         */
        private List<BigDecimal> upsAndDownsDay1List;
		        /**
         * 相对于7天前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
         * 集合
         */
        private List<BigDecimal> upsAndDownsDay7List;
		        /**
         * 相对于30天前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
         * 集合
         */
        private List<BigDecimal> upsAndDownsDay30List;
		        /**
         * 相对于365天前净值的涨跌百分，负数为-。例如为10时，涨10%，当为-10时，跌10%
         * 集合
         */
        private List<BigDecimal> upsAndDownsDay365List;
		        /**
         * 相对于3年前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
         * 集合
         */
        private List<BigDecimal> upsAndDownsDay1095List;
		        /**
         * 相对于5年前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
         * 集合
         */
        private List<BigDecimal> upsAndDownsDay1825List;
		        /**
         * 从始至今开始的涨跌
         * 集合
         */
        private List<BigDecimal> upsAndDownsList;
		        /**
         * 昨天净值的id,当该值为0时，说明是起始.如果为1，说明没有填，需要补好
         * 集合
         */
        private List<Integer> yesterdayFundRecordIdList;
		

                public List<Integer> getIdList() {
            return idList;
        }

        public void setIdList(List<Integer> idList) {
            this.idList = idList;
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
                public List<String> getCodeList() {
            return codeList;
        }

        public void setCodeList(List<String> codeList) {
            this.codeList = codeList;
        }
                public List<String> getNameList() {
            return nameList;
        }

        public void setNameList(List<String> nameList) {
            this.nameList = nameList;
        }
                public List<BigDecimal> getWorthList() {
            return worthList;
        }

        public void setWorthList(List<BigDecimal> worthList) {
            this.worthList = worthList;
        }
                public List<BigDecimal> getAppraisementList() {
            return appraisementList;
        }

        public void setAppraisementList(List<BigDecimal> appraisementList) {
            this.appraisementList = appraisementList;
        }
                public List<BigDecimal> getYesterdayWorthList() {
            return yesterdayWorthList;
        }

        public void setYesterdayWorthList(List<BigDecimal> yesterdayWorthList) {
            this.yesterdayWorthList = yesterdayWorthList;
        }
                public List<String> getTimeList() {
            return timeList;
        }

        public void setTimeList(List<String> timeList) {
            this.timeList = timeList;
        }
                public List<BigDecimal> getUpsAndDownsDay1List() {
            return upsAndDownsDay1List;
        }

        public void setUpsAndDownsDay1List(List<BigDecimal> upsAndDownsDay1List) {
            this.upsAndDownsDay1List = upsAndDownsDay1List;
        }
                public List<BigDecimal> getUpsAndDownsDay7List() {
            return upsAndDownsDay7List;
        }

        public void setUpsAndDownsDay7List(List<BigDecimal> upsAndDownsDay7List) {
            this.upsAndDownsDay7List = upsAndDownsDay7List;
        }
                public List<BigDecimal> getUpsAndDownsDay30List() {
            return upsAndDownsDay30List;
        }

        public void setUpsAndDownsDay30List(List<BigDecimal> upsAndDownsDay30List) {
            this.upsAndDownsDay30List = upsAndDownsDay30List;
        }
                public List<BigDecimal> getUpsAndDownsDay365List() {
            return upsAndDownsDay365List;
        }

        public void setUpsAndDownsDay365List(List<BigDecimal> upsAndDownsDay365List) {
            this.upsAndDownsDay365List = upsAndDownsDay365List;
        }
                public List<BigDecimal> getUpsAndDownsDay1095List() {
            return upsAndDownsDay1095List;
        }

        public void setUpsAndDownsDay1095List(List<BigDecimal> upsAndDownsDay1095List) {
            this.upsAndDownsDay1095List = upsAndDownsDay1095List;
        }
                public List<BigDecimal> getUpsAndDownsDay1825List() {
            return upsAndDownsDay1825List;
        }

        public void setUpsAndDownsDay1825List(List<BigDecimal> upsAndDownsDay1825List) {
            this.upsAndDownsDay1825List = upsAndDownsDay1825List;
        }
                public List<BigDecimal> getUpsAndDownsList() {
            return upsAndDownsList;
        }

        public void setUpsAndDownsList(List<BigDecimal> upsAndDownsList) {
            this.upsAndDownsList = upsAndDownsList;
        }
                public List<Integer> getYesterdayFundRecordIdList() {
            return yesterdayFundRecordIdList;
        }

        public void setYesterdayFundRecordIdList(List<Integer> yesterdayFundRecordIdList) {
            this.yesterdayFundRecordIdList = yesterdayFundRecordIdList;
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
