/*
 * chenhx
 * Copyright (C) 2013-2022 All Rights Reserved.
 */
package wiki.primo.generator.mybatis.plus.config.external;

import lombok.Data;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.Serializable;

/**
 * @author chenhx
 * @version 0.0.1
 * @className FtlConfig.java
 * @date 2022-03-31 3:42 下午
 * @description ftl文件配置
 */
@Data
public class FtlConfig implements Serializable {

   private static final long serialVersionUID = 8614424445657327618L;
    /**
     * 基类路径
     */
    @Parameter
   private String basePath;
    /**
     * 表的操作 ftl文件生成的路径
     * 最终路径是 ：basePath + tablePath
     */
    @Parameter
   private String tablePath;

    /**
     * 模板地址 ,可自行配置，默认：/template/page/table.ftl.vm
     */
    @Parameter
    private String tableTemplatePath;
}
