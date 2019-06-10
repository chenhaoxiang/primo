/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.util;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaPackage;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.model.JavaType;
import com.uifuture.maven.plugins.dto.JavaClassDTO;
import com.uifuture.maven.plugins.dto.JavaExceptionsDTO;
import com.uifuture.maven.plugins.dto.JavaImplementsDTO;
import com.uifuture.maven.plugins.dto.JavaMethodDTO;
import com.uifuture.maven.plugins.dto.JavaParameterDTO;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenhx
 * @version JavaProjectBuilderUtil.java, v 0.1 2019-06-10 15:58 chenhx
 */
public class JavaProjectBuilderUtil {
    private static Log log = new SystemStreamLog();
    /**
     * 对应类型转换
     */
    private static final Map<String, String> MAPPING = new HashMap<>();
    /**
     * 对应类型的默认值
     */
    private static final Map<String, String> VALUE = new HashMap<>();
    static {
        //初始化类型转换
        initMapping();
        //初始化默认值
        initValue();
    }

    private static void initMapping() {
        MAPPING.put("java.lang.Integer", "Integer");
        MAPPING.put("java.lang.Long", "Long");
        MAPPING.put("java.lang.Double", "Double");
        MAPPING.put("java.lang.String", "String");
        MAPPING.put("java.lang.Boolean", "Boolean");
        MAPPING.put("java.lang.Byte", "Byte");
        MAPPING.put("java.lang.Float", "Float");
        MAPPING.put("java.lang.Object", "Object");
        MAPPING.put("java.lang.Short", "Short");
        MAPPING.put("java.lang.StringBuffer", "StringBuffer");
        MAPPING.put("java.lang.StringBuilder", "StringBuilder");
        MAPPING.put("java.lang.Void", "Void");

        MAPPING.put("java.util.Map", "java.util.HashMap");
        MAPPING.put("java.util.List", "java.util.ArrayList");
        MAPPING.put("T", "Object");
        MAPPING.put("B", "Object");
        MAPPING.put("M", "Object");
        MAPPING.put("F", "Object");
    }
    private static void initValue() {
        VALUE.put("String","\"\"");
        VALUE.put("Integer","0");
        VALUE.put("Long","0L");
        VALUE.put("Double","0.0");
        VALUE.put("Float","0.0");
        VALUE.put("Boolean","true");

        VALUE.put("int","0");
        VALUE.put("long","0L");
        VALUE.put("double","0.0");
        VALUE.put("float","0.0");
        VALUE.put("boolean","true");
        VALUE.put("StringBuffer","new StringBuffer(\"\")");
        VALUE.put("StringBuilder","new StringBuilder(\"\")");
    }

    public static void main(String[] args) throws IOException {
        String javaName = "/Users/chenhx/Desktop/github/auto-unit-test-plugin/src/main/java/com/uifuture/maven/plugins/BuilderUtilsTest.java";
        // 获取类库
        JavaProjectBuilder builder = new JavaProjectBuilder();
        // Reading a single source file.
        builder.addSource(new File(javaName));
        JavaClass cls = builder.getClassByName("com.uifuture.maven.plugins.BuilderUtilsTest");

        String name = "com.uifuture.maven.plugins.BuilderUtilsTest";
        buildTestMethod(javaName, name, new HashMap<>());

    }

