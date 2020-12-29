package wiki.primo.generator.primogeneratormocktestdemo.service;

import wiki.primo.generator.primogeneratormocktestdemo.entity.UserTemplate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wiki.primo.generator.primogeneratormocktestdemo.query.UserTemplateQueryBo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chenhx
 * @since 2020-12-29
 */
public interface IUserTemplateService extends IService<UserTemplate> {
    /**
     * 按照条件分页查询
     * @param query 查询条件
     */
    IPage<UserTemplate> page(UserTemplateQueryBo query,int pageNo,int pageSize);

    /**
     * 查询单个的，注意，条件需要有唯一建
     * @param query
     * @return
     */
    UserTemplate getOne(UserTemplateQueryBo query);

    /**
     * 按照条件分页查询
     * @param queryWrapper 查询条件
     */
    IPage<UserTemplate> page(QueryWrapper<UserTemplate> queryWrapper,int pageNo,int pageSize);

    /**
     * 按照条件更新
     * @param entity
     * @param query
     * @return
     */
    boolean update(UserTemplate entity,UserTemplateQueryBo query);

    /**
     * 通过一个相等的条件，修改数据
     * @param entity 修改的数据
     * @param column 数据库列名
     * @param value 条件值
     * @return true-修改成功
     */
    boolean updateByColumn(UserTemplate entity,String column,Object value);

    /**
     * 按照条件查询所有
     * @param query 查询条件
     */
    List<UserTemplate> list(UserTemplateQueryBo query);

}
