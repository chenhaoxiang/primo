package wiki.primo.generator.mybatis.plus.springbootdemo.service.impl;

import wiki.primo.generator.mybatis.plus.springbootdemo.entity.UrlRuleWebDriverConfig;
import wiki.primo.generator.mybatis.plus.springbootdemo.mapper.UrlRuleWebDriverConfigMapper;
import wiki.primo.generator.mybatis.plus.springbootdemo.service.IUrlRuleWebDriverConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wiki.primo.generator.mybatis.plus.springbootdemo.query.UrlRuleWebDriverConfigQueryBo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 抓取URL的配置规则-通过无头浏览器  服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2022-04-08 14:34:43
 */
@Service
public class UrlRuleWebDriverConfigServiceImpl extends ServiceImpl<UrlRuleWebDriverConfigMapper, UrlRuleWebDriverConfig> implements IUrlRuleWebDriverConfigService {

    /**
     * 按照条件分页查询
     * @param query
     */
    @Override
    public IPage<UrlRuleWebDriverConfig> page(UrlRuleWebDriverConfigQueryBo query,int pageNo,int pageSize){
        if(query==null){
            return new Page<>();
        }
        IPage<UrlRuleWebDriverConfig> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        QueryWrapper<UrlRuleWebDriverConfig> queryWrapper = query.buildQuery();
        return this.page(page, queryWrapper);
    }

    @Override
    public UrlRuleWebDriverConfig getOne(UrlRuleWebDriverConfigQueryBo query) {
        QueryWrapper<UrlRuleWebDriverConfig> queryWrapper = query.buildQuery();
        return this.getOne(queryWrapper);
    }

    @Override
    public IPage<UrlRuleWebDriverConfig> page(QueryWrapper<UrlRuleWebDriverConfig> queryWrapper, int pageNo, int pageSize) {
        return getPage(queryWrapper,pageNo,pageSize,true);
    }

    @Override
    public IPage<UrlRuleWebDriverConfig> pageNoCount(QueryWrapper<UrlRuleWebDriverConfig> queryWrapper, int pageNo, int pageSize) {
        return getPage(queryWrapper,pageNo,pageSize,false);
    }

    private IPage<UrlRuleWebDriverConfig> getPage(QueryWrapper<UrlRuleWebDriverConfig> queryWrapper, int pageNo, int pageSize,boolean isSearchCount) {
        if(queryWrapper ==null){
            return new Page<>();
        }
        Page<UrlRuleWebDriverConfig> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        page.setSearchCount(isSearchCount);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean update(UrlRuleWebDriverConfig entity,UrlRuleWebDriverConfigQueryBo query) {
        if(entity==null || query==null){
            return false;
        }
        return this.update(entity,query.buildQuery());
    }

    @Override
    public boolean updateByColumn(UrlRuleWebDriverConfig entity, String column, Object value) {
        QueryWrapper<UrlRuleWebDriverConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column,value);
        return this.update(entity, queryWrapper);
    }

    @Override
    public List<UrlRuleWebDriverConfig> list(UrlRuleWebDriverConfigQueryBo query) {
        if(query==null){
            return new ArrayList<>(2);
        }
        return this.list(query.buildQuery());
    }

}
