/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.dubbo.swagger.test.server.primodubboswaggertestserver.server;


import wiki.primo.dubbo.swagger.test.server.primodubboswaggertestserver.dto.TestReqDTO;
import wiki.primo.dubbo.swagger.test.server.primodubboswaggertestserver.dto.TestRespDTO;

/**
 * @author chenhx
 * @version TestSwagger.java, v 0.1 2019-08-20 10:02 chenhx
 */
public interface TestSwagger {
    /**
     * 测试类
     *
     * @param testReq
     * @return
     */
    TestRespDTO test(TestReqDTO testReq);
}
