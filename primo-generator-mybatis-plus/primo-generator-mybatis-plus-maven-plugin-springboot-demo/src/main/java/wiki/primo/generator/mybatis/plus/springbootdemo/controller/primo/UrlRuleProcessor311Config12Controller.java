package wiki.primo.generator.mybatis.plus.springbootdemo.controller.primo;

import wiki.primo.generator.mybatis.plus.springbootdemo.service.IUrlRuleProcessor311Config12Service;
import wiki.primo.generator.mybatis.plus.springbootdemo.result.ResultModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wiki.primo.generator.mybatis.plus.springbootdemo.query.UrlRuleProcessor311Config12QueryBo;
import wiki.primo.generator.mybatis.plus.springbootdemo.domain.req.UrlRuleProcessor311Config12Req;
import wiki.primo.generator.mybatis.plus.springbootdemo.domain.resp.UrlRuleProcessor311Config12Resp;
import wiki.primo.generator.mybatis.plus.springbootdemo.entity.UrlRuleProcessor311Config12;
import java.util.ArrayList;
import java.util.List;
import wiki.primo.generator.mybatis.plus.springbootdemo.domain.vo.resp.PageVOResp;
import wiki.primo.generator.mybatis.plus.springbootdemo.domain.vo.req.UrlRuleProcessor311Config12PageVOReq;
import wiki.primo.generator.mybatis.plus.springbootdemo.domain.vo.req.UrlRuleProcessor311Config12EntityVOReq;

/**
 * <p>
 * 抓取URL的配置规则  前端控制器
 * </p>
 *
 * @author chenhx
 * @since 2022-08-22 21:13:31
 */
@Controller("/primo")
@RequestMapping("/urlruleprocessor311config12")
public class UrlRuleProcessor311Config12Controller {

    @Autowired
    private IUrlRuleProcessor311Config12Service iUrlRuleProcessor311Config12Service;

    /**
    * 表格页面
    */
    @RequestMapping({"tables"})
    public String tables() {
        return "tables/urlRuleProcessor311Config12";
    }

    /**
     * 添加对象
     *
     * @param urlRuleProcessor311Config12EntityVOReq 对象
     * @return ResultModel统一响应结果
     */
    @PostMapping("save")
    @ResponseBody
    public ResultModel<Object> save(@RequestBody UrlRuleProcessor311Config12EntityVOReq urlRuleProcessor311Config12EntityVOReq) {
        if(urlRuleProcessor311Config12EntityVOReq==null){
            return ResultModel.parameterError();
        }
        UrlRuleProcessor311Config12 urlRuleProcessor311Config12 = getUrlRuleProcessor311Config12(urlRuleProcessor311Config12EntityVOReq);
        iUrlRuleProcessor311Config12Service.save(urlRuleProcessor311Config12);
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
        boolean success = iUrlRuleProcessor311Config12Service.removeById(id);
        if(success) {
            return ResultModel.success();
        }else {
            return ResultModel.fail();
        }
    }

    /**
     * 根据ID进行修改对象
     *
     * @param urlRuleProcessor311Config12EntityVOReq 对象中必须有ID主键
     * @return ResultModel统一响应结果
     */
    @PostMapping("update")
    @ResponseBody
    public ResultModel<Object> update(@RequestBody UrlRuleProcessor311Config12EntityVOReq urlRuleProcessor311Config12EntityVOReq) {
        if(urlRuleProcessor311Config12EntityVOReq==null || urlRuleProcessor311Config12EntityVOReq.getId()==null){
            return ResultModel.parameterError();
        }
        UrlRuleProcessor311Config12 urlRuleProcessor311Config12 = getUrlRuleProcessor311Config12(urlRuleProcessor311Config12EntityVOReq);
        boolean success = iUrlRuleProcessor311Config12Service.updateById(urlRuleProcessor311Config12);
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
    public ResultModel<UrlRuleProcessor311Config12Resp> getById(@RequestParam Integer id) {
        if(id==null || id<0){
            return ResultModel.parameterError();
        }
        UrlRuleProcessor311Config12 urlRuleProcessor311Config12= iUrlRuleProcessor311Config12Service.getById(id);
        UrlRuleProcessor311Config12Resp urlRuleProcessor311Config12Resp = new UrlRuleProcessor311Config12Resp();
        BeanUtils.copyProperties(urlRuleProcessor311Config12,urlRuleProcessor311Config12Resp);
        return ResultModel.success(urlRuleProcessor311Config12Resp);
    }

    /**
     * 分页查询
     *
     * @return ResultModel统一响应结果
     */
    @PostMapping("page")
    @ResponseBody
    public ResultModel<PageVOResp<UrlRuleProcessor311Config12Resp>> page(@RequestBody UrlRuleProcessor311Config12PageVOReq pageVOReq) {
        IPage<UrlRuleProcessor311Config12> iPage = iUrlRuleProcessor311Config12Service.page(pageVOReq.getQueryBo(),pageVOReq.getPageNo(),pageVOReq.getPageSize());
        PageVOResp<UrlRuleProcessor311Config12Resp> templateIPage = new PageVOResp<>();
        templateIPage.setPages(iPage.getPages());
        templateIPage.setCurrent(iPage.getCurrent());
        templateIPage.setSize(iPage.getSize());
        templateIPage.setTotal(iPage.getTotal());
        List<UrlRuleProcessor311Config12> urlRuleProcessor311Config12List = iPage.getRecords();
        List<UrlRuleProcessor311Config12Resp> urlRuleProcessor311Config12RespList = new ArrayList<>(urlRuleProcessor311Config12List.size());
        for (UrlRuleProcessor311Config12 urlRuleProcessor311Config12 : urlRuleProcessor311Config12List) {
            UrlRuleProcessor311Config12Resp urlRuleProcessor311Config12Resp = new UrlRuleProcessor311Config12Resp();
            BeanUtils.copyProperties(urlRuleProcessor311Config12,urlRuleProcessor311Config12Resp);
            urlRuleProcessor311Config12RespList.add(urlRuleProcessor311Config12Resp);
        }
        templateIPage.setDraw(pageVOReq.getDraw());
        templateIPage.setRecords(urlRuleProcessor311Config12RespList);
        return ResultModel.success(templateIPage);
    }


    public UrlRuleProcessor311Config12 getUrlRuleProcessor311Config12(UrlRuleProcessor311Config12EntityVOReq req){
        UrlRuleProcessor311Config12 entity = new UrlRuleProcessor311Config12();
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
