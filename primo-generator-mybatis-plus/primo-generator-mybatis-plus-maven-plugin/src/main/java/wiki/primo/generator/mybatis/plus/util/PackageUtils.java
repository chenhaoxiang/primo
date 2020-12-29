/*
 * chenhx
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package wiki.primo.generator.mybatis.plus.util;

import org.apache.commons.lang.StringUtils;

/**
 * @author chenhx
 * @version 0.0.1
 */
public class PackageUtils {

    /**
     * 连接父子包名
     *
     * @param parent     父包名
     * @param subPackage 子包名
     * @return 连接后的包名
     */
    public static String joinPackage(String parent, String subPackage) {
        if (StringUtils.isBlank(parent)) {
            return subPackage;
        }
        return parent + "." + subPackage;
    }


}
