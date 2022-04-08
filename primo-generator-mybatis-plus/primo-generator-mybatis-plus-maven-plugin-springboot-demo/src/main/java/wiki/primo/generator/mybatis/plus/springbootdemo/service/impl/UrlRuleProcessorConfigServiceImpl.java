package wiki.primo.generator.mybatis.plus.springbootdemo.service.impl;

import wiki.primo.generator.mybatis.plus.springbootdemo.entity.UrlRuleProcessorConfig;
import wiki.primo.generator.mybatis.plus.springbootdemo.mapper.UrlRuleProcessorConfigMapper;
import wiki.primo.generator.mybatis.plus.springbootdemo.service.IUrlRuleProcessorConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wiki.primo.generator.mybatis.plus.springbootdemo.query.UrlRuleProcessorConfigQueryBo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 抓取URL的配置规则  服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2022-04-08 14:02:39
 */
@Service
public class UrlRuleProcessorConfigServiceImpl extends ServiceImpl<UrlRuleProcessorConfigMapper, UrlRuleProcessorConfig> implements IUrlRuleProcessorConfigService {

    /**
     * 按照条件分页查询
     * @param query
     */
    @Override
    public IPage<UrlRuleProcessorConfig> page(UrlRuleProcessorConfigQueryBo query,int pageNo,int pageSize){
        if(query==null){
            return new Page<>();
        }
        IPage<UrlRuleProcessorConfig> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        QueryWrapper<UrlRuleProcessorConfig> queryWrapper = query.buildQuery();
        return this.page(page, queryWrapper);
    }

    @Override
    public UrlRuleProcessorConfig getOne(UrlRuleProcessorConfigQueryBo query) {
        QueryWrapper<UrlRuleProcessorConfig> queryWrapper = query.buildQuery();
        return this.getOne(queryWrapper);
    }

    @Override
    public IPage<UrlRuleProcessorConfig> page(QueryWrapper<UrlRuleProcessorConfig> queryWrapper, int pageNo, int pageSize) {
        return getPage(queryWrapper,pageNo,pageSize,true);
    }

    @Override
    public IPage<UrlRuleProcessorConfig> pageNoCount(QueryWrapper<UrlRuleProcessorConfig> queryWrapper, int pageNo, int pageSize) {
        return getPage(queryWrapper,pageNo,pageSize,false);
    }

    private IPage<UrlRuleProcessorConfig> getPage(QueryWrapper<UrlRuleProcessorConfig> queryWrapper, int pageNo, int pageSize,boolean isSearchCount) {
        if(queryWrapper ==null){
            return new Page<>();
        }
        Page<UrlRuleProcessorConfig> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        page.setSearchCount(isSearchCount);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean update(UrlRuleProcessorConfig entity,UrlRuleProcessorConfigQueryBo query) {
        if(entity==null || query==null){
            return false;
        }
        return this.update(entity,query.buildQuery());
    }

    @Override
    public boolean updateByColumn(UrlRuleProcessorConfig entity, String column, Object value) {
        QueryWrapper<UrlRuleProcessorConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column,value);
        return this.update(entity, queryWrapper);
    }

    @Override
    public List<UrlRuleProcessorConfig> list(UrlRuleProcessorConfigQueryBo query) {
        if(query==null){
            return new ArrayList<>(2);
        }
        return this.list(query.buildQuery());
    }

}
