/*
 * primo.wiki
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.dubbo.swagger.core.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chenhx
 * @version EnumUtils.java, v 0.1 2019-11-13 15:09 chenhx
 */
@Slf4j
public class EnumUtils {

    /**
     * 通过枚举类获取 枚举说明
     *
     * @param enumClass
     * @return
     */
    public static String getEnumClassDesc(Class enumClass) {
        StringBuilder desc = new StringBuilder();
        if (enumClass.isEnum()) {
            //枚举
            //获取class中的属性
            Field[] declaredFields = enumClass.getDeclaredFields();
            List<String> names = new ArrayList<>();
            for (Field field : declaredFields) {
                names.add(field.getName());
            }
            Set<String> methodName = new HashSet<>();
            Method[] methods = enumClass.getMethods();
            for (Method method : methods) {
                methodName.add(method.getName());
            }
            List<String> methodsInvokes = new ArrayList<>();
            //删除没有get方法的属性,要遵循驼峰命名
            for (String name : names) {
                String method = "get" + StringUtils.capitalizeFirstLetter(name);
                if (methodName.contains(method)) {
                    methodsInvokes.add(method);
                }
            }

            //获取class中的属性,实际的枚举值
            Object[] fields = enumClass.getEnumConstants();
            if (fields != null) {
                try {
                    desc.append("【枚举说明：");
                    for (Object field : fields) {
                        desc.append("（");
                        //调用get方法
                        for (int i = 0; i < methodsInvokes.size(); i++) {
                            Method method = enumClass.getDeclaredMethod(methodsInvokes.get(i));
                            Object r = method.invoke(field);
                            if (i == 0) {
                                desc.append(r.toString());
                            } else {
                                desc.append(",").append(r.toString());
                            }
                        }
                        desc.append("）");
                    }
                    desc.append("】");
                } catch (Exception e) {
                    log.error("获取枚举说明出现异常，枚举类:" + enumClass.getSimpleName(), e);
                }
            }

        }
        return desc.toString();
    }

    /**
     * 通过枚举类获取 枚举的集合，
     *
     * @param enumClass
     * @param enumAttributeName
     * @return
     */
    public static String getAllowableValue(Class enumClass, String enumAttributeName) {
        StringBuilder desc = new StringBuilder();
        if (enumClass.isEnum()) {
            String methodsInvoke = "get" + StringUtils.capitalizeFirstLetter(enumAttributeName);

            String[] attrs = enumAttributeName.split(",");
            if (attrs.length > 1) {
                //说明没有配置，或者是用户配置了多个，优先匹配第一个，再依次匹配后面的
                //获取class中的属性
                Field[] declaredFields = enumClass.getDeclaredFields();
                Set<String> names = new HashSet<>();
                for (Field field : declaredFields) {
                    names.add(field.getName());
                }
                for (String attr : attrs) {
                    if (names.contains(attr)) {
                        methodsInvoke = "get" + StringUtils.capitalizeFirstLetter(attr);
                        break;
                    }
                }
            }


            //获取class中的属性,实际的枚举值
            Object[] fields = enumClass.getEnumConstants();
            if (fields != null) {
                //调用get方法
                try {
                    for (int i = 0; i < fields.length; i++) {
                        Method method = enumClass.getDeclaredMethod(methodsInvoke);
                        Object r = method.invoke(fields[i]);
                        if (i == 0) {
                            desc.append(r.toString());
                        } else {
                            desc.append(",").append(r.toString());
                        }
                    }
                } catch (Exception e) {
                    log.error("获取枚举说明出现异常，枚举类:" + enumClass.getSimpleName(), e);
                }
            }

        }
        return desc.toString();
    }

}
