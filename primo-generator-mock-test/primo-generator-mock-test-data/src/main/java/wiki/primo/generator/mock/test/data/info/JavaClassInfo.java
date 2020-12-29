/*
 * wiki.primo
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.data.info;

import wiki.primo.generator.mock.test.data.model.JavaClassModel;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 在方法中，作为参数传递给后面的类进行记录传值的类
 *
 * @author chenhx
 * @version JavaClassInfo.java, v 0.1 2019-07-04 15:42 chenhx
 */
@Data
public class JavaClassInfo {

    private static Log log = new SystemStreamLog();

    /**
     * 当前被测试类的包名
     */
    private String packageName;
    /**
     * 被测试类的绝对路径
     */
    private String absolutePath;
    /**
     * 被测试类的绝对路径
     */
    private String testAbsolutePath;
    /**
     * 被测试类的全限定名称
     */
    private String fullyTypeName;
    /**
     * 被测试类的类名
     */
    private String typeName;
    /**
     * 当前 类属性名称
     * 被测试的类名 - 首字母小写
     */
    private String modelNameLowerCamel;

    /**
     * 类信息存储
     * key - 类的全限定名称
     * value - 类信息
     */
    private Map<String, JavaClassModel> javaClassModelMap = new HashMap<>();

    /**
     * key - 属性变量名称
     * value - 属性类型的全限定名称
     */
    private Map<String, String> fieldFullyTypeNameMap = new HashMap<>();

    /**
     * key - 类型简称
     * value - 类型的全限定名称
     */
    private Map<String, String> fullyTypeNameMap = new HashMap<>();

    /**
     * mock的类信息
     * key - 属性变量名称 + "." + 方法名称（暂时不支持重名方法）
     * value - 属性类型的全限定名称
     */
    private Map<String, String> mockFullyTypeNameMap = new HashMap<>();

    /**
     * 需要导入的包  如果有多个，全部使用全限定名，在该map中的，表示没有简称相同的类
     * key - 变量名-简称
     * value - 全限定名称
     */
    private Map<String, Set<String>> implementsJavaPackageMap = new HashMap<>();

    /**
     * 测试方法名称出现的次数，如果有多个重名方法，方法后面接上数字
     */
    private Map<String, Integer> methodMap = new HashMap<>();


    public String getTypeName() {
        if (StringUtils.isEmpty(typeName)) {
            log.error("typeName 未进行设置值，请设置值后再进行获取");
        }
        return typeName;
    }

    /**
     * Getter method for property <tt>packageName</tt>.
     *
     * @return property value of packageName
     */
    public String getPackageName() {
        if (StringUtils.isEmpty(packageName)) {
            log.error("packageName 未进行设置值，请设置值后再进行获取");
        }
        return packageName;
    }

    /**
     * Getter method for property <tt>absolutePath</tt>.
     *
     * @return property value of absolutePath
     */
    public String getAbsolutePath() {
        if (StringUtils.isEmpty(absolutePath)) {
            log.error("absolutePath 未进行设置值，请设置值后再进行获取");
        }
        return absolutePath;
    }

    /**
     * Setter method for property <tt>absolutePath</tt>.
     *
     * @param absolutePath value to be assigned to property absolutePath
     */
    public void setAbsolutePath(String absolutePath) {

        if (StringUtils.isEmpty(typeName)) {
            //判断系统
            String osName = System.getProperty("os.name");
            if (osName == null) {
                setTypeName(absolutePath.substring(absolutePath.lastIndexOf("/") + 1, absolutePath.lastIndexOf(".")));
            }else {
                if (osName.contains("Windows")) {
                    setTypeName(absolutePath.substring(absolutePath.lastIndexOf("\\") + 1, absolutePath.lastIndexOf(".")));
                } else {
                    setTypeName(absolutePath.substring(absolutePath.lastIndexOf("/") + 1, absolutePath.lastIndexOf(".")));
                }
            }

        }
        this.absolutePath = absolutePath;
    }

    /**
     * Getter method for property <tt>testAbsolutePath</tt>.
     *
     * @return property value of testAbsolutePath
     */
    public String getTestAbsolutePath() {
        if (StringUtils.isEmpty(testAbsolutePath)) {
            log.error("testAbsolutePath 未进行设置值，请设置值后再进行获取");
        }
        return testAbsolutePath;
    }

    /**
     * Getter method for property <tt>fullyTypeName</tt>.
     *
     * @return property value of fullyTypeName
     */
    public String getFullyTypeName() {
        if (StringUtils.isNotEmpty(fullyTypeName)) {
            return fullyTypeName;
        }
        if (StringUtils.isNotEmpty(packageName) && StringUtils.isNotEmpty(typeName)) {
            fullyTypeName = packageName + "." + typeName;
        }
        if (StringUtils.isEmpty(fullyTypeName)) {
            log.error("fullyTypeName 未进行设置值，请设置值后再进行获取");
        }
        return fullyTypeName;
    }

    /**
     * Setter method for property <tt>fullyTypeName</tt>.
     *
     * @param fullyTypeName value to be assigned to property fullyTypeName
     */
    public void setFullyTypeName(String fullyTypeName) {
        if (StringUtils.isEmpty(typeName)) {
            setTypeName(fullyTypeName.substring(fullyTypeName.lastIndexOf("/") + 1, fullyTypeName.lastIndexOf(".")));
        }
        this.fullyTypeName = fullyTypeName;
    }
}
