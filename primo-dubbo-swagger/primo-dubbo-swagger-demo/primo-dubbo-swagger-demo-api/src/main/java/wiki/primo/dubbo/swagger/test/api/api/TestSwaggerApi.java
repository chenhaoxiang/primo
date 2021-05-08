/*
 * primo.wiki
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.dubbo.swagger.test.api.api;


import wiki.primo.dubbo.swagger.test.api.req.TestReq;
import wiki.primo.dubbo.swagger.test.api.resp.TestResp;

/**
 * @author chenhx
 * @version TestSwaggerApi.java, v 0.1 2019-08-20 09:51 chenhx
 */
public interface TestSwaggerApi {
    /**
     * 测试类
     *
     * @param testReq
     * @return
     */
    TestResp test(TestReq testReq);
}