    /**
     * 生成测试方法
     *
     * @param javaNameFile java文件的绝对路径
     * @param javaName     java类的全限定名称
     */
    public static JavaClassDTO buildTestMethod(String javaNameFile, String javaName, Map<String, Object> data) throws IOException {
        JavaClassDTO javaClassDTO = new JavaClassDTO();
        // 获取类库
        JavaProjectBuilder builder = new JavaProjectBuilder();
        // 正在读取单个源文件
        builder.addSource(new File(javaNameFile));
        //获取Java类
        JavaClass cls = builder.getClassByName(javaName);
        log.info("正在构建类：" + cls);
        if (cls.isInterface()) {
            log.info("跳过接口：" + cls);
            return null;
        }
        if (cls.isEnum()) {
            log.info("跳过枚举：" + cls);
            return null;
        }
        if (cls.isAbstract()) {
            log.info("跳过抽象类：" + cls);
            return null;
        }
        if (cls.isPrivate()) {
            log.info("跳过私有类：" + cls);
            return null;
        }

        //获取导入的包
        List<JavaImplementsDTO> javaImplementsDTOList = getJavaImplementsDTOList(cls);
        javaClassDTO.setJavaImplementsDTOList(javaImplementsDTOList);

        //设置包名
        JavaPackage pkg = cls.getPackage();
        javaClassDTO.setPackageName(pkg.getName());

        List<JavaMethodDTO> javaMethodDTOList = new ArrayList<>();
        //获取方法集合
        List<JavaMethod> javaMethodList = cls.getMethods();
        for (JavaMethod javaMethod : javaMethodList) {
            JavaMethodDTO javaMethodDTO = new JavaMethodDTO();
            //获取方法名称
            String methodName = javaMethod.getName();
            javaMethodDTO.setMethodName(methodName);
            //获取方法返回类型
            JavaClass returnValue = javaMethod.getReturns();
            String returnValueStr = returnValue.toString();
            returnValueStr = MAPPING.getOrDefault(returnValueStr,returnValueStr);
            javaMethodDTO.setMethodReturnType(returnValueStr);

            //是否是静态方法
            boolean mStatic = javaMethod.isStatic();
            if (mStatic) {
                continue;
            }
            //是否公共方法
            boolean mPublic = javaMethod.isPublic();
            if (!mPublic) {
                continue;
            }
            //参数
            List<JavaParameterDTO> javaParameterDTOS = getJavaParameterDTOList(javaMethod);
            javaMethodDTO.setJavaParameterDTOList(javaParameterDTOS);

            //方法抛出的异常
            List<JavaExceptionsDTO> javaExceptionsDTOS = getJavaExceptionsDTOList(javaMethod);
            javaMethodDTO.setJavaExceptionsDTOList(javaExceptionsDTOS);

            //返回的是否是数组
            boolean mArray = javaMethod.getReturns().isArray();
            javaMethodDTOList.add(javaMethodDTO);
        }
        javaClassDTO.setJavaMethodDTOList(javaMethodDTOList);
        log.info("构建的类信息：" + javaClassDTO);
        return javaClassDTO;
    }

    /**
     * 方法抛出的异常
     *
     * @param javaMethod
     * @return
     */
    private static List<JavaExceptionsDTO> getJavaExceptionsDTOList(JavaMethod javaMethod) {
        List<JavaClass> exceptions = javaMethod.getExceptions();
        List<JavaExceptionsDTO> javaExceptionsDTOS = new ArrayList<>();
        for (JavaClass exception : exceptions) {
            JavaExceptionsDTO javaExceptionsDTO = new JavaExceptionsDTO();
            javaExceptionsDTO.setType(exception.toString());
            javaExceptionsDTOS.add(javaExceptionsDTO);
        }
        return javaExceptionsDTOS;
    }

    /**
     * 获取的参数
     *
     * @param javaMethod
     * @return
     */
    private static List<JavaParameterDTO> getJavaParameterDTOList(JavaMethod javaMethod) {
        List<JavaParameter> parameterList = javaMethod.getParameters();
        List<JavaParameterDTO> javaParameterDTOS = new ArrayList<>();
        //TODO 暂时未处理其他泛型,简单的使用Obj代替
        for (JavaParameter javaParameter : parameterList) {
            JavaParameterDTO javaParameterDTO = new JavaParameterDTO();
            javaParameterDTO.setName(javaParameter.getName());
            String typeToStr = javaParameter.getType().toString();
            javaParameterDTO.setType(MAPPING.getOrDefault(typeToStr, typeToStr));
            javaParameterDTO.setValue(VALUE.getOrDefault(javaParameterDTO.getType(),null));
            javaParameterDTOS.add(javaParameterDTO);
        }
        return javaParameterDTOS;
    }

    /**
     * 获取导入的包名
     *
     * @param cls
     * @return
     */
    private static List<JavaImplementsDTO> getJavaImplementsDTOList(JavaClass cls) {
        List<JavaType> javaTypeList = cls.getImplements();
        List<JavaImplementsDTO> javaImplementsDTOList = new ArrayList<>();
        for (JavaType javaType : javaTypeList) {
            JavaImplementsDTO javaImplementsDTO = new JavaImplementsDTO();
            javaImplementsDTO.setType(javaType.toString());
            javaImplementsDTOList.add(javaImplementsDTO);
        }
        return javaImplementsDTOList;
    }
}