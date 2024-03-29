package wiki.primo.generator.mybatis.plus.springbootdemo.controller.primo;

import wiki.primo.generator.mybatis.plus.springbootdemo.service.IUrlRuleProcessorConfigService;
import wiki.primo.generator.mybatis.plus.springbootdemo.result.ResultModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wiki.primo.generator.mybatis.plus.springbootdemo.query.UrlRuleProcessorConfigQueryBo;
import wiki.primo.generator.mybatis.plus.springbootdemo.domain.req.UrlRuleProcessorConfigReq;
import wiki.primo.generator.mybatis.plus.springbootdemo.domain.resp.UrlRuleProcessorConfigResp;
import wiki.primo.generator.mybatis.plus.springbootdemo.entity.UrlRuleProcessorConfig;
import java.util.ArrayList;
import java.util.List;
import wiki.primo.generator.mybatis.plus.springbootdemo.domain.vo.resp.PageVOResp;
import wiki.primo.generator.mybatis.plus.springbootdemo.domain.vo.req.UrlRuleProcessorConfigPageVOReq;
import wiki.primo.generator.mybatis.plus.springbootdemo.domain.vo.req.UrlRuleProcessorConfigEntityVOReq;

/**
 * <p>
 * 抓取URL的配置规则  前端控制器
 * </p>
 *
 * @author chenhx
 * @since 2022-08-22 21:17:00
 */
@Controller
@RequestMapping("/primo/urlruleprocessorconfig")
public class UrlRuleProcessorConfigController {

    @Autowired
    private IUrlRuleProcessorConfigService iUrlRuleProcessorConfigService;

    /**
    * 表格页面
    */
    @RequestMapping({"tables"})
    public String tables() {
        return "tables/urlRuleProcessorConfig";
    }

    /**
     * 添加对象
     *
     * @param urlRuleProcessorConfigEntityVOReq 对象
     * @return ResultModel统一响应结果
     */
    @PostMapping("save")
    @ResponseBody
    public ResultModel<Object> save(@RequestBody UrlRuleProcessorConfigEntityVOReq urlRuleProcessorConfigEntityVOReq) {
        if(urlRuleProcessorConfigEntityVOReq==null){
            return ResultModel.parameterError();
        }
        UrlRuleProcessorConfig urlRuleProcessorConfig = getUrlRuleProcessorConfig(urlRuleProcessorConfigEntityVOReq);
        iUrlRuleProcessorConfigService.save(urlRuleProcessorConfig);
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
        boolean success = iUrlRuleProcessorConfigService.removeById(id);
        if(success) {
            return ResultModel.success();
        }else {
            return ResultModel.fail();
        }
    }

    /**
     * 根据ID进行修改对象
     *
     * @param urlRuleProcessorConfigEntityVOReq 对象中必须有ID主键
     * @return ResultModel统一响应结果
     */
    @PostMapping("update")
    @ResponseBody
    public ResultModel<Object> update(@RequestBody UrlRuleProcessorConfigEntityVOReq urlRuleProcessorConfigEntityVOReq) {
        if(urlRuleProcessorConfigEntityVOReq==null || urlRuleProcessorConfigEntityVOReq.getId()==null){
            return ResultModel.parameterError();
        }
        UrlRuleProcessorConfig urlRuleProcessorConfig = getUrlRuleProcessorConfig(urlRuleProcessorConfigEntityVOReq);
        boolean success = iUrlRuleProcessorConfigService.updateById(urlRuleProcessorConfig);
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
    public ResultModel<UrlRuleProcessorConfigResp> getById(@RequestParam Integer id) {
        if(id==null || id<0){
            return ResultModel.parameterError();
        }
        UrlRuleProcessorConfig urlRuleProcessorConfig= iUrlRuleProcessorConfigService.getById(id);
        UrlRuleProcessorConfigResp urlRuleProcessorConfigResp = new UrlRuleProcessorConfigResp();
        BeanUtils.copyProperties(urlRuleProcessorConfig,urlRuleProcessorConfigResp);
        return ResultModel.success(urlRuleProcessorConfigResp);
    }

    /**
     * 分页查询
     *
     * @return ResultModel统一响应结果
     */
    @PostMapping("page")
    @ResponseBody
    public ResultModel<PageVOResp<UrlRuleProcessorConfigResp>> page(@RequestBody UrlRuleProcessorConfigPageVOReq pageVOReq) {
        IPage<UrlRuleProcessorConfig> iPage = iUrlRuleProcessorConfigService.page(pageVOReq.getQueryBo(),pageVOReq.getPageNo(),pageVOReq.getPageSize());
        PageVOResp<UrlRuleProcessorConfigResp> templateIPage = new PageVOResp<>();
        templateIPage.setPages(iPage.getPages());
        templateIPage.setCurrent(iPage.getCurrent());
        templateIPage.setSize(iPage.getSize());
        templateIPage.setTotal(iPage.getTotal());
        List<UrlRuleProcessorConfig> urlRuleProcessorConfigList = iPage.getRecords();
        List<UrlRuleProcessorConfigResp> urlRuleProcessorConfigRespList = new ArrayList<>(urlRuleProcessorConfigList.size());
        for (UrlRuleProcessorConfig urlRuleProcessorConfig : urlRuleProcessorConfigList) {
            UrlRuleProcessorConfigResp urlRuleProcessorConfigResp = new UrlRuleProcessorConfigResp();
            BeanUtils.copyProperties(urlRuleProcessorConfig,urlRuleProcessorConfigResp);
            urlRuleProcessorConfigRespList.add(urlRuleProcessorConfigResp);
        }
        templateIPage.setDraw(pageVOReq.getDraw());
        templateIPage.setRecords(urlRuleProcessorConfigRespList);
        return ResultModel.success(templateIPage);
    }


    public UrlRuleProcessorConfig getUrlRuleProcessorConfig(UrlRuleProcessorConfigEntityVOReq req){
        UrlRuleProcessorConfig entity = new UrlRuleProcessorConfig();
                                                            if(req.getId()!=null) {
                    entity.setId(req.getId());
                }
                                                                        if(req.getPlatformNameEn()!=null && req.getPlatformNameEn().length()>0) {
                    entity.setPlatformNameEn(req.getPlatformNameEn());
                }
                                                                        if(req.getReferer()!=null && req.getReferer().length()>0) {
                    entity.setReferer(req.getReferer());
                }
                                                                        if(req.getUrlPrefix()!=null && req.getUrlPrefix().length()>0) {
                    entity.setUrlPrefix(req.getUrlPrefix());
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
