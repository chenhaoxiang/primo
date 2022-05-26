/*
 * chenhx
 * Copyright (C) 2013-2022 All Rights Reserved.
 */
package wiki.primo.generator.mybatis.plus.config.external;

import lombok.Data;
import org.apache.maven.plugins.annotations.Parameter;
import wiki.primo.generator.mybatis.plus.config.constant.ConstVal;
import wiki.primo.generator.mybatis.plus.util.StringUtils;

import java.io.File;
import java.io.Serializable;

/**
 * @author chenhx
 * @version 0.0.1
 * @className FtlConfig.java
 * @date 2022-03-31 3:42 下午
 * @description ftl文件配置
 */
public class FtlConfig implements Serializable {

   private static final long serialVersionUID = 8614424445657327618L;
    /**
     * 表的操作 ftl文件生成的路径,默认在 templates/tables 下
     * 最终路径是 ：resources目录 + "/" + tablePath
     */
    @Parameter
   private String tablePath;
    /**
     * 模板地址 ,可自行配置，默认：/template/page/table.ftl.vm
     */
    @Parameter
    private String tableTemplatePath;
    /**
     * 生成ftl文件的开关,默认开启
     */
    @Parameter
    private Boolean open;

    public void setTablePath(String tablePath) {
        this.tablePath = tablePath;
    }

    public void setTableTemplatePath(String tableTemplatePath) {
        this.tableTemplatePath = tableTemplatePath;
    }

    public Boolean getOpen() {
        if(open==null){
            open = true;
        }
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }


    public String getTablePath() {
        if(StringUtils.isEmpty(tablePath)){
            return "templates" + File.separator + "tables"  + File.separator;
        }
        return tablePath;
    }

    public String getTableTemplatePath() {
        if(StringUtils.isEmpty(tableTemplatePath)){
            return ConstVal.TEMPLATE_PAGE_TABLE;
        }
        return tableTemplatePath;
    }

}
