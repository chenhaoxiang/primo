/*
 * wiki.primo
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.core.build;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import wiki.primo.generator.mock.test.core.constant.CommonConstant;
import wiki.primo.generator.mock.test.core.constant.InitConstant;
import wiki.primo.generator.mock.test.core.handle.FullNameHandle;
import wiki.primo.generator.mock.test.core.util.StringUtils;
import wiki.primo.generator.mock.test.data.config.json.JsonConfig;
import wiki.primo.generator.mock.test.data.config.json.JsonConfigList;
import wiki.primo.generator.mock.test.data.dto.JavaMethodDTO;
import wiki.primo.generator.mock.test.data.dto.JavaParameterDTO;
import wiki.primo.generator.mock.test.data.enums.JsonConfigListScopeEnum;
import wiki.primo.generator.mock.test.data.info.JavaClassInfo;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 构建方法的参数
 *
 * @author chenhx
 * @version BuildClassMethodParameteImpl.java, v 0.1 2019-06-27 16:51 chenhx
 */
public class BuildClassMethodParameteImpl {
    private static Log log = new SystemStreamLog();

    /**
     * 获取的参数
     * 参数组装-方法参数
     *  @param javaMethod       类方法信息
     * @param javaGenInfoModel 存储的类信息
     * @param javaMethodDTO    模板方法信息
     * @param javaClass 当前方法的所在类
     */
    public static void build(JavaMethod javaMethod, JavaClassInfo javaGenInfoModel, JavaMethodDTO javaMethodDTO, JavaClass javaClass) {
        List<JavaParameterDTO> javaParameterDTOS = new ArrayList<>();

        List<JavaParameter> parameterList = javaMethod.getParameters();
        //TODO 暂时未处理其他泛型,简单的使用Obj代替
        int order = 1;
        //这里是方法最开始的第一层参数
        for (JavaParameter javaParameter : parameterList) {
            JavaParameterDTO javaParameterDTO = new JavaParameterDTO();
            javaParameterDTO.setName(javaParameter.getName());
            //参数的全限定名称
            String typeToStr = javaParameter.getType().getFullyQualifiedName();
            //设置全称
            javaParameterDTO.setFullyType(typeToStr);
            String type = InitConstant.getAbbreviation(typeToStr);
            //设置简称
            javaParameterDTO.setType(type);
            javaParameterDTO.setCustomType(false);

            //进行设置导入包，全称与简称 的处理
            FullNameHandle.addQualifiedNameToImplementsPackageMap(javaParameterDTO, javaGenInfoModel.getImplementsJavaPackageMap());

            //设置构造参数需要的值
            javaParameterDTO.setClassfullyType(javaClass.getFullyQualifiedName());
            javaParameterDTO.setMethodfullyType(javaClass.getFullyQualifiedName() + "#" +javaMethod.getName());
            javaParameterDTO.setPackageName(javaClass.getPackageName());
            log.info("当前的参数信息: "+javaParameterDTO);

            //设置默认值
            javaParameterDTOSetValue(javaParameterDTO, InitConstant.getValue(type));
            javaParameterDTO.setOrder(order++);

            if (javaParameterDTO.getValue() == null) {
                //自定义类型 暂时处理一层包装类
                JavaClass parameterJavaClass = CommonConstant.javaProjectBuilder.getClassByName(typeToStr);
                log.info("自定义类型：" + typeToStr + "，cls：" + (parameterJavaClass == null ? "null" : parameterJavaClass.getFullyQualifiedName()));

                if (parameterJavaClass != null) {

                    if (InitConstant.FULLY_COLLECTION_VALUE_IMPORT.keySet().contains(typeToStr)) {
                        //设置为ArrayList
                        javaParameterDTO.setSubClassFullyType(InitConstant.FULLY_COLLECTION_VALUE_IMPORT.get(typeToStr));
                        javaParameterDTO.setSubClassType(InitConstant.getAbbreviation(InitConstant.FULLY_COLLECTION_VALUE_IMPORT.get(typeToStr)));

                        javaParameterDTO.setIsInterface(true);
                        log.debug("参数类型为FULLY_COLLECTION_VALUE_IMPORT中类型，javaParameterDTO:" + javaParameterDTO);
                    } else if (parameterJavaClass.isInterface()) {
                        //获取实现类 该类是一个接口,获取实现类,子类-也就是派生类
                        List<JavaClass> javaClassList = parameterJavaClass.getDerivedClasses();
                        log.debug("javaClassList1:" + javaClassList + ",自定义类型" + typeToStr);
                        if (!javaClassList.isEmpty()) {
                            //取第一个
                            JavaClass javaClass1 = javaClassList.get(0);
                            javaParameterDTO.setCustomType(true);
                            List<JavaParameterDTO> javaParameterDTOList = new ArrayList<>();
                            //获取属性 - javaParameterDTOList需要排除没有set方法的属性值
                            addParameterToList(javaParameterDTOList, javaClass1, javaGenInfoModel);
                            //设置属性的内部实现
                            javaParameterDTO.setJavaParameterDTOList(javaParameterDTOList);
                        } else {
                            //接口的实现类没有，设置为null
                            javaParameterDTOSetValue(javaParameterDTO, "null");
                        }

                    } else if (parameterJavaClass.isEnum()) {
                        //枚举取值
                        List<JavaField> javaFieldList = parameterJavaClass.getFields();
                        log.debug("获取的枚举值：" + javaFieldList + "，javaClass=" + parameterJavaClass);
                        if (!javaFieldList.isEmpty()) {
                            JavaField javaField = javaFieldList.get(0);
                            javaParameterDTOSetValue(javaParameterDTO, javaField.getType().getFullyQualifiedName() + "." + javaField.getName());
                        } else {
                            javaParameterDTOSetValue(javaParameterDTO, "null");
                        }

                    } else {
                        //自定义的类型

                        javaParameterDTO.setCustomType(true);
                        List<JavaParameterDTO> javaParameterDTOList = new ArrayList<>();
                        //获取属性
                        addParameterToList(javaParameterDTOList, parameterJavaClass, javaGenInfoModel);

                        //获取父类属性 - 暂时也只获取一层
                        JavaClass superJavaClass = parameterJavaClass.getSuperJavaClass();
                        if (!InitConstant.MAPPING.containsKey(superJavaClass.getFullyQualifiedName())) {
                            addParameterToList(javaParameterDTOList, superJavaClass, javaGenInfoModel);
                        }
                        log.debug("superJavaClass:" + superJavaClass.getFullyQualifiedName() + ",自定义类型" + typeToStr + "，自定义类型中的类型：" + javaParameterDTOList);
                        javaParameterDTO.setJavaParameterDTOList(javaParameterDTOList);
                    }

                } else {
                    //TODO 框架无法识别第三方Jar包中的类  说明不是项目中的类 - 设置为null
                    javaParameterDTOSetValue(javaParameterDTO, "null");
                }

                //处理全称限定名称 - 简称
                FullNameHandle.addQualifiedNameToImplementsPackageMap(javaParameterDTO, javaGenInfoModel.getImplementsJavaPackageMap());
            }

            log.debug("生成完参数值的设定:" + javaParameterDTO);
            javaParameterDTOS.add(javaParameterDTO);
        }

        //设置json值
        javaParameterDTOSetJsonValue(javaParameterDTOS);

        javaMethodDTO.setJavaParameterDTOList(javaParameterDTOS);
    }


