/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.core.build;

import wiki.primo.generator.mock.test.core.constant.InitConstant;
import wiki.primo.generator.mock.test.data.dto.JavaMethodDTO;
import wiki.primo.generator.mock.test.data.dto.JavaMethodExceptionsDTO;
import wiki.primo.generator.mock.test.data.info.JavaClassInfo;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 构建类中的方法
 *
 * @author chenhx
 * @version BuildClassMethodImpl.java, v 0.1 2019-06-27 16:37 chenhx
 */
public class BuildClassMethodImpl {

    private static Log log = new SystemStreamLog();

    /**
     * 构建测试类核心方法
     *
     * @param javaClass        被测试类的信息
     * @param javaGenInfoModel 贯穿本次类构造，记录类信息
     * @return 方法DTO集合
     */
    public static List<JavaMethodDTO> build(JavaClass javaClass,
                                            JavaClassInfo javaGenInfoModel) {
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

            //处理重名方法
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
            BuildClassMethodParameteImpl.build(javaMethod, javaGenInfoModel, javaMethodDTO,javaClass);


            //方法抛出的异常
            List<JavaMethodExceptionsDTO> javaMethodExceptionsDTOS = getJavaExceptionsDTOList(javaMethod);
            javaMethodDTO.setJavaMethodExceptionsDTOList(javaMethodExceptionsDTOS);

            //Mock方法的设置
            BuildMockClassMethodImpl.buildMock(javaGenInfoModel, javaMethod, javaMethodDTO);


            javaMethodDTOList.add(javaMethodDTO);
        }
        return javaMethodDTOList;
    }


    /**
     * 处理重名方法
     *
     * @param methodMap     测试方法名称出现的次数，如果有多个重名方法，方法后面接上数字
     * @param methodName    方法名称
     * @param javaMethodDTO 方法展示信息
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
     * 排除静态方法和私有方法
     *
     * @param javaMethod 类方法信息
     * @return true-进行排除，false-不是静态方法和私有方法
     */
    private static boolean excludeMethod(JavaMethod javaMethod) {
        //是否是静态方法
        boolean mStatic = javaMethod.isStatic();
        if (mStatic) {
            return true;
        }
        boolean isPublic = javaMethod.isPublic();
        return !isPublic;
    }


    /**
     * 方法抛出的异常
     *
     * @param javaMethod
     * @return
     */
    private static List<JavaMethodExceptionsDTO> getJavaExceptionsDTOList(JavaMethod javaMethod) {
        List<JavaClass> exceptions = javaMethod.getExceptions();
        List<JavaMethodExceptionsDTO> javaMethodExceptionsDTOS = new ArrayList<>();
        for (JavaClass exception : exceptions) {
            JavaMethodExceptionsDTO javaMethodExceptionsDTO = new JavaMethodExceptionsDTO();
            javaMethodExceptionsDTO.setType(exception.getFullyQualifiedName());
            javaMethodExceptionsDTOS.add(javaMethodExceptionsDTO);
        }
        return javaMethodExceptionsDTOS;
    }


}
