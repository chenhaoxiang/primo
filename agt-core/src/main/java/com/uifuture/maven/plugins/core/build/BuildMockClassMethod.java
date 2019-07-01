/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.build;

import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.uifuture.maven.plugins.core.common.BaseConstant;
import com.uifuture.maven.plugins.core.dto.JavaMethodDTO;
import com.uifuture.maven.plugins.core.dto.JavaMockMethodInfoDTO;
import com.uifuture.maven.plugins.core.dto.JavaParameterDTO;
import com.uifuture.maven.plugins.core.model.JavaClassModel;
import com.uifuture.maven.plugins.core.model.JavaGenInfoModel;
import com.uifuture.maven.plugins.core.model.JavaMethodModel;
import com.uifuture.maven.plugins.core.model.JavaParameteModel;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenhx
 * @version BuildMockClassMethod.java, v 0.1 2019-06-27 18:31 chenhx
 */
public class BuildMockClassMethod {

    private static Log log = new SystemStreamLog();
    /**
     * 构建mock的类方法信息
     * @param javaGenInfoModel 存储的类信息
     * @param javaMethod 方法信息
     * @param javaMethodDTO 模板的方法信息
     */
    public static void buildMock(JavaGenInfoModel javaGenInfoModel, JavaMethod javaMethod, JavaMethodDTO javaMethodDTO) {
        //Mock方法模拟
        List<JavaMockMethodInfoDTO> javaMockMethodInfoDTOList = new ArrayList<>();
        //获取方法的源码
        String methodCode = javaMethod.getSourceCode();

        Map<String, String> mockFullyTypeNameMap = javaGenInfoModel.getMockFullyTypeNameMap();

        //判断方法中是否有需要mock的方法,需要有 属性名+方法名称
        for (String name : mockFullyTypeNameMap.keySet()) {

            //name - 属性名称
            String pattern = name + "\\([\\S ]+\\);";
            //正则匹配
            Pattern p = Pattern.compile(pattern);
            // 获取 matcher 对象
            Matcher m = p.matcher(methodCode);
            while (m.find()) {
                saveMockMethodInfoDTO(javaGenInfoModel, javaMockMethodInfoDTOList, methodCode, name, m);
            }
        }
        javaMethodDTO.setJavaMockMethodInfoDTOList(javaMockMethodInfoDTOList);
    }



