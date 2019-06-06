/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.test.integration;

import com.alipay.sofa.facade.StudentRpcService;
import com.alipay.sofa.facade.UserAuthorizationService;
import com.alipay.sofa.facade.model.request.LoginRequest;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.test.base.AbstractTestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.alipay.sofa.common.util.Result;

/**
 * 集成测试
 * Integration test
 * @author kerry.pzd
 */
public class IntegrationTest extends AbstractTestBase {

    @Test
    public void testRequestJson() {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(urlHttpPrefix
                                                                              + "/json",
            String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        String responseBody = responseEntity.getBody();
        LOGGER.info(responseBody);
        Assert.assertTrue(responseBody.contains("pong"));
    }

    @Test
    public void testStudentRpcBoltService() {
        ConsumerConfig<StudentRpcService> consumerConfig = new ConsumerConfig<StudentRpcService>()
                .setInterfaceId(StudentRpcService.class.getName()) // 指定接口
                .setProtocol("bolt") // 指定协议
                .setDirectUrl("bolt://127.0.0.1:12200") // 指定直连地址
                .setConnectTimeout(10 * 1000);

        StudentRpcService studentRpcService = consumerConfig.refer();

        Assert.assertTrue("pong".equals(studentRpcService.ping()));
    }

    @Test
    public void testUserRpcBoltServiceLogin() {
        ConsumerConfig<UserAuthorizationService> consumerConfig = new ConsumerConfig<UserAuthorizationService>()
                .setInterfaceId(UserAuthorizationService.class.getName()) // 指定接口
                .setProtocol("bolt") // 指定协议
                .setDirectUrl("bolt://127.0.0.1:12200") // 指定直连地址
                .setConnectTimeout(10 * 1000);

        UserAuthorizationService userAuthorizationService = consumerConfig.refer();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName("notfound");
        loginRequest.setPassword("test");
        Result result = userAuthorizationService.login(loginRequest);
        Assert.assertTrue(!result.isSuccess());
        Assert.assertNotNull(result.getErrorContext());
    }

    @Test
    public void testUserRpcRestServiceLogin() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName("notfound");
        loginRequest.setPassword("test");
        ResponseEntity<Result> responseEntity = testRestTemplate.postForEntity(restHttpPrefix+"/api/v1/login",loginRequest,Result.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Result result = responseEntity.getBody();

        Assert.assertTrue(!result.isSuccess());
        Assert.assertNotNull(result.getErrorContext());
    }
}
