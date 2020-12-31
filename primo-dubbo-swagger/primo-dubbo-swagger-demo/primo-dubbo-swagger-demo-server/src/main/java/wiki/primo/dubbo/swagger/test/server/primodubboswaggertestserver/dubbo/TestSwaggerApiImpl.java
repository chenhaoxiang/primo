/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.dubbo.swagger.test.server.primodubboswaggertestserver.dubbo;

import org.springframework.stereotype.Service;
import wiki.primo.dubbo.swagger.test.api.api.TestSwaggerApi;
import wiki.primo.dubbo.swagger.test.api.req.TestReq;
import wiki.primo.dubbo.swagger.test.api.resp.TestResp;

/**
 * @author chenhx
 * @version TestSwaggerApiImpl.java, v 0.1 2019-08-20 09:58 chenhx
 */
@Service
public class TestSwaggerApiImpl implements TestSwaggerApi {
    @Override
    public TestResp test(TestReq testReq) {
        if (testReq == null) {
            return null;
        }
        TestResp testResp = new TestResp();
        testResp.setName(testReq.getName());
        testResp.setAge(testReq.getAge());
        return testResp;
    }
}