    /**
     * 获取属性，设置到对象中去
     *
     * @param javaParameterDTOList 模板的属性信息集合
     * @param javaClass            类信息
     * @param javaGenInfoModel     存储的类信息
     */
    private static void addParameterToList(List<JavaParameterDTO> javaParameterDTOList, JavaClass javaClass, JavaClassInfo javaGenInfoModel) {
        if (javaClass.isInterface()) {
            return;
        }
        //获取方法名称 - set方法名称
        Set<String> methodSet = new HashSet<>();
        List<JavaMethod> javaMethodList = javaClass.getMethods();
        for (JavaMethod javaMethod : javaMethodList) {
            methodSet.add(javaMethod.getName());
        }

        //处理源码上含有生成set方法的注解，但是源码中没有set方法处理
        boolean hasAnnotationSet = false;
        //注解 - lombok.Data;lombok.Setter;，均会在class生成set方法
        List<JavaAnnotation> annotationList = javaClass.getAnnotations();
        for (JavaAnnotation javaAnnotation : annotationList) {
            JavaClass anClass = javaAnnotation.getType();
            String anClassName = anClass.getFullyQualifiedName();
            if (InitConstant.CLASS_ANNOTATION_AUTO_SET.contains(anClassName)) {
                //表明有set注解
                hasAnnotationSet = true;
            }
        }
        //TODO 属性上的setter注解并没有进行处理

        List<JavaField> javaFields = javaClass.getFields();
        if (javaFields.isEmpty()) {
            log.warn("获取的类下的属性为空，可能是由于不在同一个项目，类：" + javaClass);
        }
        for (JavaField javaField : javaFields) {
            if (javaField.isStatic()) {
                continue;
            }
            if (javaField.isFinal()) {
                continue;
            }
            //遍历属性,属性名称
            String fieldName = javaField.getName();
            //获取属性类型
            String fullyTypeName = javaField.getType().getFullyQualifiedName();
            String fieldType = InitConstant.getAbbreviation(fullyTypeName);
            JavaParameterDTO javaParameterDTO1 = new JavaParameterDTO();

            javaParameterDTO1.setCustomType(false);
            javaParameterDTO1.setName(fieldName);
            javaParameterDTO1.setType(fieldType);
            javaParameterDTO1.setFullyType(fullyTypeName);
            String value = InitConstant.getValue(fieldType);
            javaParameterDTOSetValue(javaParameterDTO1, value);
            javaParameterDTO1.setUpName(StringUtils.strConvertUpperCamel(fieldName));

            //全称需要增加到导入的包中
            FullNameHandle.addQualifiedNameToImplementsPackageMap(javaParameterDTO1, javaGenInfoModel.getImplementsJavaPackageMap());

            if (value == null) {
                JavaClass javaClass1 = CommonConstant.javaProjectBuilder.getClassByName(fullyTypeName);
                if (javaClass1 != null) {

                    if (InitConstant.COLLECTION_VALUE_IMPORT.keySet().contains(fieldType)) {
                        //设置为ArrayList
                        javaParameterDTO1.setFullyType(InitConstant.COLLECTION_VALUE_IMPORT.get(fieldType));
                        javaParameterDTO1.setType(InitConstant.getAbbreviation(InitConstant.COLLECTION_VALUE_IMPORT.get(fieldType)));
                    } else if (javaClass1.isInterface()) {
                        //接口 - 设置实现类
                        List<JavaClass> javaClassList = javaClass.getDerivedClasses();
                        if (!javaClassList.isEmpty()) {
                            //处理List
                            log.info("获取的属性类型为:" + fieldType);

                            //取第一个
                            JavaClass javaClass2 = javaClassList.get(0);
                            log.info("获取的属性父类信息：" + javaClass2.getFullyQualifiedName() + "，fullyTypeName=" + fullyTypeName + "，javaClass2.getName=" + javaClass2.getName() + "," + javaClass2.getSimpleName());
                            javaParameterDTO1.setCustomType(true);
                            fullyTypeName = javaClass2.getFullyQualifiedName();
                            javaParameterDTO1.setFullyType(fullyTypeName);
                            if ("List".equals(fullyTypeName)) {
                                log.info("javaParameterDTOList=" + javaParameterDTOList + ",,,javaParameterDTO1=" + javaParameterDTO1);
                            }
                            fieldType = InitConstant.getAbbreviation(fullyTypeName);
                            javaParameterDTO1.setType(fieldType);

                        }
                    }

                    //全称需要增加到导入的包中
                    FullNameHandle.addQualifiedNameToImplementsPackageMap(javaParameterDTO1, javaGenInfoModel.getImplementsJavaPackageMap());
                }
            }

            //判断类是否含有set的注解，注解能够在class生成set方法的
            if (hasAnnotationSet) {
                javaParameterDTOList.add(javaParameterDTO1);
            } else {
                //判断是否有该属性的set方法
                if (methodSet.contains("set" + javaParameterDTO1.getUpName())) {
                    javaParameterDTOList.add(javaParameterDTO1);
                }
            }

        }
    }

