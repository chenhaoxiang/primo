package wiki.primo.generator.mybatis.plus.springbootdemo.service.ext.impl;

import wiki.primo.generator.mybatis.plus.springbootdemo.service.ext.IUrlRuleWebDriverConfigServiceExt;
import wiki.primo.generator.mybatis.plus.springbootdemo.service.IUrlRuleWebDriverConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 抓取URL的配置规则-通过无头浏览器  服务实现扩展类，该类不会被自动生成覆盖掉
 * </p>
 *
 * @author chenhx
 * @since 2022-04-08 14:01:06
 */
@Service
public class UrlRuleWebDriverConfigServiceExtImpl implements IUrlRuleWebDriverConfigServiceExt {
    @Autowired
    private IUrlRuleWebDriverConfigService iUrlRuleWebDriverConfigService;


}
