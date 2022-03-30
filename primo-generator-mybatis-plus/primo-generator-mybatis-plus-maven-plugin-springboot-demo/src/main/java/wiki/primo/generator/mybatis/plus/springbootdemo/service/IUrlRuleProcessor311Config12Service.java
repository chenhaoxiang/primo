package wiki.primo.generator.mybatis.plus.springbootdemo.service;

import wiki.primo.generator.mybatis.plus.springbootdemo.entity.UrlRuleProcessor311Config12;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wiki.primo.generator.mybatis.plus.springbootdemo.query.UrlRuleProcessor311Config12QueryBo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chenhx
 * @since 2022-03-30 17:28:09
 */
public interface IUrlRuleProcessor311Config12Service extends IService<UrlRuleProcessor311Config12> {
    /**
     * 按照条件分页查询
     * @param query 查询条件
     */
    IPage<UrlRuleProcessor311Config12> page(UrlRuleProcessor311Config12QueryBo query,int pageNo,int pageSize);

    /**
     * 查询单个的，注意，条件需要有唯一建
     * @param query
     * @return
     */
    UrlRuleProcessor311Config12 getOne(UrlRuleProcessor311Config12QueryBo query);

    /**
     * 按照条件分页查询
     * @param queryWrapper 查询条件
     */
    IPage<UrlRuleProcessor311Config12> page(QueryWrapper<UrlRuleProcessor311Config12> queryWrapper,int pageNo,int pageSize);

    /**
     * 按照条件分页查询-不查询总页数，当不需要总页数时，可极大提升查询性能
     * @param queryWrapper 查询条件
     */
    IPage<UrlRuleProcessor311Config12> pageNoCount(QueryWrapper<UrlRuleProcessor311Config12> queryWrapper, int pageNo, int pageSize);
    /**
     * 按照条件更新
     * @param entity
     * @param query
     * @return
     */
    boolean update(UrlRuleProcessor311Config12 entity,UrlRuleProcessor311Config12QueryBo query);

    /**
     * 通过一个相等的条件，修改数据
     * @param entity 修改的数据
     * @param column 数据库列名
     * @param value 条件值
     * @return true-修改成功
     */
    boolean updateByColumn(UrlRuleProcessor311Config12 entity,String column,Object value);

    /**
     * 按照条件查询所有
     * @param query 查询条件
     */
    List<UrlRuleProcessor311Config12> list(UrlRuleProcessor311Config12QueryBo query);

}
