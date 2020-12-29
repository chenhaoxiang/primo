package wiki.primo.generator.primogeneratormocktestdemo.controller;

import wiki.primo.generator.primogeneratormocktestdemo.service.IUserTemplateService;
import wiki.primo.generator.primogeneratormocktestdemo.entity.UserTemplate;
import wiki.primo.generator.primogeneratormocktestdemo.result.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wiki.primo.generator.primogeneratormocktestdemo.query.UserTemplateQueryBo;

/**
 * <p>
 * 用户-模板表  前端控制器
 * </p>
 *
 * @author chenhx
 * @since 2020-12-29
 */
@Controller
@RequestMapping("/usertemplate")
public class UserTemplateController {

    @Autowired
    private IUserTemplateService iUserTemplateService;

    /**
     * 添加对象
     *
     * @param userTemplate 对象
     * @return ResultModel统一响应结果
     */
    @PostMapping("save")
    @ResponseBody
    public ResultModel<Object> save(UserTemplate userTemplate) {
        if(userTemplate==null){
            return ResultModel.parameterError();
        }
        iUserTemplateService.save(userTemplate);
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
        boolean success = iUserTemplateService.removeById(id);
        if(success) {
            return ResultModel.success();
        }else {
            return ResultModel.fail();
        }
    }

    /**
     * 根据ID进行修改对象
     *
     * @param userTemplate 对象中必须有ID主键
     * @return ResultModel统一响应结果
     */
    @PostMapping("update")
    @ResponseBody
    public ResultModel<Object> update(UserTemplate userTemplate) {
        if(userTemplate==null || userTemplate.getId()==null){
            return ResultModel.parameterError();
        }
        boolean success = iUserTemplateService.updateById(userTemplate);
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
    public ResultModel<UserTemplate> getById(@RequestParam Integer id) {
        if(id==null || id<0){
            return ResultModel.parameterError();
        }
        UserTemplate userTemplate = iUserTemplateService.getById(id);
        return ResultModel.success(userTemplate);
    }

    /**
     * 分页查询
     *
     * @return ResultModel统一响应结果
     */
    @PostMapping("page")
    @ResponseBody
    public ResultModel<IPage<UserTemplate>> page(UserTemplateQueryBo queryBo,int pageNo,int pageSize) {
        if(queryBo==null){
            return ResultModel.parameterError();
        }
        IPage<UserTemplate> templateIPage = iUserTemplateService.page(queryBo,pageNo,pageSize);
        return ResultModel.success(templateIPage);
    }

}