    /**
     * 设置参数的值
     * @param javaParameterDTO
     * @param value
     */
    private static void javaParameterDTOSetValue(JavaParameterDTO javaParameterDTO, String value) {
        javaParameterDTO.setValue(value);
    }



    private static void javaParameterDTOSetJsonValue(List<JavaParameterDTO> javaParameterDTOS) {
        //对于一些自定义的参数，进行json赋值
        for (JavaParameterDTO parameterDTO : javaParameterDTOS) {
            log.info("开始进行json赋值："+JSON.toJSONString(parameterDTO));
            if(!parameterDTO.getCustomType()){
                //非自定义的参数不进行赋值  - 后续再处理基础的类型等等
                continue;
            }
            List<JavaParameterDTO> javaParameters = parameterDTO.getJavaParameterDTOList();

            //获取到配置
            JsonConfig jsonConfig = CommonConstant.CONFIG_ENTITY.getJsonConfig();
            log.info("开始进行json赋值,获取到配置："+JSON.toJSONString(jsonConfig));
            if(jsonConfig == null){
                return;
            }
            boolean isOpen = jsonConfig.getIsOpen();
            if(!isOpen){
                return;
            }
            List<JsonConfigList> jsonConfigLists = jsonConfig.getList();
            if(CollectionUtils.isEmpty(jsonConfigLists)){
                return;
            }

            //处理不同作用域的值
            globalValue(parameterDTO, javaParameters, jsonConfigLists);
            packageValue(parameterDTO, javaParameters, jsonConfigLists);
            classValue(parameterDTO, javaParameters, jsonConfigLists);
            methodValue(parameterDTO, javaParameters, jsonConfigLists);
        }
    }

