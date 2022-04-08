package wiki.primo.generator.mybatis.plus.springbootdemo.service.ext.impl;

import wiki.primo.generator.mybatis.plus.springbootdemo.service.ext.IUrlRuleProcessorConfigServiceExt;
import wiki.primo.generator.mybatis.plus.springbootdemo.service.IUrlRuleProcessorConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 抓取URL的配置规则  服务实现扩展类，该类不会被自动生成覆盖掉
 * </p>
 *
 * @author chenhx
 * @since 2022-04-08 14:01:06
 */
@Service
public class UrlRuleProcessorConfigServiceExtImpl implements IUrlRuleProcessorConfigServiceExt {
    @Autowired
    private IUrlRuleProcessorConfigService iUrlRuleProcessorConfigService;


}
