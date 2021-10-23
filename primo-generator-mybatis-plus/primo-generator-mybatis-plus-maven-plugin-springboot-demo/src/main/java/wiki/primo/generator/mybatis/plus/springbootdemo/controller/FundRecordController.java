package wiki.primo.generator.mybatis.plus.springbootdemo.controller;

import wiki.primo.generator.mybatis.plus.springbootdemo.service.IFundRecordService;
import wiki.primo.generator.mybatis.plus.springbootdemo.entity.FundRecord;
import wiki.primo.generator.mybatis.plus.springbootdemo.result.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wiki.primo.generator.mybatis.plus.springbootdemo.query.FundRecordQueryBo;
import java.util.Date;
                        import java.math.BigDecimal;


/**
 * 基金净值与相对盈亏记录  前端控制器
 *
 * @author chenhx
 * @since 2021-10-23 00:20:25
 */
@Controller
@RequestMapping("/fundrecord")
public class FundRecordController {

    @Autowired
    private IFundRecordService iFundRecordService;

    /**
     * 添加对象
     * @param createTime 创建时间
     * @param updateTime 修改时间
     * @param code 基金公司代码
     * @param name 基金公司名称
     * @param worth 当天的净值
     * @param appraisement 当天的估值，不一定有。单位都统一的
     * @param yesterdayWorth 昨天的净值，当为起始时，昨天净值为0
     * @param time 净值日期，格式：2021-06-28
     * @param upsAndDownsDay1 相对于昨天净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDownsDay7 相对于7天前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDownsDay30 相对于30天前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDownsDay365 相对于365天前净值的涨跌百分，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDownsDay1095 相对于3年前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDownsDay1825 相对于5年前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDowns 从始至今开始的涨跌
     * @param yesterdayFundRecordId 昨天净值的id,当该值为0时，说明是起始.如果为1，说明没有填，需要补好
     * @return ResultModel统一响应结果
     */
    @PostMapping("save")
    @ResponseBody
    public ResultModel<Object> save(Date createTime,Date updateTime,String code,String name,BigDecimal worth,BigDecimal appraisement,BigDecimal yesterdayWorth,String time,BigDecimal upsAndDownsDay1,BigDecimal upsAndDownsDay7,BigDecimal upsAndDownsDay30,BigDecimal upsAndDownsDay365,BigDecimal upsAndDownsDay1095,BigDecimal upsAndDownsDay1825,BigDecimal upsAndDowns,Integer yesterdayFundRecordId        ) {
        FundRecord fundRecord = new FundRecord();
        //遍历set
        fundRecord.setCreateTime(createTime);
        fundRecord.setUpdateTime(updateTime);
        fundRecord.setCode(code);
        fundRecord.setName(name);
        fundRecord.setWorth(worth);
        fundRecord.setAppraisement(appraisement);
        fundRecord.setYesterdayWorth(yesterdayWorth);
        fundRecord.setTime(time);
        fundRecord.setUpsAndDownsDay1(upsAndDownsDay1);
        fundRecord.setUpsAndDownsDay7(upsAndDownsDay7);
        fundRecord.setUpsAndDownsDay30(upsAndDownsDay30);
        fundRecord.setUpsAndDownsDay365(upsAndDownsDay365);
        fundRecord.setUpsAndDownsDay1095(upsAndDownsDay1095);
        fundRecord.setUpsAndDownsDay1825(upsAndDownsDay1825);
        fundRecord.setUpsAndDowns(upsAndDowns);
        fundRecord.setYesterdayFundRecordId(yesterdayFundRecordId);
        iFundRecordService.save(fundRecord);
        return ResultModel.success();
    }

    /**
     * 根据ID进行删除
     *
     * @param id 主键
     * @return ResultModel统一响应结果
     */
    @GetMapping("removeById")
    @ResponseBody
    public ResultModel<Object> delete(@RequestParam Integer id) {
        if(id==null || id<0){
            return ResultModel.parameterError();
        }
        boolean success = iFundRecordService.removeById(id);
        if(success) {
            return ResultModel.success();
        }else {
            return ResultModel.fail();
        }
    }

