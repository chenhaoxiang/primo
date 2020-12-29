package wiki.primo.generator.primogeneratormocktestdemo.service.impl;

import wiki.primo.generator.primogeneratormocktestdemo.entity.TableSharding;
import wiki.primo.generator.primogeneratormocktestdemo.mapper.TableShardingMapper;
import wiki.primo.generator.primogeneratormocktestdemo.service.ITableShardingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wiki.primo.generator.primogeneratormocktestdemo.query.TableShardingQueryBo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 分片表1  服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2020-12-29
 */
@Service
public class TableShardingServiceImpl extends ServiceImpl<TableShardingMapper, TableSharding> implements ITableShardingService {

    /**
     * 按照条件分页查询
     * @param query
     */
    @Override
    public IPage<TableSharding> page(TableShardingQueryBo query,int pageNo,int pageSize){
        if(query==null){
            return new Page<>();
        }
        IPage<TableSharding> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        QueryWrapper<TableSharding> queryWrapper = query.buildQuery();
        return this.page(page, queryWrapper);
    }

    @Override
    public TableSharding getOne(TableShardingQueryBo query) {
        QueryWrapper<TableSharding> queryWrapper = query.buildQuery();
        return this.getOne(queryWrapper);
    }

    @Override
    public IPage<TableSharding> page(QueryWrapper<TableSharding> queryWrapper, int pageNo, int pageSize) {
        if(queryWrapper==null){
            return new Page<>();
        }
        IPage<TableSharding> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean update(TableSharding entity,TableShardingQueryBo query) {
        if(entity==null || query==null){
            return false;
        }
        return this.update(entity,query.buildQuery());
    }

    @Override
    public boolean updateByColumn(TableSharding entity, String column, Object value) {
        QueryWrapper<TableSharding> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column,value);
        return this.update(entity, queryWrapper);
    }

    @Override
    public List<TableSharding> list(TableShardingQueryBo query) {
        if(query==null){
            return new ArrayList<>(2);
        }
        return this.list(query.buildQuery());
    }

}
