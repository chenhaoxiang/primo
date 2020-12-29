/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.generator.mock.test.core.util;

import java.util.UUID;

/**
 * @author chenhx
 * @version UUIDUtil.java, v 0.1 2019-06-10 20:37 chenhx
 */
public class UUIDUtils {
    /**
     * 生成10位UUID
     *
     * @return 生成10位UUID
     */
    public static String getID() {
        UUID uuid = UUID.randomUUID();
        // 改变uuid的生成规则
        return HashUtils.convertToHashStr(uuid.getMostSignificantBits(), 5)
                + HashUtils.convertToHashStr(uuid.getLeastSignificantBits(), 5);
    }

}