    /**
     * 根据ID进行修改对象
     *
     * @param id
     * @param createTime 创建时间
     * @param updateTime 修改时间
     * @param code 基金公司代码
     * @param name 基金公司名称
     * @param worth 当天的净值
     * @param appraisement 当天的估值，不一定有。单位都统一的
     * @param yesterdayWorth 昨天的净值，当为起始时，昨天净值为0
     * @param time 净值日期，格式：2021-06-28
     * @param upsAndDownsDay1 相对于昨天净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDownsDay7 相对于7天前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDownsDay30 相对于30天前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDownsDay365 相对于365天前净值的涨跌百分，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDownsDay1095 相对于3年前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDownsDay1825 相对于5年前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDowns 从始至今开始的涨跌
     * @param yesterdayFundRecordId 昨天净值的id,当该值为0时，说明是起始.如果为1，说明没有填，需要补好
     * @return ResultModel统一响应结果
     */
    @PostMapping("update")
    @ResponseBody
    public ResultModel<Object> update(Integer id, Date createTime, Date updateTime, String code, String name, BigDecimal worth, BigDecimal appraisement, BigDecimal yesterdayWorth, String time, BigDecimal upsAndDownsDay1, BigDecimal upsAndDownsDay7, BigDecimal upsAndDownsDay30, BigDecimal upsAndDownsDay365, BigDecimal upsAndDownsDay1095, BigDecimal upsAndDownsDay1825, BigDecimal upsAndDowns, Integer yesterdayFundRecordId  ) {
        if(id==null){
            return ResultModel.parameterError();
        }
        FundRecord fundRecord = new FundRecord();
        //遍历set
        fundRecord.setId(id);
        fundRecord.setCreateTime(createTime);
        fundRecord.setUpdateTime(updateTime);
        fundRecord.setCode(code);
        fundRecord.setName(name);
        fundRecord.setWorth(worth);
        fundRecord.setAppraisement(appraisement);
        fundRecord.setYesterdayWorth(yesterdayWorth);
        fundRecord.setTime(time);
        fundRecord.setUpsAndDownsDay1(upsAndDownsDay1);
        fundRecord.setUpsAndDownsDay7(upsAndDownsDay7);
        fundRecord.setUpsAndDownsDay30(upsAndDownsDay30);
        fundRecord.setUpsAndDownsDay365(upsAndDownsDay365);
        fundRecord.setUpsAndDownsDay1095(upsAndDownsDay1095);
        fundRecord.setUpsAndDownsDay1825(upsAndDownsDay1825);
        fundRecord.setUpsAndDowns(upsAndDowns);
        fundRecord.setYesterdayFundRecordId(yesterdayFundRecordId);
        boolean success = iFundRecordService.updateById(fundRecord);
        if(success) {
            return ResultModel.success();
        }else {
            return ResultModel.fail();
        }
    }

    /**
     * 查询详情
     *
     * @param id 主键
     * @return ResultModel统一响应结果
     */
    @GetMapping("getById")
    @ResponseBody
    public ResultModel<FundRecord> getById(@RequestParam Integer id) {
        if(id==null || id<0){
            return ResultModel.parameterError();
        }
        FundRecord fundRecord = iFundRecordService.getById(id);
        return ResultModel.success(fundRecord);
    }

    /**
     * 分页查询
     * @param id
     * @param createTime 创建时间
     * @param updateTime 修改时间
     * @param code 基金公司代码
     * @param name 基金公司名称
     * @param worth 当天的净值
     * @param appraisement 当天的估值，不一定有。单位都统一的
     * @param yesterdayWorth 昨天的净值，当为起始时，昨天净值为0
     * @param time 净值日期，格式：2021-06-28
     * @param upsAndDownsDay1 相对于昨天净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDownsDay7 相对于7天前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDownsDay30 相对于30天前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDownsDay365 相对于365天前净值的涨跌百分，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDownsDay1095 相对于3年前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDownsDay1825 相对于5年前净值的涨跌百分比，负数为-。例如为10时，涨10%，当为-10时，跌10%
     * @param upsAndDowns 从始至今开始的涨跌
     * @param yesterdayFundRecordId 昨天净值的id,当该值为0时，说明是起始.如果为1，说明没有填，需要补好
     * @param pageNo 当前页
     * @param pageSize 一页的数据量
     * @return ResultModel统一响应结果
     */
    @PostMapping("page")
    @ResponseBody
    public ResultModel<IPage<FundRecord>> page(Integer id,Date createTime,Date updateTime,String code,String name,BigDecimal worth,BigDecimal appraisement,BigDecimal yesterdayWorth,String time,BigDecimal upsAndDownsDay1,BigDecimal upsAndDownsDay7,BigDecimal upsAndDownsDay30,BigDecimal upsAndDownsDay365,BigDecimal upsAndDownsDay1095,BigDecimal upsAndDownsDay1825,BigDecimal upsAndDowns,Integer yesterdayFundRecordId, Integer pageNo,Integer pageSize) {
        FundRecordQueryBo queryBo = new FundRecordQueryBo();
        //遍历set
        queryBo.setId(id);
        queryBo.setCreateTime(createTime);
        queryBo.setUpdateTime(updateTime);
        queryBo.setCode(code);
        queryBo.setName(name);
        queryBo.setWorth(worth);
        queryBo.setAppraisement(appraisement);
        queryBo.setYesterdayWorth(yesterdayWorth);
        queryBo.setTime(time);
        queryBo.setUpsAndDownsDay1(upsAndDownsDay1);
        queryBo.setUpsAndDownsDay7(upsAndDownsDay7);
        queryBo.setUpsAndDownsDay30(upsAndDownsDay30);
        queryBo.setUpsAndDownsDay365(upsAndDownsDay365);
        queryBo.setUpsAndDownsDay1095(upsAndDownsDay1095);
        queryBo.setUpsAndDownsDay1825(upsAndDownsDay1825);
        queryBo.setUpsAndDowns(upsAndDowns);
        queryBo.setYesterdayFundRecordId(yesterdayFundRecordId);
        IPage<FundRecord> templateIPage = iFundRecordService.page(queryBo,pageNo,pageSize);
        return ResultModel.success(templateIPage);
    }

}
