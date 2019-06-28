/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.build;

import com.uifuture.maven.plugins.core.common.ConfigConstant;
import com.uifuture.maven.plugins.core.gen.FullNameHandle;

import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaPackage;
import com.thoughtworks.qdox.model.JavaType;
import com.uifuture.maven.plugins.core.common.BaseConstant;
import com.uifuture.maven.plugins.core.dto.JavaClassDTO;
import com.uifuture.maven.plugins.core.dto.JavaImplementsDTO;
import com.uifuture.maven.plugins.core.dto.JavaMethodDTO;
import com.uifuture.maven.plugins.core.dto.JavaMockClassInfoDTO;
import com.uifuture.maven.plugins.core.dto.JavaParameterDTO;
import com.uifuture.maven.plugins.core.gen.MockClassInfo;
import com.uifuture.maven.plugins.core.model.JavaGenInfoModel;
import com.uifuture.maven.plugins.core.util.StringUtil;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 构建测试类
 * @author chenhx
 * @version BuildClass.java, v 0.1 2019-06-10 15:58 chenhx
 */
public class BuildClass {
    private static Log log = new SystemStreamLog();

    public static void main(String[] args) throws IOException {
        String mainJava = "/Users/chenhx/Desktop/github/auto-unit-test-plugin/src/main/java/";

        String name = "com.uifuture.maven.plugins.core.test.ActionServiceImpl";

        JavaClassDTO javaClassDTO = new JavaClassDTO();
        javaClassDTO.setDate(BaseConstant.DATE);
        javaClassDTO.setAuthor(ConfigConstant.CONFIG_ENTITY.getAuthor());

        BaseConstant.javaProjectBuilder.addSourceTree(new File(mainJava));
        build(name,javaClassDTO);
    }

    /**
     * 生成测试方法
     *
     * @param javaName     java类的全限定名称
     * @param javaClassDTO
     */
    public static Boolean build(String javaName, JavaClassDTO javaClassDTO) throws IOException {
        //获取Java类
        JavaClass javaClass = BaseConstant.javaProjectBuilder.getClassByName(javaName);
        log.info("正在构建类：" + javaClass);
        //防御性编程
        if (javaClass == null) {
            log.error("未查询到该类，请确保项目包中有该类，类名：" + javaName);
            return false;
        }

        if (javaClass.isInterface()) {
            log.info("跳过接口，" + javaClass);
            return false;
        }
        if (javaClass.isEnum()) {
            log.info("跳过枚举，" + javaClass);
            return false;
        }
        if (javaClass.isAbstract()) {
            log.info("跳过抽象类，" + javaClass);
            return false;
        }
        if (javaClass.isPrivate()) {
            log.info("跳过私有类，" + javaClass);
            return false;
        }

        JavaGenInfoModel javaGenInfoModel = new JavaGenInfoModel();

        javaGenInfoModel.setModelNameLowerCamel(javaClassDTO.getModelNameLowerCamel());

        //设置mock的信息
        List<JavaMockClassInfoDTO> javaMockClassInfoDTOList = MockClassInfo.getMockClass(javaClass, javaGenInfoModel);
        //需要引入的mcok类
        javaClassDTO.setJavaMockClassInfoDTOList(javaMockClassInfoDTOList);


        //包装类的内部属性 - 包含了父类的属性
        Map<String, List<JavaParameterDTO>> javaParameterDTOMap = new HashMap<>();

        List<JavaMethodDTO> javaMethodDTOList = BuildClassMethod.build(javaClass, javaParameterDTOMap, javaGenInfoModel);
        javaClassDTO.setJavaMethodDTOList(javaMethodDTOList);


        //获取导入的包 - 处理导入的包
        Map<String, Set<String>> implementsJavaPackageMap = javaGenInfoModel.getImplementsJavaPackageMap();
        List<JavaImplementsDTO> javaImplementsDTOList = FullNameHandle.handleImplements(implementsJavaPackageMap);
        javaClassDTO.setJavaImplementsDTOList(javaImplementsDTOList);

        //处理测试类的参数，需要导入的包
        //遍历方法
        for (JavaMethodDTO javaMethodDTO : javaMethodDTOList) {
            //遍历参数
            List<JavaParameterDTO> javaParameterDTOList = javaMethodDTO.getJavaParameterDTOList();
            for (JavaParameterDTO javaParameterDTO : javaParameterDTOList) {
                String type = javaParameterDTO.getType();
                if (implementsJavaPackageMap.containsKey(type)) {
                    //包含该类型
                    Set<String> types = implementsJavaPackageMap.get(type);
                    //有多个简称，使用全名
                    if (types.size() > 1) {
                        //设置全名
                        javaParameterDTO.setType(javaParameterDTO.getFullyType());
                    }
                }
            }
        }

        javaClassDTO.setJavaParameterDTOMap(javaParameterDTOMap);
        //设置包名
        JavaPackage pkg = javaClass.getPackage();
        javaClassDTO.setPackageName(pkg.getName());

        log.info("构建的类信息：" + javaClassDTO);
        return true;
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
            javaImplementsDTO.setType(javaType.getFullyQualifiedName());
            javaImplementsDTOList.add(javaImplementsDTO);
        }
        return javaImplementsDTOList;
    }

}