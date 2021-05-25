/*
 * primo.wiki
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.dubbo.swagger.test.api.api;


import wiki.primo.dubbo.swagger.api.ApiMethod;
import wiki.primo.dubbo.swagger.api.ApiParam;
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
    @ApiMethod(value = "测试类", notes = "", params = {
            @ApiParam(name = "testReq", value = "测试参数")
    })
    TestResp test(TestReq testReq);
}
