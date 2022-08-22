package wiki.primo.generator.mybatis.plus.springbootdemo.controller.primo;

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
 * @since 2022-08-22 21:13:31
 */
@Controller("/primo")
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
        UrlRuleWebDriverConfig urlRuleWebDriverConfig = getUrlRuleWebDriverConfig(urlRuleWebDriverConfigEntityVOReq);
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
        UrlRuleWebDriverConfig urlRuleWebDriverConfig = getUrlRuleWebDriverConfig(urlRuleWebDriverConfigEntityVOReq);
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


    public UrlRuleWebDriverConfig getUrlRuleWebDriverConfig(UrlRuleWebDriverConfigEntityVOReq req){
        UrlRuleWebDriverConfig entity = new UrlRuleWebDriverConfig();
                                                            if(req.getId()!=null) {
                    entity.setId(req.getId());
                }
                                                                        if(req.getPlatformNameEn()!=null && req.getPlatformNameEn().length()>0) {
                    entity.setPlatformNameEn(req.getPlatformNameEn());
                }
                                                                        if(req.getReferer()!=null && req.getReferer().length()>0) {
                    entity.setReferer(req.getReferer());
                }
                                                                        if(req.getUrlRuleJson()!=null && req.getUrlRuleJson().length()>0) {
                    entity.setUrlRuleJson(req.getUrlRuleJson());
                }
                                                                        if(req.getTitleRuleJson()!=null && req.getTitleRuleJson().length()>0) {
                    entity.setTitleRuleJson(req.getTitleRuleJson());
                }
                                                                        if(req.getPulishTimeRuleJson()!=null && req.getPulishTimeRuleJson().length()>0) {
                    entity.setPulishTimeRuleJson(req.getPulishTimeRuleJson());
                }
                                                                        if(req.getAuthorRuleJson()!=null && req.getAuthorRuleJson().length()>0) {
                    entity.setAuthorRuleJson(req.getAuthorRuleJson());
                }
                                                                        if(req.getTagsRuleJson()!=null && req.getTagsRuleJson().length()>0) {
                    entity.setTagsRuleJson(req.getTagsRuleJson());
                }
                                                                        if(req.getDateDelete()!=null) {
                    entity.setDateDelete(req.getDateDelete());
                }
                                                                        if(req.getDomain()!=null && req.getDomain().length()>0) {
                    entity.setDomain(req.getDomain());
                }
                                                                        if(req.getBlogsAuthorIndexUrlType()!=null) {
                    entity.setBlogsAuthorIndexUrlType(req.getBlogsAuthorIndexUrlType());
                }
                                                                        if(req.getCreateTime()!=null) {
                    entity.setCreateTime(req.getCreateTime());
                }
                                                                        if(req.getUpdateTime()!=null) {
                    entity.setUpdateTime(req.getUpdateTime());
                }
                                                                        if(req.getStatus()!=null) {
                    entity.setStatus(req.getStatus());
                }
                            return entity;
    }

}
