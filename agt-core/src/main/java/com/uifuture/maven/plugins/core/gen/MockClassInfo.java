/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.gen;

import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import com.uifuture.maven.plugins.core.common.BaseConstant;
import com.uifuture.maven.plugins.core.common.InitConstant;
import com.uifuture.maven.plugins.core.dto.JavaClassDTO;
import com.uifuture.maven.plugins.core.dto.JavaMockClassInfoDTO;
import com.uifuture.maven.plugins.core.dto.JavaMockMethodInfoDTO;
import com.uifuture.maven.plugins.core.model.JavaClassModel;
import com.uifuture.maven.plugins.core.model.JavaMethodModel;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chenhx
 * @version MockClassInfo.java, v 0.1 2019-06-24 17:21 chenhx
 */
public class MockClassInfo {
    private static Log log = new SystemStreamLog();


    /**
     * 获取mock的信息，
     * 遍历类中属性，以及属性名称，设置到需要mock的类的信息
     *
     * @param javaClass
     * @param javaFieldList
     * @param mockJavaClassModelMap
     */
    public static void getMockClass(JavaClassDTO javaClassDTO,
                                    JavaClass javaClass,
                                    List<JavaField> javaFieldList,
                                    Map<String, JavaClassModel> mockJavaClassModelMap) {
        //需要mock的类
        List<JavaMockClassInfoDTO> javaMockClassInfoDTOList = new ArrayList<>();
        for (JavaField javaField : javaFieldList) {
            JavaMockClassInfoDTO javaMockClassInfoDTO = new JavaMockClassInfoDTO();
            if (javaField.isStatic()) {
                //跳过静态方法
                continue;
            }
            if (javaField.isFinal()) {
                //跳过final方法
                continue;
            }
            JavaClassModel javaClassModel = new JavaClassModel();
            //属性名称
            javaClassModel.setName(javaField.getName());
            String typeStr = javaField.getType().getFullyQualifiedName();
            String type = InitConstant.MAPPING.getOrDefault(typeStr, typeStr);
            javaClassModel.setType(type);
            //获取类型中的方法 Java类

            //排除不需要mock的类
            if (!BaseConstant.mockJavaSet.contains(typeStr)) {
                continue;
            }

            List<JavaMethodModel> javaMethodModelList = new ArrayList<>();
            List<JavaMockMethodInfoDTO> javaMockMethodInfoDTOList = new ArrayList<>();

            //TODO 获取父类 - 暂时只支持两层 - 暂时也不支持其他jar包中的类
            JavaClass superClass = javaField.getType().getSuperJavaClass();
            if(superClass!=null) {
                log.info("获取的父类：" + superClass);
                //获取类中方法
                JavaClass fieldClass = BaseConstant.javaProjectBuilder.getClassByName(typeStr);
                List<JavaMethod> fieldMethodList = fieldClass.getMethods();
                for (JavaMethod javaMethod : fieldMethodList) {
                    setMockMethodInfo(javaField, type, javaMockMethodInfoDTOList, javaMethod, superClass);
                }

                List<JavaMethod> superJavaMethod = superClass.getMethods();
                if (!BaseConstant.mockParentJavaClassModelMap.containsKey(superClass.getFullyQualifiedName())) {
                    JavaClassModel javaClassModel1 = new JavaClassModel();
                    javaClassModel1.setName(superClass.getName());
                    javaClassModel1.setType(superClass.getFullyQualifiedName());
                    List<JavaMethodModel> javaMethodModelList1 = new ArrayList<>();
                    for (JavaMethod javaMethod : superJavaMethod) {
                        //存储父类信息
                        JavaMethodModel javaMethodModel = saveJavaMethodModel(javaMethod);
                        javaMethodModelList1.add(javaMethodModel);
                    }
                    javaClassModel1.setJavaMethodModelList(javaMethodModelList1);
                    BaseConstant.mockParentJavaClassModelMap.put(superClass.getFullyQualifiedName(), javaClassModel1);
                }
                javaMockClassInfoDTO.setParentType(superClass.getFullyQualifiedName());
            }

            javaMockClassInfoDTO.setName(javaField.getName());
            javaMockClassInfoDTO.setType(type);
            javaMockClassInfoDTO.setJavaMockMethodInfoDTOList(javaMockMethodInfoDTOList);
            //获取类型中的方法 Java类 - 排除不需要mock的java类
            if (BaseConstant.mockJavaSet.contains(type)) {
                //说明该属性类需要mock
                javaMockClassInfoDTOList.add(javaMockClassInfoDTO);
            }

            javaClassModel.setJavaMethodModelList(javaMethodModelList);
            mockJavaClassModelMap.put(javaClassModel.getName(), javaClassModel);

        }
        javaClassDTO.setJavaMockClassInfoDTOList(javaMockClassInfoDTOList);
        //属性的相关信息
        log.info("本类属性相关信息，类：" + javaClass.getName() + ", mock属性类相关信息：" + mockJavaClassModelMap);
    }


    /**
     * 设置mock方法的信息
     *
     * @param javaField
     * @param type
     * @param javaMockMethodInfoDTOList
     * @param javaMethod
     * @param superClass
     */
    private static void setMockMethodInfo(JavaField javaField, String type, List<JavaMockMethodInfoDTO> javaMockMethodInfoDTOList, JavaMethod javaMethod, JavaClass superClass) {
        JavaMockMethodInfoDTO javaMockMethodInfoDTO = new JavaMockMethodInfoDTO();

        JavaMethodModel javaMethodModel = saveJavaMethodModel(javaMethod);

        javaMockMethodInfoDTO.setClassName(javaField.getName());
        javaMockMethodInfoDTO.setClassType(type);
        javaMockMethodInfoDTO.setName(javaMethod.getName());
        javaMockMethodInfoDTO.setParameterNum(javaMethodModel.getParameterNum());
        javaMockMethodInfoDTO.setParameterName(javaMethodModel.getParameterName());
        javaMockMethodInfoDTO.setParameterType(javaMethodModel.getParameterType());
        javaMockMethodInfoDTO.setReturnType(javaMethodModel.getReturnType());
        //设置父类类型
        javaMockMethodInfoDTO.setParentClassType(superClass.getFullyQualifiedName());
        javaMockMethodInfoDTOList.add(javaMockMethodInfoDTO);
    }

    private static JavaMethodModel saveJavaMethodModel(JavaMethod javaMethod) {
        JavaMethodModel javaMethodModel = new JavaMethodModel();
        javaMethodModel.setName(javaMethod.getName());
        //方法参数
        List<JavaParameter> javaParameterList = javaMethod.getParameters();
        List<String> parameterNameList = new ArrayList<>();
        List<String> parameterTypeList = new ArrayList<>();
        for (JavaParameter javaParameter : javaParameterList) {
            parameterNameList.add(javaParameter.getName());
            String typeS = javaParameter.getType().getFullyQualifiedName();
            String pType = InitConstant.MAPPING.getOrDefault(typeS, typeS);
            parameterTypeList.add(pType);
        }
        javaMethodModel.setParameterName(parameterNameList);
        javaMethodModel.setParameterType(parameterTypeList);
        javaMethodModel.setParameterNum(parameterNameList.size());
        String rTypeStr = javaMethod.getReturnType().getFullyQualifiedName();
        String rType = InitConstant.MAPPING.getOrDefault(rTypeStr, rTypeStr);
        javaMethodModel.setReturnType(rType);
        return javaMethodModel;
    }

}