/*
 * wiki.primo
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.core.handle;

import wiki.primo.generator.mock.test.core.constant.CommonConstant;
import wiki.primo.generator.mock.test.core.constant.InitConstant;
import wiki.primo.generator.mock.test.data.dto.JavaMockClassInfoDTO;
import wiki.primo.generator.mock.test.data.dto.JavaMockMethodInfoDTO;
import wiki.primo.generator.mock.test.data.dto.JavaParameterDTO;
import wiki.primo.generator.mock.test.data.info.JavaClassInfo;
import wiki.primo.generator.mock.test.data.model.JavaClassModel;
import wiki.primo.generator.mock.test.data.model.JavaMethodModel;
import wiki.primo.generator.mock.test.data.model.JavaParameteModel;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chenhx
 * @version MockClassInfoHandle.java, v 0.1 2019-06-24 17:21 chenhx
 */
public class MockClassInfoHandle {
    private static Log log = new SystemStreamLog();


    /**
     * 获取mock的信息，
     * 遍历类中属性，以及属性名称，设置到需要mock的类的信息
     *
     * @param javaClass     测试类信息
     * @param javaClassInfo 处理当前测试类时存储的类信息
     * @return 模板中类信息
     */
    public static List<JavaMockClassInfoDTO> getMockClass(JavaClass javaClass, JavaClassInfo javaClassInfo) {
        //需要mock的类
        List<JavaMockClassInfoDTO> javaMockClassInfoDTOList = new ArrayList<>();

        //mock的类方法信息
        Map<String, JavaClassModel> javaClassModelMap = javaClassInfo.getJavaClassModelMap();

        //导入的Java类
        Map<String, Set<String>> implementsJavaPackageMap = javaClassInfo.getImplementsJavaPackageMap();

        List<JavaField> javaFieldList = javaClass.getFields();

        //遍历待测试类的属性
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
            //类型全限定名
            String fullyQualifiedName = javaField.getType().getFullyQualifiedName();
            String type = InitConstant.getAbbreviation(fullyQualifiedName);

            JavaClassModel javaClassModel = new JavaClassModel();
            //属性名称
            javaClassModel.setName(javaField.getName());
            javaClassModel.setFullyType(fullyQualifiedName);
            javaClassModel.setType(type);

            if (implementsJavaPackageMap.containsKey(type)) {
                Set<String> stringList = implementsJavaPackageMap.get(type);
                stringList.add(fullyQualifiedName);
                implementsJavaPackageMap.put(type, stringList);
            } else {
                Set<String> stringList = new HashSet<>();
                stringList.add(fullyQualifiedName);
                implementsJavaPackageMap.put(type, stringList);
            }

            //设置属性名称和全限定名称
            Map<String, String> fullyTypeNameMap = javaClassInfo.getFieldFullyTypeNameMap();
            if (!fullyTypeNameMap.containsKey(javaClassModel.getName())) {
                fullyTypeNameMap.put(javaClassModel.getName(), javaClassModel.getFullyType());
            }

            List<JavaMethodModel> javaMethodModelList = new ArrayList<>();
            JavaClass superClass = javaField.getType().getSuperJavaClass();
            //获取该类中的方法
            JavaClass fieldClass = CommonConstant.javaProjectBuilder.getClassByName(fullyQualifiedName);

//            log.debug("获取该类的源码为:" + fieldClass.getSource());

            List<JavaMethod> fieldMethodList = fieldClass.getMethods();

            for (JavaMethod javaMethod : fieldMethodList) {
                JavaMethodModel javaMethodModel = getJavaMethodModel(javaMethod, javaField.getName(), javaField.getType().getFullyQualifiedName(), superClass);
                String key = javaClassModel.getName() + "." + javaMethodModel.getName();
                Map<String, String> mockFullyTypeNameMap = javaClassInfo.getMockFullyTypeNameMap();
                if (!mockFullyTypeNameMap.containsKey(key)) {
                    mockFullyTypeNameMap.put(key, javaClassModel.getFullyType());
                }
                javaMethodModelList.add(javaMethodModel);
            }
            javaClassModel.setJavaMethodModelList(javaMethodModelList);
            if (!javaClassModelMap.containsKey(javaClassModel.getFullyType())) {
                javaClassModelMap.put(javaClassModel.getFullyType(), javaClassModel);
            }

            //该类是接口
            if (javaField.getType().isInterface()) {
                //获取该接口的父接口
                List<JavaClass> javaClassList = javaField.getType().getInterfaces();
                if (javaClassList != null) {
                    for (JavaClass aClass : javaClassList) {
                        superClass = aClass;
                        // 第三方包，可以通过插件导入包解决解析源码问题
                        log.info("获取的父类接口：" + superClass.getSource());
                        handleSuperClass(javaClassInfo, javaField, javaMockClassInfoDTO, superClass, javaClassModel);
                    }
                }
            } else {
                //TODO 获取父类 - 暂时只支持两层 - 暂时也不支持其他jar包中的类,引入包即可
                if (superClass != null) {
                    log.info("获取的父类：" + superClass.getSource());
                    handleSuperClass(javaClassInfo, javaField, javaMockClassInfoDTO, superClass, javaClassModel);

                } else {
                    log.info("无法解析到父类:" + javaField.getType());
                }
            }

            javaMockClassInfoDTO.setName(javaField.getName());
            javaMockClassInfoDTO.setType(type);
            javaMockClassInfoDTO.setFullyType(fullyQualifiedName);

            javaMockClassInfoDTOList.add(javaMockClassInfoDTO);
        }

