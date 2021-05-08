package wiki.primo.dubbo.swagger.core.extension;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * primo.wiki
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
public class ApiParamReaderTest extends TestCase {


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class c = Object.class;

        System.out.println(JSON.toJSONString(c.getEnumConstants()));
        //获取class中的属性
        Field[] declaredFields = c.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field.getName());
        }
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());

        }


        Object[] fields = c.getEnumConstants();
        for (Object field : fields) {
            System.out.println(field.toString());
            Method method = c.getDeclaredMethod("getName");
            Object r = method.invoke(field);
            System.out.println(r.toString());


        }

    }
}