    /**
     * 保存方法中对应使用的mock方法的一些信息
     *
     * @param javaGenInfoModel 存储的类信息
     * @param javaMockMethodInfoDTOList Mock方法集合
     * @param methodCode 方法的源码
     * @param name 属性变量名称 + "." + 方法名称
     * @param m 正则匹配
     */
    private static void saveMockMethodInfoDTO(JavaGenInfoModel javaGenInfoModel,
                                              List<JavaMockMethodInfoDTO> javaMockMethodInfoDTOList,
                                              String methodCode,
                                              String name, Matcher m) {
        Map<String, String> mockFullyTypeNameMap = javaGenInfoModel.getMockFullyTypeNameMap();
        //全限定名称
        String fullyType = mockFullyTypeNameMap.get(name);

        String str = methodCode.substring(m.start(), m.end());
        JavaMockMethodInfoDTO javaMockMethodInfoDTO = new JavaMockMethodInfoDTO();

        JavaClassModel javaClassModel = javaGenInfoModel.getJavaClassModelMap().get(fullyType);
        if (javaClassModel == null) {
            log.warn("获取的mock类数据为NULL，"
                    + "mockJavaClassModelMap=" + javaGenInfoModel
                    + ",name=" + name
                    + ",方法源码=" + methodCode);
            return;
        }
        javaMockMethodInfoDTO.setClassType(javaClassModel.getType());
        //获取变量名称
        String methodName = null;
        try {
            String fieldName = name.split("\\.")[0];
            log.info("获取的变量名称为:"+fieldName+",全部名称为"+name.split("\\.")[0]+"."+name.split("\\.")[1]+",匹配的数据为:"+str);
            if ("this".equals(fieldName)) {
                //当前测试类 属性
                fieldName = javaGenInfoModel.getModelNameLowerCamel();
                log.info("获取的变量名称为:"+fieldName+",进行设置属性变量："+fieldName);
            }
            javaMockMethodInfoDTO.setFieldName(fieldName);
            //TODO 方法名称 - 这里实际还需要区分参数类型和参数个数，否则无法匹配到唯一的方法,目前不支持重名方法！！！
//        String nameS = str.substring(str.indexOf(".") + 1, str.indexOf("("));
            //获取方法名称
            methodName = name.split("\\.")[1];
        } catch (Exception e) {
            log.error("获取变量名称异常，变量名.方法名:" + name + "，全限定名称为:" + fullyType, e);
            throw new RuntimeException(e);
        }

        log.info("获取到Mock的方法：" + str + ",javaMockMethodInfoDTO=" + javaMockMethodInfoDTO);

        javaMockMethodInfoDTO.setName(methodName);
        int num = str.split(",").length;
        //判断是否是空参方法
        if (str.contains(javaMockMethodInfoDTO.getName() + "();")) {
            num = 0;
        }

        JavaMethodModel javaMethodModel = null;

        List<JavaMethodModel> javaMethodModelList = javaClassModel.getJavaMethodModelList();
        for (JavaMethodModel methodModel : javaMethodModelList) {
            if (methodModel.getName().equals(methodName)) {
                javaMethodModel = methodModel;
                break;
            }
        }
        if (javaMethodModel == null) {
            javaMethodModel = getJavaMethodModelByParent(javaGenInfoModel, name, methodName, javaMethodModel);
            if (javaMethodModel == null) {
                //手动拼接 - 未获取到时，通过正则判断
                javaMethodModel = new JavaMethodModel();

                javaMethodModel.setParentClassFullyType("");
                javaMethodModel.setFieldName("");
                javaMethodModel.setClassType("");
                javaMethodModel.setName("");
                List<JavaParameteModel> javaParameteModelList = new ArrayList<JavaParameteModel>();
                for (int i = 0; i < num; i++) {
                    JavaParameteModel javaParameteModel = new JavaParameteModel();
                    javaParameteModelList.add(javaParameteModel);
                }
                javaMethodModel.setJavaParameteModelList(javaParameteModelList);
                javaMethodModel.setReturnFullyType("");
                javaMethodModel.setReturnType("");
            }
        }
        //设置参数数量

        javaMockMethodInfoDTO.setParentClassFullyType(javaMethodModel.getParentClassFullyType());

        List<JavaParameteModel> javaParameteModelList = javaMethodModel.getJavaParameteModelList();
        List<JavaParameterDTO> javaParameterDTOList = new ArrayList<>();
        if (javaParameteModelList != null) {
            for (JavaParameteModel javaParameteModel : javaParameteModelList) {
                JavaParameterDTO javaParameterDTO = new JavaParameterDTO();
                javaParameterDTO.setName(javaParameteModel.getName());
                javaParameterDTO.setUpName(javaParameteModel.getUpName());
                javaParameterDTO.setType(javaParameteModel.getType());
                javaParameterDTO.setFullyType(javaParameteModel.getFullyType());
                javaParameterDTO.setCustomType(javaParameteModel.getCustomType());
                javaParameterDTO.setValue(javaParameteModel.getValue());
                javaParameterDTOList.add(javaParameterDTO);
            }
        }
        javaMockMethodInfoDTO.setJavaParameterDTOList(javaParameterDTOList);

        javaMockMethodInfoDTO.setReturnFullyType(javaMethodModel.getReturnFullyType());
        javaMockMethodInfoDTO.setReturnType(javaMethodModel.getReturnType());

        //去重
//        if (methodNameSet.contains(nameS)) {
//            return;
//        }
        log.info("mock方法的属性：" + javaMockMethodInfoDTO);
        javaMockMethodInfoDTOList.add(javaMockMethodInfoDTO);
    }



    /**
     * 通过父类进行获取方法的属性
     *
     * @param javaGenInfoModel 存储的类信息
     * @param name 属性变量名称 + "." + 方法名称
     * @param methodName 方法名称
     * @param javaMethodModel 方法信息
     * @return 方法信息
     */
    private static JavaMethodModel getJavaMethodModelByParent(JavaGenInfoModel javaGenInfoModel, String name, String methodName, JavaMethodModel javaMethodModel) {
        //通过父类再进行获取
        JavaClass javaClass = BaseConstant.javaProjectBuilder.getClassByName(name);
        if (javaClass == null) {
            log.warn("没有找到该类，类名："
                    + name + "，javaClass=null");
            return null;
        }
        JavaClass superJavaClass = javaClass.getSuperJavaClass();
        if (superJavaClass == null) {
            log.warn("没有找到该类的父类，类名："
                    + name + "，superJavaClass=null，javaClass=" + javaClass);
            return null;
        }

        JavaClassModel javaClassModel1 = javaGenInfoModel.getJavaClassModelMap().get(superJavaClass.getFullyQualifiedName());
        if (javaClassModel1 == null) {
            log.warn("没有找到该父类的JavaClassModel，superJavaClass：" + superJavaClass + "，javaClass："
                    + javaClass + "，javaGenInfoModel=" + javaGenInfoModel);
            return null;
        }
        for (JavaMethodModel methodModel : javaClassModel1.getJavaMethodModelList()) {
            //获取到对应的方法
            if (methodModel.getName().equals(methodName)) {
                return javaMethodModel;
            }
        }
        log.warn("在类中没有找到该方法，方法名：" + methodName + "，类名："
                + name + "，javaGenInfoModel=" + javaGenInfoModel);

        return null;
    }


}