        //处理父类的方法，进行mock父类的方法
        if (CommonConstant.CONFIG_ENTITY.getIsMockThisOtherMethod()) {
            mockThisOtherMethod(javaClass, javaClassInfo, javaClassModelMap);
        }

        //属性的相关信息
        log.info("本类属性相关信息，类：" + javaClass.getName());
        return javaMockClassInfoDTOList;
    }

    /**
     * mock父类的方法，mock父类和当前测试类非本测试方法的方法
     *
     * @param javaClass         测试类信息
     * @param javaGenInfoModel  存储类的信息
     * @param javaClassModelMap 类信息存储，key - 类的全限定名称，value - 类信息
     */
    private static void mockThisOtherMethod(JavaClass javaClass, JavaClassInfo javaGenInfoModel, Map<String, JavaClassModel> javaClassModelMap) {
        JavaClass superJavaClass = javaClass.getSuperJavaClass();
        JavaClassModel superJavaClassModel = new JavaClassModel();
        superJavaClassModel.setName(javaGenInfoModel.getModelNameLowerCamel());
        superJavaClassModel.setType(InitConstant.getAbbreviation(superJavaClass.getFullyQualifiedName()));
        superJavaClassModel.setFullyType(superJavaClass.getFullyQualifiedName());
        List<JavaMethodModel> javaMethodModelList1 = new ArrayList<>();
        //遍历父类的方法
        for (JavaMethod method : superJavaClass.getMethods()) {
            JavaMethodModel javaMethodModel = getJavaMethodModel(method, javaGenInfoModel.getModelNameLowerCamel(), javaClass.getFullyQualifiedName(), superJavaClass);
            String key = "this." + javaMethodModel.getName();
            Map<String, String> mockFullyTypeNameMap = javaGenInfoModel.getMockFullyTypeNameMap();
            if (!mockFullyTypeNameMap.containsKey(key)) {
                mockFullyTypeNameMap.put(key, superJavaClassModel.getFullyType());
            }
            javaMethodModelList1.add(javaMethodModel);
        }
        superJavaClassModel.setJavaMethodModelList(javaMethodModelList1);
        if (!javaClassModelMap.containsKey(superJavaClassModel.getFullyType())) {
            javaClassModelMap.put(superJavaClassModel.getFullyType(), superJavaClassModel);
        }
    }

    /**
     * 存储父类的信息
     *
     * @param javaGenInfoModel     存储的类信息
     * @param javaField            类属性信息
     * @param javaMockClassInfoDTO 模板中的类信息
     * @param superClass           父类
     * @param javaClassModel       类信息
     */
    private static void handleSuperClass(JavaClassInfo javaGenInfoModel, JavaField javaField, JavaMockClassInfoDTO javaMockClassInfoDTO, JavaClass superClass, JavaClassModel javaClassModel) {
        //父类的方法
        Map<String, JavaClassModel> javaClassModelMap = javaGenInfoModel.getJavaClassModelMap();
        List<JavaMethod> superJavaMethod = superClass.getMethods();
        JavaClassModel superJavaClassModel = new JavaClassModel();
        superJavaClassModel.setName(superClass.getName());
        superJavaClassModel.setType(InitConstant.getAbbreviation(superClass.getFullyQualifiedName()));
        superJavaClassModel.setFullyType(superClass.getFullyQualifiedName());
        List<JavaMethodModel> javaMethodModelList1 = new ArrayList<>();

        for (JavaMethod javaMethod : superJavaMethod) {
            JavaMethodModel javaMethodModel = getJavaMethodModel(javaMethod, javaField.getName(), javaField.getType().getFullyQualifiedName(), superClass);
            String key = javaClassModel.getName() + "." + javaMethodModel.getName();
            Map<String, String> mockFullyTypeNameMap = javaGenInfoModel.getMockFullyTypeNameMap();
            if (!mockFullyTypeNameMap.containsKey(key)) {
                mockFullyTypeNameMap.put(key, superJavaClassModel.getFullyType());
            }
            javaMethodModelList1.add(javaMethodModel);
        }
        superJavaClassModel.setJavaMethodModelList(javaMethodModelList1);
        if (!javaClassModelMap.containsKey(superJavaClassModel.getFullyType())) {
            javaClassModelMap.put(superJavaClassModel.getFullyType(), superJavaClassModel);
        }

        //TODO 暂时只处理一个接口 ,如果是接口，暂时设置最后一个接口的全限定名称 - 后面需要将接口和类进行区分
        javaMockClassInfoDTO.setParentClassFullyType(superClass.getFullyQualifiedName());

    }


    /**
     * 设置mock方法的信息
     *
     * @param javaField  类属性
     * @param javaMethod 方法
     * @param superClass 父类
     */
    private static JavaMockMethodInfoDTO setMockMethodInfo(JavaField javaField, JavaMethod javaMethod, JavaClass superClass) {

        JavaMethodModel javaMethodModel = getJavaMethodModel(javaMethod, javaField.getName(), javaField.getType().getFullyQualifiedName(), superClass);

        JavaMockMethodInfoDTO javaMockMethodInfoDTO = new JavaMockMethodInfoDTO();

        javaMockMethodInfoDTO.setParentClassFullyType(javaMethodModel.getParentClassFullyType());
        javaMockMethodInfoDTO.setFieldName(javaMethodModel.getFieldName());
        javaMockMethodInfoDTO.setClassType(javaMethodModel.getClassType());
        javaMockMethodInfoDTO.setName(javaMethodModel.getName());

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

        return javaMockMethodInfoDTO;
    }


    /**
     * 获取方法的信息，参数，方法返回值，参数类型
     *
     * @param javaMethod 方法
     * @param fieldName  调用该方法的变量名
     * @param classType  该方法的类的类型
     * @param superClass 父类
     * @return 方法信息
     */
    private static JavaMethodModel getJavaMethodModel(JavaMethod javaMethod, String fieldName, String classType, JavaClass superClass) {
        JavaMethodModel javaMethodModel = new JavaMethodModel();

        javaMethodModel.setFieldName(fieldName);
        javaMethodModel.setClassType(classType);
        javaMethodModel.setName(javaMethod.getName());

        javaMethodModel.setName(javaMethod.getName());
        //方法参数
        List<JavaParameter> javaParameterList = javaMethod.getParameters();
        List<JavaParameteModel> javaParameteModelList = new ArrayList<>();

        //方法参数遍历
        Integer order = 1;
        for (JavaParameter javaParameter : javaParameterList) {
            JavaParameteModel javaParameteModel = new JavaParameteModel();
            //全限定类型 的名称
            String typeS = javaParameter.getType().getFullyQualifiedName();
            String pType = InitConstant.getAbbreviation(typeS);
            javaParameteModel.setName(javaParameter.getName());
            javaParameteModel.setUpName(javaParameter.getName().substring(0, 1).toUpperCase() + javaParameter.getName().substring(1));
            javaParameteModel.setType(pType);
            javaParameteModel.setFullyType(typeS);
            javaParameteModel.setKeyName("");
            //TODO 设置值
            javaParameteModel.setCustomType(true);
            javaParameteModel.setValue("");
            javaParameteModel.setOrder(order);
            order++;

            javaParameteModelList.add(javaParameteModel);
        }

        javaMethodModel.setJavaParameteModelList(javaParameteModelList);

        String rTypeStr = javaMethod.getReturnType().getFullyQualifiedName();
        javaMethodModel.setReturnFullyType(rTypeStr);

        String rType = InitConstant.getAbbreviation(rTypeStr);
        javaMethodModel.setReturnType(rType);

        if (superClass != null) {
            //设置父类类型
            javaMethodModel.setParentClassFullyType(superClass.getFullyQualifiedName());
        }
        return javaMethodModel;
    }

}
