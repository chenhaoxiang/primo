/*
 * wiki.primo
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.core.build;

import wiki.primo.generator.mock.test.core.constant.CommonConstant;
import wiki.primo.generator.mock.test.data.dto.JavaMethodDTO;
import wiki.primo.generator.mock.test.data.dto.JavaMockMethodInfoDTO;
import wiki.primo.generator.mock.test.data.dto.JavaParameterDTO;
import wiki.primo.generator.mock.test.data.info.JavaClassInfo;
import wiki.primo.generator.mock.test.data.model.JavaClassModel;
import wiki.primo.generator.mock.test.data.model.JavaMethodModel;
import wiki.primo.generator.mock.test.data.model.JavaParameteModel;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenhx
 * @version BuildMockClassMethodImpl.java, v 0.1 2019-06-27 18:31 chenhx
 */
public class BuildMockClassMethodImpl {

    private static Log log = new SystemStreamLog();

    /**
     * 构建mock的类方法信息
     *
     * @param javaGenInfoModel 存储的类信息
     * @param javaMethod       方法信息
     * @param javaMethodDTO    模板的方法信息
     */
    public static void buildMock(JavaClassInfo javaGenInfoModel, JavaMethod javaMethod, JavaMethodDTO javaMethodDTO) {
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
     * @param javaGenInfoModel          存储的类信息
     * @param javaMockMethodInfoDTOList Mock方法集合
     * @param methodCode                方法的源码
     * @param name                      属性变量名称 + "." + 方法名称
     * @param m                         正则匹配
     */
    private static void saveMockMethodInfoDTO(JavaClassInfo javaGenInfoModel,
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
            log.info("获取的变量名称为:" + fieldName + ",全部名称为" + name.split("\\.")[0] + "." + name.split("\\.")[1] + ",匹配的数据为:" + str);
            if ("this".equals(fieldName)) {
                //当前测试类 属性
                fieldName = javaGenInfoModel.getModelNameLowerCamel();
                log.info("获取的变量名称为:" + fieldName + ",进行设置属性变量：" + fieldName);
            }
            javaMockMethodInfoDTO.setFieldName(fieldName);
            //TODO 方法名称 - 这里实际还需要区分参数类型和参数个数，否则无法匹配到唯一的方法,目前不支持重名方法！！！
            //TODO 在后面有临时解决了一下，进行注释该种方法

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
                //TODO 手动拼接 - 未获取到时，通过正则判断
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
            //TODO  暂时没有实际用起来，mock方法的参数
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


        //存储方法名和参数的数量 - 暂时解决重载方法的临时方案 - 参数也一致的情况，进行屏蔽 start
        Map<String,Integer> javaMethodModelMap = new HashMap<>();
        for (JavaMethodModel methodModel : javaMethodModelList) {
            try {
                //方法名是否存在了 ，方法参数数量
                Integer p = methodModel.getJavaParameteModelList().size();
                String key = methodModel.getName()+p;
                if(javaMethodModelMap.containsKey(key)){
                    Integer size =javaMethodModelMap.get(key);
                    javaMethodModelMap.put(key,++size);
                }else {
                    javaMethodModelMap.put(key,1);
                }

                //获取父类是否包含一样的方法
                String pType = methodModel.getParentClassFullyType();
                log.info("methodModel="+methodModel+",pType="+pType);
                JavaClass javaClass = CommonConstant.javaProjectBuilder.getClassByName(pType);
                //获取方法
                List<JavaMethod> methods = javaClass.getMethods();
                for (JavaMethod method : methods) {
                    //父类中的方法
                    if(method.getName().equals(methodModel.getName()) && method.getParameters().size()==methodModel.getJavaParameteModelList().size() ){
                        Integer size =javaMethodModelMap.get(key);
                        javaMethodModelMap.put(key,++size);
                    }
                }
            } catch (NullPointerException e) {
//                log.error(e); 忽略
            }catch (Exception e) {
                log.error(e);
            }

        }
        //mock方法名称 -methodName
        String key =  javaMockMethodInfoDTO.getName() + javaMockMethodInfoDTO.getJavaParameterDTOList().size();
        Integer size =javaMethodModelMap.getOrDefault(key,0);
        if(size > 1){
            javaMockMethodInfoDTO.setIsHaveMoreMethod(true);
        }else {
            javaMockMethodInfoDTO.setIsHaveMoreMethod(false);
        }

        //end


        javaMockMethodInfoDTO.setReturnFullyType(javaMethodModel.getReturnFullyType());
        javaMockMethodInfoDTO.setReturnType(javaMethodModel.getReturnType());


        log.info("mock方法的属性：" + javaMockMethodInfoDTO);
        javaMockMethodInfoDTOList.add(javaMockMethodInfoDTO);
    }


    /**
     * 通过父类进行获取方法的属性
     *
     * @param javaGenInfoModel 存储的类信息
     * @param name             属性变量名称 + "." + 方法名称
     * @param methodName       方法名称
     * @param javaMethodModel  方法信息
     * @return 方法信息
     */
    private static JavaMethodModel getJavaMethodModelByParent(JavaClassInfo javaGenInfoModel, String name, String methodName, JavaMethodModel javaMethodModel) {
        //通过父类再进行获取
        JavaClass javaClass = CommonConstant.javaProjectBuilder.getClassByName(name);
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
