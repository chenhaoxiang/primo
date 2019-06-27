/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.build;

import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import com.uifuture.maven.plugins.core.common.BaseConstant;
import com.uifuture.maven.plugins.core.common.InitConstant;
import com.uifuture.maven.plugins.core.dto.JavaExceptionsDTO;
import com.uifuture.maven.plugins.core.dto.JavaMethodDTO;
import com.uifuture.maven.plugins.core.dto.JavaMockMethodInfoDTO;
import com.uifuture.maven.plugins.core.dto.JavaParameterDTO;
import com.uifuture.maven.plugins.core.gen.FullNameHandle;
import com.uifuture.maven.plugins.core.model.JavaClassModel;
import com.uifuture.maven.plugins.core.model.JavaGenInfoModel;
import com.uifuture.maven.plugins.core.model.JavaMethodModel;
import com.uifuture.maven.plugins.core.model.JavaParameteModel;
import com.uifuture.maven.plugins.core.util.StringUtil;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 构建类中的方法
 * @author chenhx
 * @version BuildClassMethod.java, v 0.1 2019-06-27 16:37 chenhx
 */
public class BuildClassMethod {

    private static Log log = new SystemStreamLog();

    /**
     * 核心方法
     *
     * @param javaClass
     * @param javaParameterDTOMap 包装类的内部属性 - 包含了父类的属性
     * @param javaGenInfoModel
     */
    public static List<JavaMethodDTO> build(JavaClass javaClass,
                                                        Map<String, List<JavaParameterDTO>> javaParameterDTOMap,
                                                        JavaGenInfoModel javaGenInfoModel) {
        List<JavaMethodDTO> javaMethodDTOList = new ArrayList<>();

        Map<String, Integer> methodMap = javaGenInfoModel.getMethodMap();
        //获取方法集合
        List<JavaMethod> javaMethodList = javaClass.getMethods();
        //遍历类中的方法
        for (JavaMethod javaMethod : javaMethodList) {

            JavaMethodDTO javaMethodDTO = new JavaMethodDTO();

            //获取方法名称
            String methodName = javaMethod.getName();
            javaMethodDTO.setMethodName(methodName);
//          //处理重名方法
            methodDdealingWithRenaming(methodMap, methodName, javaMethodDTO);

            //获取方法返回类型
            JavaClass returnValue = javaMethod.getReturns();
            String returnValueStr = returnValue.getFullyQualifiedName();
            javaMethodDTO.setReturnFullyType(returnValueStr);
            returnValueStr = InitConstant.getAbbreviation(returnValueStr);
            javaMethodDTO.setReturnType(returnValueStr);

            //排除静态方法和私有方法
            if (excludeMethod(javaMethod)) {
                continue;
            }

            //方法参数的设置，包装类设置属性 默认值
            BuildClassMethodParamete.build(javaMethod, javaGenInfoModel,javaMethodDTO);


            //方法抛出的异常
            List<JavaExceptionsDTO> javaExceptionsDTOS = getJavaExceptionsDTOList(javaMethod);
            javaMethodDTO.setJavaExceptionsDTOList(javaExceptionsDTOS);

            //Mock方法的设置
            BuildMockClassMethod.buildMock(javaGenInfoModel, javaMethod, javaMethodDTO);


            javaMethodDTOList.add(javaMethodDTO);
        }
        return javaMethodDTOList;
    }



    /**
     * 处理重名方法
     *
     * @param methodMap
     * @param methodName
     * @param javaMethodDTO
     */
    private static void methodDdealingWithRenaming(Map<String, Integer> methodMap, String methodName, JavaMethodDTO javaMethodDTO) {
        javaMethodDTO.setMethodTestName(methodName + "Test");
        if (methodMap.containsKey(methodName)) {
            Integer t = methodMap.get(methodName);
            javaMethodDTO.setMethodTestName(javaMethodDTO.getMethodTestName() + t);
            methodMap.put(methodName, ++t);
        } else {
            methodMap.put(methodName, 1);
        }
    }


    /**
     * 排除方法
     *
     * @param javaMethod
     * @return
     */
    private static boolean excludeMethod(JavaMethod javaMethod) {
        //是否是静态方法
        boolean mStatic = javaMethod.isStatic();
        if (mStatic) {
            return true;
        }
        //是否公共方法
        boolean mPublic = javaMethod.isPublic();
        return !mPublic;
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
            javaExceptionsDTO.setType(exception.getFullyQualifiedName());
            javaExceptionsDTOS.add(javaExceptionsDTO);
        }
        return javaExceptionsDTOS;
    }



}