    /**
     * 处理方法
     * @param parameterDTO
     * @param javaParameters
     * @param jsonConfigLists
     */
    private static void methodValue(JavaParameterDTO parameterDTO, List<JavaParameterDTO> javaParameters, List<JsonConfigList> jsonConfigLists) {
        for (JsonConfigList jsonConfigList : jsonConfigLists) {
            String scope = jsonConfigList.getScope();
            //全局
            if(JsonConfigListScopeEnum.METHOD.getValue().equals(scope)){
                //如果不是自定义的类型
                if(!parameterDTO.getCustomType()){
                    continue;
                }
                //设置值
                if(!parameterDTO.getFullyType().equals(jsonConfigList.getFullyType())){
                    continue;
                }
                //判断是否是一个类下的
                String scopeValue = jsonConfigList.getScopeValue();
                if(StringUtils.isEmpty(scopeValue)){
                    log.warn("【primo-generator-mock-test】json配置的作用域为METHOD，未配置scopeValue,jsonConfigList="+JSON.toJSONString(jsonConfigList));
                    continue;
                }
                if(!scopeValue.equals(parameterDTO.getMethodfullyType())){
                    continue;
                }

                //获取到值，进行设置
                jsonValueSet(javaParameters, jsonConfigList);

            }
        }
    }
    /**
     * 处理类
     * @param parameterDTO
     * @param javaParameters
     * @param jsonConfigLists
     */
    private static void classValue(JavaParameterDTO parameterDTO, List<JavaParameterDTO> javaParameters, List<JsonConfigList> jsonConfigLists) {
        for (JsonConfigList jsonConfigList : jsonConfigLists) {
            String scope = jsonConfigList.getScope();
            //全局
            if(JsonConfigListScopeEnum.CLASS.getValue().equals(scope)){
                //如果不是自定义的类型
                if(!parameterDTO.getCustomType()){
                    continue;
                }
                //设置值
                if(!parameterDTO.getFullyType().equals(jsonConfigList.getFullyType())){
                    continue;
                }
                //判断是否是一个类下的
                String scopeValue = jsonConfigList.getScopeValue();
                if(StringUtils.isEmpty(scopeValue)){
                    log.warn("【primo-generator-mock-test】json配置的作用域为CLASS，未配置scopeValue,jsonConfigList="+JSON.toJSONString(jsonConfigList));
                    continue;
                }
                if(!scopeValue.equals(parameterDTO.getClassfullyType())){
                    continue;
                }

                //获取到值，进行设置
                jsonValueSet(javaParameters, jsonConfigList);

            }
        }
    }

