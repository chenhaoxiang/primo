/*
 * wiki.primo
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.core.entity;

import wiki.primo.generator.mock.test.data.config.json.JsonConfig;
import lombok.Data;

import java.io.File;

/**
 * 配置属性
 *
 * @author chenhx
 * @version ConfigEntity.java, v 0.1 2019-06-21 10:49 chenhx
 */
@Data
public class ConfigEntity {
    /**
     * 需要测试的项目包名
     */
    private String testPackageName;
    /**
     * 作者
     */
    private String author;
    /**
     * 是否获取子包下的类
     */
    private Boolean isGetChildPackage;


    /**
     * 运行项目的target路径
     */
    private String target;

    /**
     * 运行项目的跟路径
     */
    private File basedir;

    /**
     * 运行项目名
     */
    private String project;

    /**
     * 配置文件路径
     */
    private String configPath;

    /**
     * 配置文件名称
     */
    private String configFileName;


    /**
     * 配置是否mock掉父类以及自身测试类非测试的方法
     */
    private Boolean isMockThisOtherMethod;

    /**
     * 配置是否设置基础类型的值随机生成
     */
    private Boolean isSetBasicTypesRandomValue;


    /**
     * 配置字符串随机值的位数（例如："10"，表示10位随机字母/数字字符）
     *
     */
    private String setStringRandomRange;
    /**
     * 配置int/Integer类型随机值的范围（例如："0,1000"，表示[0,1000]范围的int数值，配置固定的值可配置为"0",则int值固定为0）
     *
     */
    private String setIntRandomRange;
    /**
     * 配置long/Long类型随机值的范围(配置规则与setIntRandomRange类似)
     *
     */
    private String setLongRandomRange;
    /**
     * 配置boolean/Boolean类型随机值的范围（例如："true,false"，表示true和false随机。配置为"true"/"false"表示为固定的值）
     *
     */
    private String setBooleanRandomRange;

    /**
     * json的配置值
     */
    private JsonConfig jsonConfig;

    /**
     * json配置文件路径
     */
    private String jsonConfigPath;

    /**
     * json配置文件名称
     */
    private String jsonConfigFileName;


    protected Boolean isDownloadTemplateFile;

    /**
     * 是否将json配置文件下载到本地，默认true
     */
    protected Boolean isDownloadJsonFile;

}
