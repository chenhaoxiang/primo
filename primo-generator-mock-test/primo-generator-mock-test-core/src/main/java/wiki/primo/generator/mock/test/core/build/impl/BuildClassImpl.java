/*
 * wiki.primo
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.core.build.impl;

import wiki.primo.generator.mock.test.core.build.BuildClassMethodImpl;
import wiki.primo.generator.mock.test.core.constant.CommonConstant;
import wiki.primo.generator.mock.test.core.handle.FullNameHandle;
import wiki.primo.generator.mock.test.core.handle.MockClassInfoHandle;
import wiki.primo.generator.mock.test.core.util.StringUtils;
import wiki.primo.generator.mock.test.data.dto.JavaClassDTO;
import wiki.primo.generator.mock.test.data.dto.JavaImplementsDTO;
import wiki.primo.generator.mock.test.data.dto.JavaMethodDTO;
import wiki.primo.generator.mock.test.data.dto.JavaMockClassInfoDTO;
import wiki.primo.generator.mock.test.data.dto.JavaParameterDTO;
import wiki.primo.generator.mock.test.data.info.JavaClassInfo;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaPackage;
import com.thoughtworks.qdox.model.JavaType;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 构建测试类
 *
 * @author chenhx
 * @version BuildClassImpl.java, v 0.1 2019-06-10 15:58 chenhx
 */
public class BuildClassImpl {
    private static Log log = new SystemStreamLog();

    /**
     * 生成测试类
     * @param javaClassInfo java类信息
     * @return true-生成成功，false-生成失败
     * @throws IOException IO异常
     */
    public static JavaClassDTO build(JavaClassInfo javaClassInfo) throws IOException {
        //模板类信息
        JavaClassDTO javaClassDTO = new JavaClassDTO();
        //构建信息
        javaClassDTO.setDate(CommonConstant.DATE);
        javaClassDTO.setAuthor(CommonConstant.CONFIG_ENTITY.getAuthor());
        javaClassDTO.setModelNameUpperCamel(javaClassInfo.getTypeName());
        javaClassDTO.setModelNameLowerCamel(StringUtils.strConvertLowerCamel(javaClassInfo.getTypeName()));
        javaClassDTO.setModelNameUpperCamelTestClass(javaClassInfo.getTypeName() + CommonConstant.TEST_CLASS_SUFFIX);
        javaClassDTO.setModelNameLowerCamelTestClass(StringUtils.strConvertLowerCamel(javaClassInfo.getTypeName() + CommonConstant.TEST_CLASS_SUFFIX));

        //通过QDox 获取Java类
        JavaClass javaClass = CommonConstant.javaProjectBuilder.getClassByName(javaClassInfo.getFullyTypeName());
        log.info("当前正在构建类：" + javaClass);

        //是否跳过类的测试生成
        if (defensiveProgramming(javaClassInfo.getFullyTypeName(), javaClass)) {
            return null;
        }

        //设置首字母小写的类名  被测试的类名
        javaClassInfo.setModelNameLowerCamel(javaClassDTO.getModelNameLowerCamel());

        //设置mock的信息
        List<JavaMockClassInfoDTO> javaMockClassInfoDTOList = MockClassInfoHandle.getMockClass(javaClass, javaClassInfo);

        //需要引入的mcok类
        javaClassDTO.setJavaMockClassInfoDTOList(javaMockClassInfoDTOList);

        //设置方法，属性
        List<JavaMethodDTO> javaMethodDTOList = BuildClassMethodImpl.build(javaClass, javaClassInfo);

        javaClassDTO.setJavaMethodDTOList(javaMethodDTOList);


        //获取导入的包 - 处理导入的包
        Map<String, Set<String>> implementsJavaPackageMap = javaClassInfo.getImplementsJavaPackageMap();
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

        //设置包名
        JavaPackage pkg = javaClass.getPackage();
        javaClassDTO.setPackageName(pkg.getName());

        log.debug("构建的类信息：" + javaClassDTO);
        return javaClassDTO;
    }

    /**
     * 是否跳过类的测试生成
     *
     * @param fullyTypeName 类的全限定名
     * @param javaClass     类信息
     * @return 返回是否跳过类的测试生成，true-跳过
     */
    private static boolean defensiveProgramming(String fullyTypeName, JavaClass javaClass) {
        if (javaClass == null) {
            log.error("未查询到该类，请确保项目包中有该类，类名：" + fullyTypeName);
            return true;
        }
        if (javaClass.isInterface()) {
            log.info("跳过接口，" + javaClass);
            return true;
        }
        if (javaClass.isEnum()) {
            log.info("跳过枚举，" + javaClass);
            return true;
        }
        if (javaClass.isAbstract()) {
            log.info("跳过抽象类，" + javaClass);
            return true;
        }
        if (javaClass.isPrivate()) {
            log.info("跳过私有类，" + javaClass);
            return true;
        }
        return false;
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