    /**
     * 处理包
     * @param parameterDTO
     * @param javaParameters
     * @param jsonConfigLists
     */
    private static void packageValue(JavaParameterDTO parameterDTO, List<JavaParameterDTO> javaParameters, List<JsonConfigList> jsonConfigLists) {
        for (JsonConfigList jsonConfigList : jsonConfigLists) {
            String scope = jsonConfigList.getScope();
            //全局
            if(JsonConfigListScopeEnum.PACKAGE.getValue().equals(scope)){
                //如果不是自定义的类型
                if(!parameterDTO.getCustomType()){
                    continue;
                }
                //设置值
                if(!parameterDTO.getFullyType().equals(jsonConfigList.getFullyType())){
                    continue;
                }
                //判断是否是一个包下的
                String scopeValue = jsonConfigList.getScopeValue();
                if(StringUtils.isEmpty(scopeValue)){
                    log.warn("【primo-generator-mock-test】json配置的作用域为PACKAGE，未配置scopeValue,jsonConfigList="+JSON.toJSONString(jsonConfigList));
                    continue;
                }
                if(!scopeValue.equals(parameterDTO.getPackageName())){
                    continue;
                }

                //获取到值，进行设置
                jsonValueSet(javaParameters, jsonConfigList);

            }
        }
    }

    /**
     * 处理全局
     * @param parameterDTO
     * @param javaParameters
     * @param jsonConfigLists
     */
    private static void globalValue(JavaParameterDTO parameterDTO, List<JavaParameterDTO> javaParameters, List<JsonConfigList> jsonConfigLists) {
        for (JsonConfigList jsonConfigList : jsonConfigLists) {
            String scope = jsonConfigList.getScope();
            //全局
            if(JsonConfigListScopeEnum.GLOBAL.getValue().equals(scope)){
                //如果不是自定义的类型
                if(!parameterDTO.getCustomType()){
                    continue;
                }
                //设置值
                if(!parameterDTO.getFullyType().equals(jsonConfigList.getFullyType())){
                    continue;
                }
                //获取到值，进行设置
                jsonValueSet(javaParameters, jsonConfigList);
            }
        }
    }


    private static void jsonValueSet(List<JavaParameterDTO> javaParameters, JsonConfigList jsonConfigList) {
        String value = jsonConfigList.getValue();
        JSONObject jsonObject = JSON.parseObject(value);
        log.info("开始进行json赋值,jsonObject：" + jsonObject);

        for (JavaParameterDTO javaParameter : javaParameters) {
            Object obj = jsonObject.get(javaParameter.getName());
            if (obj == null) {
                continue;
            }
            javaParameter.setValue(obj.toString());
            if ("String".equals(javaParameter.getType())) {
                javaParameter.setValue("\"" + obj.toString() + "\"");
            }
            log.info("设置的值为：" + javaParameter);
        }
    }



}
