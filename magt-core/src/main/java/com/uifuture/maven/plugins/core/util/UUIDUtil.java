/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.maven.plugins.core.util;

import java.util.UUID;

/**
 * @author chenhx
 * @version UUIDUtil.java, v 0.1 2019-06-10 20:37 chenhx
 */
public class UUIDUtil {

    /**
     * 生成10位UUID
     * @return 生成10位UUID
     */
    public static String getID() {
        UUID uuid = UUID.randomUUID();
        // 改变uuid的生成规则
        return HashUtil.convertToHashStr(uuid.getMostSignificantBits(), 5)
                + HashUtil.convertToHashStr(uuid.getLeastSignificantBits(), 5);
    }

}