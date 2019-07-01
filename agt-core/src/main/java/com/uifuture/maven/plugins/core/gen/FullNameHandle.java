/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.gen;

import com.uifuture.maven.plugins.core.common.BaseCanUserType;
import com.uifuture.maven.plugins.core.common.InitConstant;
import com.uifuture.maven.plugins.core.dto.JavaImplementsDTO;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 导入包全称与简称的处理
 * @author chenhx
 * @version FullNameHandle.java, v 0.1 2019-06-27 16:26 chenhx
 */
public class FullNameHandle {

    private static Log log = new SystemStreamLog();


    /**
     * 处理全称限定名称 - 简称
     * 页面进行导入时进行处理
     * @param baseCanUserTypes 被处理的类集合，设置是否能够使用简称
     * @param implementsJavaPackageMap 需要导入的包  如果有多个，全部使用全限定名，在该map中的，表示没有简称相同的类
     *                                 key - 变量名-简称
     *                                 value - 全限定名称
     */
    public static void addQualifiedNameToImplementsPackageMap(List<? extends BaseCanUserType> baseCanUserTypes, Map<String, Set<String>> implementsJavaPackageMap) {
        //处理全限定名称
        for (BaseCanUserType javaParameterDTO : baseCanUserTypes) {
            addQualifiedNameToImplementsPackageMap(javaParameterDTO,implementsJavaPackageMap);
        }
    }

    /**
     * 处理全称限定名称 - 简称
     *
     * @param baseCanUserType          被处理的类，设置是否能够使用简称
     * @param implementsJavaPackageMap 需要导入的包  如果有多个，全部使用全限定名，在该map中的，表示没有简称相同的类
     *                                 key - 变量名-简称
     *                                 value - 全限定名称
     */
    public static void addQualifiedNameToImplementsPackageMap(BaseCanUserType baseCanUserType, Map<String, Set<String>> implementsJavaPackageMap) {
        //处理全限定名称
        String type = baseCanUserType.getType();
        if (implementsJavaPackageMap.containsKey(type)) {
            Set<String> stringList = implementsJavaPackageMap.get(type);
            if(stringList.size()==1){
                for (String fType : stringList) {
                    if(fType.equals(baseCanUserType.getFullyType())){
                        baseCanUserType.setCanUserType(true);
                    }
                }
            }
            stringList.add(baseCanUserType.getFullyType());
        } else {
            Set<String> stringList = new HashSet<>();
            stringList.add(baseCanUserType.getFullyType());
            implementsJavaPackageMap.put(baseCanUserType.getType(), stringList);
            baseCanUserType.setCanUserType(true);
        }

        //接口，处理实现类的全限定名称
        if(baseCanUserType.getIsInterface()){
            String subType = baseCanUserType.getSubClassType();
            if (implementsJavaPackageMap.containsKey(subType)) {

                Set<String> stringList = implementsJavaPackageMap.get(subType);
                if(stringList.size()==1){
                    for (String fType : stringList) {
                        if(fType.equals(baseCanUserType.getSubClassFullyType())){
                            baseCanUserType.setSubClassCanUserType(true);
                        }
                    }
                }
                stringList.add(baseCanUserType.getSubClassFullyType());

            } else {
                Set<String> stringList = new HashSet<>();
                stringList.add(baseCanUserType.getSubClassFullyType());
                implementsJavaPackageMap.put(baseCanUserType.getSubClassType(), stringList);
                baseCanUserType.setSubClassCanUserType(true);
            }

        }
    }


    /**
     * 处理导入的包，排除不需要导入的包
     * @param implementsJavaPackageMap
     *      * 需要导入的包  如果有多个，全部使用全限定名，在该map中的，表示没有简称相同的类
     *      * key - 变量名-简称
     *      * value - 全限定名称
     * @return 需要导入的包 - 简称相同的类，不会被导入
     */
    public static List<JavaImplementsDTO> handleImplements(Map<String, Set<String>> implementsJavaPackageMap) {
        List<JavaImplementsDTO> javaImplementsDTOList = new ArrayList<>();
        //全称限定名称
        for (String key : implementsJavaPackageMap.keySet()) {
            JavaImplementsDTO javaImplementsDTO = new JavaImplementsDTO();
            Set<String> types = implementsJavaPackageMap.get(key);
            if (types.size() == 1) {
                //获取导入
                for (String type : types) {
                    //只会有一个 - 排除基础类型，lang包下的类型
                    if (InitConstant.EXCLUDE_IMPORT_TYPE.contains(type)) {
                        continue;
                    }
                    javaImplementsDTO.setType(type);
                    javaImplementsDTOList.add(javaImplementsDTO);
                }
            }
        }
        return javaImplementsDTOList;
    }

}