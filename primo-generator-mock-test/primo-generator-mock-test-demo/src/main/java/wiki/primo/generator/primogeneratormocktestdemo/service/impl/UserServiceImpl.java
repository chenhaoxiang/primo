package wiki.primo.generator.primogeneratormocktestdemo.service.impl;

import wiki.primo.generator.primogeneratormocktestdemo.entity.User;
import wiki.primo.generator.primogeneratormocktestdemo.mapper.UserMapper;
import wiki.primo.generator.primogeneratormocktestdemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wiki.primo.generator.primogeneratormocktestdemo.query.UserQueryBo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *   服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2020-12-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * 按照条件分页查询
     * @param query
     */
    @Override
    public IPage<User> page(UserQueryBo query,int pageNo,int pageSize){
        if(query==null){
            return new Page<>();
        }
        IPage<User> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        QueryWrapper<User> queryWrapper = query.buildQuery();
        return this.page(page, queryWrapper);
    }

    @Override
    public User getOne(UserQueryBo query) {
        QueryWrapper<User> queryWrapper = query.buildQuery();
        return this.getOne(queryWrapper);
    }

    @Override
    public IPage<User> page(QueryWrapper<User> queryWrapper, int pageNo, int pageSize) {
        if(queryWrapper==null){
            return new Page<>();
        }
        IPage<User> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean update(User entity,UserQueryBo query) {
        if(entity==null || query==null){
            return false;
        }
        return this.update(entity,query.buildQuery());
    }

    @Override
    public boolean updateByColumn(User entity, String column, Object value) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column,value);
        return this.update(entity, queryWrapper);
    }

    @Override
    public List<User> list(UserQueryBo query) {
        if(query==null){
            return new ArrayList<>(2);
        }
        return this.list(query.buildQuery());
    }

}
