package wiki.primo.generator.primogeneratormocktestdemo.service.impl;

import wiki.primo.generator.primogeneratormocktestdemo.entity.UserTemplate;
import wiki.primo.generator.primogeneratormocktestdemo.mapper.UserTemplateMapper;
import wiki.primo.generator.primogeneratormocktestdemo.service.IUserTemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wiki.primo.generator.primogeneratormocktestdemo.query.UserTemplateQueryBo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户-模板表  服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2020-12-29
 */
@Service
public class UserTemplateServiceImpl extends ServiceImpl<UserTemplateMapper, UserTemplate> implements IUserTemplateService {

    /**
     * 按照条件分页查询
     * @param query
     */
    @Override
    public IPage<UserTemplate> page(UserTemplateQueryBo query,int pageNo,int pageSize){
        if(query==null){
            return new Page<>();
        }
        IPage<UserTemplate> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        QueryWrapper<UserTemplate> queryWrapper = query.buildQuery();
        return this.page(page, queryWrapper);
    }

    @Override
    public UserTemplate getOne(UserTemplateQueryBo query) {
        QueryWrapper<UserTemplate> queryWrapper = query.buildQuery();
        return this.getOne(queryWrapper);
    }

    @Override
    public IPage<UserTemplate> page(QueryWrapper<UserTemplate> queryWrapper, int pageNo, int pageSize) {
        if(queryWrapper==null){
            return new Page<>();
        }
        IPage<UserTemplate> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean update(UserTemplate entity,UserTemplateQueryBo query) {
        if(entity==null || query==null){
            return false;
        }
        return this.update(entity,query.buildQuery());
    }

    @Override
    public boolean updateByColumn(UserTemplate entity, String column, Object value) {
        QueryWrapper<UserTemplate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column,value);
        return this.update(entity, queryWrapper);
    }

    @Override
    public List<UserTemplate> list(UserTemplateQueryBo query) {
        if(query==null){
            return new ArrayList<>(2);
        }
        return this.list(query.buildQuery());
    }

}
