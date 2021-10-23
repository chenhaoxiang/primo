package wiki.primo.generator.mybatis.plus.springbootdemo.service.ext.impl;

import wiki.primo.generator.mybatis.plus.springbootdemo.service.ext.IFundRecordServiceExt;
import wiki.primo.generator.mybatis.plus.springbootdemo.service.IFundRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 基金净值与相对盈亏记录  服务实现扩展类，该类不会被自动生成覆盖掉
 * </p>
 *
 * @author chenhx
 * @since 2021-10-15 17:57:35
 */
@Service
public class FundRecordServiceExtImpl implements IFundRecordServiceExt {
    @Autowired
    private IFundRecordService iFundRecordService;


}
