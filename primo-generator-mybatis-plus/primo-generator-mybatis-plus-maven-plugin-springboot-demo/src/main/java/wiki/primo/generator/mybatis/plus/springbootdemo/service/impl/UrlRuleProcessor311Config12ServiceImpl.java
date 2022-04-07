package wiki.primo.generator.mybatis.plus.springbootdemo.service.impl;

import wiki.primo.generator.mybatis.plus.springbootdemo.entity.UrlRuleProcessor311Config12;
import wiki.primo.generator.mybatis.plus.springbootdemo.mapper.UrlRuleProcessor311Config12Mapper;
import wiki.primo.generator.mybatis.plus.springbootdemo.service.IUrlRuleProcessor311Config12Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wiki.primo.generator.mybatis.plus.springbootdemo.query.UrlRuleProcessor311Config12QueryBo;
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
 * @since 2022-04-07 18:24:32
 */
@Service
public class UrlRuleProcessor311Config12ServiceImpl extends ServiceImpl<UrlRuleProcessor311Config12Mapper, UrlRuleProcessor311Config12> implements IUrlRuleProcessor311Config12Service {

    /**
     * 按照条件分页查询
     * @param query
     */
    @Override
    public IPage<UrlRuleProcessor311Config12> page(UrlRuleProcessor311Config12QueryBo query,int pageNo,int pageSize){
        if(query==null){
            return new Page<>();
        }
        IPage<UrlRuleProcessor311Config12> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        QueryWrapper<UrlRuleProcessor311Config12> queryWrapper = query.buildQuery();
        return this.page(page, queryWrapper);
    }

    @Override
    public UrlRuleProcessor311Config12 getOne(UrlRuleProcessor311Config12QueryBo query) {
        QueryWrapper<UrlRuleProcessor311Config12> queryWrapper = query.buildQuery();
        return this.getOne(queryWrapper);
    }

    @Override
    public IPage<UrlRuleProcessor311Config12> page(QueryWrapper<UrlRuleProcessor311Config12> queryWrapper, int pageNo, int pageSize) {
        return getPage(queryWrapper,pageNo,pageSize,true);
    }

    @Override
    public IPage<UrlRuleProcessor311Config12> pageNoCount(QueryWrapper<UrlRuleProcessor311Config12> queryWrapper, int pageNo, int pageSize) {
        return getPage(queryWrapper,pageNo,pageSize,false);
    }

    private IPage<UrlRuleProcessor311Config12> getPage(QueryWrapper<UrlRuleProcessor311Config12> queryWrapper, int pageNo, int pageSize,boolean isSearchCount) {
        if(queryWrapper ==null){
            return new Page<>();
        }
        Page<UrlRuleProcessor311Config12> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        page.setSearchCount(isSearchCount);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean update(UrlRuleProcessor311Config12 entity,UrlRuleProcessor311Config12QueryBo query) {
        if(entity==null || query==null){
            return false;
        }
        return this.update(entity,query.buildQuery());
    }

    @Override
    public boolean updateByColumn(UrlRuleProcessor311Config12 entity, String column, Object value) {
        QueryWrapper<UrlRuleProcessor311Config12> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column,value);
        return this.update(entity, queryWrapper);
    }

    @Override
    public List<UrlRuleProcessor311Config12> list(UrlRuleProcessor311Config12QueryBo query) {
        if(query==null){
            return new ArrayList<>(2);
        }
        return this.list(query.buildQuery());
    }

}
