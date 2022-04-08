/*
 * chenhx
 * Copyright (C) 2013-2022 All Rights Reserved.
 */
package wiki.primo.generator.mybatis.plus.builder.page;

import lombok.Data;
import wiki.primo.generator.mybatis.plus.builder.po.TableInfoPO;
import wiki.primo.generator.mybatis.plus.config.external.PackageConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 每个页面需要构建的实体类
 * @author chenhx
 * @version 0.0.1
 * @since  2022-03-30 4:57 下午
 */
@Data
public class ControllerPageBuilder implements Serializable {
    private static final long serialVersionUID = -5776714046494430656L;
    /**
     * 父包名称 - 注意，作为URL时，该值需要全小写
     * @see PackageConfig#moduleName 取的是该值
     */
    private String moduleName;
    /**
     * TODO 构建请求的url时需要，请求后端 - 暂未使用
     */
    private ControllerUrlBuilder controllerUrlBuilder;
    /**
     * 当前数据表的表格菜单
     */
    private ControllerMenuBuilder controllerMenuBuilder;
    /**
     * 表信息
     */
    private TableInfoPO tableInfoPO;
    /**
     * 模板的文件名路径 + 文件名
     */
    private String templateFilePath;

    /**
     * 文件名
     */
    private String saveFilePathName;
    /**
     * 保存的路径
     */
    private String saveFilePath;

    /**
     * 博客中，实体类字段的名称 - 返回给前端生成th的
     */
    private List<PageFieldBuilder> fieldResps;

    /**
     * 获取数量加1的值
     * @return
     */
    public Integer sizeAdd(Integer size,Integer add){
        return size+add;
    }

    /**
     * 获取可以模糊搜索的字段
     * @return
     */
    public List<PageFieldBuilder> getCanFuzzyList(){
        List<PageFieldBuilder> fieldRespList = new ArrayList<>();
        for (PageFieldBuilder fieldResp : fieldResps) {
            if(fieldResp.getCanFuzzy()){
                fieldRespList.add(fieldResp);
            }
        }
        return fieldRespList;
    }
}
