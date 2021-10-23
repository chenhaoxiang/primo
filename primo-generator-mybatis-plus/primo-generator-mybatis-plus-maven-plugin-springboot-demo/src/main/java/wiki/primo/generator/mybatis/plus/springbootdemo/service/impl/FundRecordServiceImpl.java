package wiki.primo.generator.mybatis.plus.springbootdemo.service.impl;

import wiki.primo.generator.mybatis.plus.springbootdemo.entity.FundRecord;
import wiki.primo.generator.mybatis.plus.springbootdemo.mapper.FundRecordMapper;
import wiki.primo.generator.mybatis.plus.springbootdemo.service.IFundRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wiki.primo.generator.mybatis.plus.springbootdemo.query.FundRecordQueryBo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 基金净值与相对盈亏记录  服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2021-10-23 00:20:25
 */
@Service
public class FundRecordServiceImpl extends ServiceImpl<FundRecordMapper, FundRecord> implements IFundRecordService {

    /**
     * 按照条件分页查询
     * @param query
     */
    @Override
    public IPage<FundRecord> page(FundRecordQueryBo query,int pageNo,int pageSize){
        if(query==null){
            return new Page<>();
        }
        IPage<FundRecord> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        QueryWrapper<FundRecord> queryWrapper = query.buildQuery();
        return this.page(page, queryWrapper);
    }

    @Override
    public FundRecord getOne(FundRecordQueryBo query) {
        QueryWrapper<FundRecord> queryWrapper = query.buildQuery();
        return this.getOne(queryWrapper);
    }

    @Override
    public IPage<FundRecord> page(QueryWrapper<FundRecord> queryWrapper, int pageNo, int pageSize) {
        return getPage(queryWrapper,pageNo,pageSize,true);
    }

    @Override
    public IPage<FundRecord> pageNoCount(QueryWrapper<FundRecord> queryWrapper, int pageNo, int pageSize) {
        return getPage(queryWrapper,pageNo,pageSize,false);
    }

    private IPage<FundRecord> getPage(QueryWrapper<FundRecord> queryWrapper, int pageNo, int pageSize,boolean isSearchCount) {
        if(queryWrapper ==null){
            return new Page<>();
        }
        Page<FundRecord> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        page.setSearchCount(isSearchCount);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean update(FundRecord entity,FundRecordQueryBo query) {
        if(entity==null || query==null){
            return false;
        }
        return this.update(entity,query.buildQuery());
    }

    @Override
    public boolean updateByColumn(FundRecord entity, String column, Object value) {
        QueryWrapper<FundRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column,value);
        return this.update(entity, queryWrapper);
    }

    @Override
    public List<FundRecord> list(FundRecordQueryBo query) {
        if(query==null){
            return new ArrayList<>(2);
        }
        return this.list(query.buildQuery());
    }

}
