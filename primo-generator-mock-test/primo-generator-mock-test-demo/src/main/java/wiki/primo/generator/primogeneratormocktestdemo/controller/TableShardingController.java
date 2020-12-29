package wiki.primo.generator.primogeneratormocktestdemo.controller;

import wiki.primo.generator.primogeneratormocktestdemo.service.ITableShardingService;
import wiki.primo.generator.primogeneratormocktestdemo.entity.TableSharding;
import wiki.primo.generator.primogeneratormocktestdemo.result.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wiki.primo.generator.primogeneratormocktestdemo.query.TableShardingQueryBo;

/**
 * <p>
 * 分片表1  前端控制器
 * </p>
 *
 * @author chenhx
 * @since 2020-12-29
 */
@Controller
@RequestMapping("/tablesharding")
public class TableShardingController {

    @Autowired
    private ITableShardingService iTableShardingService;

    /**
     * 添加对象
     *
     * @param tableSharding 对象
     * @return ResultModel统一响应结果
     */
    @PostMapping("save")
    @ResponseBody
    public ResultModel<Object> save(TableSharding tableSharding) {
        if(tableSharding==null){
            return ResultModel.parameterError();
        }
        iTableShardingService.save(tableSharding);
        return ResultModel.success();
    }

    /**
     * 根据ID进行删除
     *
     * @param id 主键
     * @return ResultModel统一响应结果
     */
    @GetMapping("removeById")
    @ResponseBody
    public ResultModel<Object> delete(@RequestParam Integer id) {
        if(id==null || id<0){
            return ResultModel.parameterError();
        }
        boolean success = iTableShardingService.removeById(id);
        if(success) {
            return ResultModel.success();
        }else {
            return ResultModel.fail();
        }
    }

    /**
     * 根据ID进行修改对象
     *
     * @param tableSharding 对象中必须有ID主键
     * @return ResultModel统一响应结果
     */
    @PostMapping("update")
    @ResponseBody
    public ResultModel<Object> update(TableSharding tableSharding) {
        if(tableSharding==null || tableSharding.getId()==null){
            return ResultModel.parameterError();
        }
        boolean success = iTableShardingService.updateById(tableSharding);
        if(success) {
            return ResultModel.success();
        }else {
            return ResultModel.fail();
        }
    }

    /**
     * 查询详情
     *
     * @param id 主键
     * @return ResultModel统一响应结果
     */
    @GetMapping("getById")
    @ResponseBody
    public ResultModel<TableSharding> getById(@RequestParam Integer id) {
        if(id==null || id<0){
            return ResultModel.parameterError();
        }
        TableSharding tableSharding = iTableShardingService.getById(id);
        return ResultModel.success(tableSharding);
    }

    /**
     * 分页查询
     *
     * @return ResultModel统一响应结果
     */
    @PostMapping("page")
    @ResponseBody
    public ResultModel<IPage<TableSharding>> page(TableShardingQueryBo queryBo,int pageNo,int pageSize) {
        if(queryBo==null){
            return ResultModel.parameterError();
        }
        IPage<TableSharding> templateIPage = iTableShardingService.page(queryBo,pageNo,pageSize);
        return ResultModel.success(templateIPage);
    }

}
