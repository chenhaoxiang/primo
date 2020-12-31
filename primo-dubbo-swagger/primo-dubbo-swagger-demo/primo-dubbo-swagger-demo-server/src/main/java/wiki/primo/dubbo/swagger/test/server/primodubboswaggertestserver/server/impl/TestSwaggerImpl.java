/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.dubbo.swagger.test.server.primodubboswaggertestserver.server.impl;

import org.springframework.stereotype.Service;
import wiki.primo.dubbo.swagger.test.server.primodubboswaggertestserver.dto.TestReqDTO;
import wiki.primo.dubbo.swagger.test.server.primodubboswaggertestserver.dto.TestRespDTO;
import wiki.primo.dubbo.swagger.test.server.primodubboswaggertestserver.server.TestSwagger;

/**
 * @author chenhx
 * @version TestSwaggerApiImpl.java, v 0.1 2019-08-20 09:58 chenhx
 */
@Service
public class TestSwaggerImpl implements TestSwagger {
    @Override
    public TestRespDTO test(TestReqDTO testReq) {
        if (testReq == null) {
            return null;
        }
        TestRespDTO testResp = new TestRespDTO();
        testResp.setName(testReq.getName());
        testResp.setAge(testReq.getAge());
        return testResp;
    }
}
