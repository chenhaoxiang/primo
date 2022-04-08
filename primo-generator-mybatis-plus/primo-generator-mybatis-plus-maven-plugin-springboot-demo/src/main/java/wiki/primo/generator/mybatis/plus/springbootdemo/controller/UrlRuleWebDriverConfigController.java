package wiki.primo.generator.mybatis.plus.springbootdemo.controller;

import wiki.primo.generator.mybatis.plus.springbootdemo.service.IUrlRuleWebDriverConfigService;
import wiki.primo.generator.mybatis.plus.springbootdemo.result.ResultModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wiki.primo.generator.mybatis.plus.springbootdemo.query.UrlRuleWebDriverConfigQueryBo;
import wiki.primo.generator.mybatis.plus.springbootdemo.domain.req.UrlRuleWebDriverConfigReq;
import wiki.primo.generator.mybatis.plus.springbootdemo.domain.resp.UrlRuleWebDriverConfigResp;
import wiki.primo.generator.mybatis.plus.springbootdemo.entity.UrlRuleWebDriverConfig;
import java.util.ArrayList;
import java.util.List;
import wiki.primo.generator.mybatis.plus.springbootdemo.domain.vo.resp.PageVOResp;
import wiki.primo.generator.mybatis.plus.springbootdemo.domain.vo.req.UrlRuleWebDriverConfigPageVOReq;
import wiki.primo.generator.mybatis.plus.springbootdemo.domain.vo.req.UrlRuleWebDriverConfigEntityVOReq;

/**
 * <p>
 * 抓取URL的配置规则-通过无头浏览器  前端控制器
 * </p>
 *
 * @author chenhx
 * @since 2022-04-08 14:11:06
 */
@Controller
@RequestMapping("/urlrulewebdriverconfig")
public class UrlRuleWebDriverConfigController {

    @Autowired
    private IUrlRuleWebDriverConfigService iUrlRuleWebDriverConfigService;

    /**
    * 表格页面
    */
    @RequestMapping({"tables"})
    public String tables() {
        return "tables/urlRuleWebDriverConfig";
    }

    /**
     * 添加对象
     *
     * @param urlRuleWebDriverConfigEntityVOReq 对象
     * @return ResultModel统一响应结果
     */
    @PostMapping("save")
    @ResponseBody
    public ResultModel<Object> save(@RequestBody UrlRuleWebDriverConfigEntityVOReq urlRuleWebDriverConfigEntityVOReq) {
        if(urlRuleWebDriverConfigEntityVOReq==null){
            return ResultModel.parameterError();
        }
        UrlRuleWebDriverConfig urlRuleWebDriverConfig = new UrlRuleWebDriverConfig();
        BeanUtils.copyProperties(urlRuleWebDriverConfigEntityVOReq,urlRuleWebDriverConfig);
        iUrlRuleWebDriverConfigService.save(urlRuleWebDriverConfig);
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
        boolean success = iUrlRuleWebDriverConfigService.removeById(id);
        if(success) {
            return ResultModel.success();
        }else {
            return ResultModel.fail();
        }
    }

    /**
     * 根据ID进行修改对象
     *
     * @param urlRuleWebDriverConfigEntityVOReq 对象中必须有ID主键
     * @return ResultModel统一响应结果
     */
    @PostMapping("update")
    @ResponseBody
    public ResultModel<Object> update(@RequestBody UrlRuleWebDriverConfigEntityVOReq urlRuleWebDriverConfigEntityVOReq) {
        if(urlRuleWebDriverConfigEntityVOReq==null || urlRuleWebDriverConfigEntityVOReq.getId()==null){
            return ResultModel.parameterError();
        }
        UrlRuleWebDriverConfig urlRuleWebDriverConfig = new UrlRuleWebDriverConfig();
        BeanUtils.copyProperties(urlRuleWebDriverConfigEntityVOReq,urlRuleWebDriverConfig);
        boolean success = iUrlRuleWebDriverConfigService.updateById(urlRuleWebDriverConfig);
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
    public ResultModel<UrlRuleWebDriverConfigResp> getById(@RequestParam Integer id) {
        if(id==null || id<0){
            return ResultModel.parameterError();
        }
        UrlRuleWebDriverConfig urlRuleWebDriverConfig= iUrlRuleWebDriverConfigService.getById(id);
        UrlRuleWebDriverConfigResp urlRuleWebDriverConfigResp = new UrlRuleWebDriverConfigResp();
        BeanUtils.copyProperties(urlRuleWebDriverConfig,urlRuleWebDriverConfigResp);
        return ResultModel.success(urlRuleWebDriverConfigResp);
    }

    /**
     * 分页查询
     *
     * @return ResultModel统一响应结果
     */
    @PostMapping("page")
    @ResponseBody
    public ResultModel<PageVOResp<UrlRuleWebDriverConfigResp>> page(@RequestBody UrlRuleWebDriverConfigPageVOReq pageVOReq) {
        IPage<UrlRuleWebDriverConfig> iPage = iUrlRuleWebDriverConfigService.page(pageVOReq.getQueryBo(),pageVOReq.getPageNo(),pageVOReq.getPageSize());
        PageVOResp<UrlRuleWebDriverConfigResp> templateIPage = new PageVOResp<>();
        templateIPage.setPages(iPage.getPages());
        templateIPage.setCurrent(iPage.getCurrent());
        templateIPage.setSize(iPage.getSize());
        templateIPage.setTotal(iPage.getTotal());
        List<UrlRuleWebDriverConfig> urlRuleWebDriverConfigList = iPage.getRecords();
        List<UrlRuleWebDriverConfigResp> urlRuleWebDriverConfigRespList = new ArrayList<>(urlRuleWebDriverConfigList.size());
        for (UrlRuleWebDriverConfig urlRuleWebDriverConfig : urlRuleWebDriverConfigList) {
            UrlRuleWebDriverConfigResp urlRuleWebDriverConfigResp = new UrlRuleWebDriverConfigResp();
            BeanUtils.copyProperties(urlRuleWebDriverConfig,urlRuleWebDriverConfigResp);
            urlRuleWebDriverConfigRespList.add(urlRuleWebDriverConfigResp);
        }
        templateIPage.setDraw(pageVOReq.getDraw());
        templateIPage.setRecords(urlRuleWebDriverConfigRespList);
        return ResultModel.success(templateIPage);
    }

}
