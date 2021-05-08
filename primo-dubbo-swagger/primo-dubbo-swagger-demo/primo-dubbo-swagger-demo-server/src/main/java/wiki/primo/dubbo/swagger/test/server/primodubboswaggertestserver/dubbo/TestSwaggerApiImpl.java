/*
 * primo.wiki
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package wiki.primo.dubbo.swagger.test.server.primodubboswaggertestserver.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import wiki.primo.dubbo.swagger.test.api.api.TestSwaggerApi;
import wiki.primo.dubbo.swagger.test.api.req.TestReq;
import wiki.primo.dubbo.swagger.test.api.resp.TestResp;
import wiki.primo.dubbo.swagger.test.server.primodubboswaggertestserver.dto.TestReqDTO;
import wiki.primo.dubbo.swagger.test.server.primodubboswaggertestserver.server.TestSwagger;

/**
 * @author chenhx
 * @version TestSwaggerApiImpl.java, v 0.1 2019-08-20 09:58 chenhx
 */
@Service
public class TestSwaggerApiImpl implements TestSwaggerApi {
    private TestSwagger testSwagger;
    @Override
    public TestResp test(TestReq testReq) {
        if (testReq == null) {
            return null;
        }
        TestReqDTO testReqDTO = new TestReqDTO();
        BeanUtils.copyProperties(testReq,testReqDTO);

        TestResp testResp = new TestResp();
        BeanUtils.copyProperties( testSwagger.test(testReqDTO),testReq);
        return testResp;
    }
}
