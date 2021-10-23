package wiki.primo.generator.mybatis.plus.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
						import java.math.BigDecimal;
	
/**
 * <p>
 * 基金净值与相对盈亏记录
 * </p>
 *
 * @author chenhx
 * @since 2021-10-23 00:20:25
 */
@TableName("`fund_record`")
public class FundRecord implements Serializable {

private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
    @TableId(type = IdType.AUTO)
	@TableField(value="`id`")
	private Integer id;

	/**
	 * 创建时间
	 */
	@TableField(value="`create_time`")
	private Date createTime;

	/**
	 * 修改时间
	 */
	@TableField(value="`update_time`")
	private Date updateTime;

	/**
	 * 基金公司代码
	 */
	@TableField(value="`code`")
	private String code;

	/**
	 * 基金公司名称
	 */
	@TableField(value="`name`")
	private String name;

	/**
	 * 当天的净值
	 */
	@TableField(value="`worth`")
	private BigDecimal worth;

	/**
	 * 当天的估值，不一定有。单位都统一的
	 */
	@TableField(value="`appraisement`")
	private BigDecimal appraisement;

	/**
	 * 昨天的净值，当为起始时，昨天净值为0
	 */
	@TableField(value="`yesterday_worth`")
	private BigDecimal yesterdayWorth;

	/**
	 * 净值日期，格式：2021-06-28
	 */
	@TableField(value="`time`")
	private String time;

	/**
	 * 相对于昨天净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
	 */
	@TableField(value="`ups_and_downs_day1`")
	private BigDecimal upsAndDownsDay1;

	/**
	 * 相对于7天前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
	 */
	@TableField(value="`ups_and_downs_day7`")
	private BigDecimal upsAndDownsDay7;

	/**
	 * 相对于30天前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
	 */
	@TableField(value="`ups_and_downs_day30`")
	private BigDecimal upsAndDownsDay30;

	/**
	 * 相对于365天前净值的涨跌百分，负数为-。例如为10时，涨10%，当为-10时，跌10%
	 */
	@TableField(value="`ups_and_downs_day365`")
	private BigDecimal upsAndDownsDay365;

	/**
	 * 相对于3年前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
	 */
	@TableField(value="`ups_and_downs_day1095`")
	private BigDecimal upsAndDownsDay1095;

	/**
	 * 相对于5年前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
	 */
	@TableField(value="`ups_and_downs_day1825`")
	private BigDecimal upsAndDownsDay1825;

	/**
	 * 从始至今开始的涨跌
	 */
	@TableField(value="`ups_and_downs`")
	private BigDecimal upsAndDowns;

	/**
	 * 昨天净值的id,当该值为0时，说明是起始.如果为1，说明没有填，需要补好
	 */
	@TableField(value="`yesterday_fund_record_id`")
	private Integer yesterdayFundRecordId;


	public static final String ID="id";
	public static final String CREATE_TIME="create_time";
	public static final String UPDATE_TIME="update_time";
	public static final String CODE="code";
	public static final String NAME="name";
	public static final String WORTH="worth";
	public static final String APPRAISEMENT="appraisement";
	public static final String YESTERDAY_WORTH="yesterday_worth";
	public static final String TIME="time";
	public static final String UPS_AND_DOWNS_DAY1="ups_and_downs_day1";
	public static final String UPS_AND_DOWNS_DAY7="ups_and_downs_day7";
	public static final String UPS_AND_DOWNS_DAY30="ups_and_downs_day30";
	public static final String UPS_AND_DOWNS_DAY365="ups_and_downs_day365";
	public static final String UPS_AND_DOWNS_DAY1095="ups_and_downs_day1095";
	public static final String UPS_AND_DOWNS_DAY1825="ups_and_downs_day1825";
	public static final String UPS_AND_DOWNS="ups_and_downs";
	public static final String YESTERDAY_FUND_RECORD_ID="yesterday_fund_record_id";

		
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

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("FundRecord{").append(super.toString());

	sb.append(",id=").append(id);
		sb.append(",createTime=").append(createTime);
		sb.append(",updateTime=").append(updateTime);
		sb.append(",code='").append(code).append('\'');
		sb.append(",name='").append(name).append('\'');
		sb.append(",worth=").append(worth);
		sb.append(",appraisement=").append(appraisement);
		sb.append(",yesterdayWorth=").append(yesterdayWorth);
		sb.append(",time='").append(time).append('\'');
		sb.append(",upsAndDownsDay1=").append(upsAndDownsDay1);
		sb.append(",upsAndDownsDay7=").append(upsAndDownsDay7);
		sb.append(",upsAndDownsDay30=").append(upsAndDownsDay30);
		sb.append(",upsAndDownsDay365=").append(upsAndDownsDay365);
		sb.append(",upsAndDownsDay1095=").append(upsAndDownsDay1095);
		sb.append(",upsAndDownsDay1825=").append(upsAndDownsDay1825);
		sb.append(",upsAndDowns=").append(upsAndDowns);
		sb.append(",yesterdayFundRecordId=").append(yesterdayFundRecordId);
	
		sb.append('}');
		return sb.toString();
	}
}
