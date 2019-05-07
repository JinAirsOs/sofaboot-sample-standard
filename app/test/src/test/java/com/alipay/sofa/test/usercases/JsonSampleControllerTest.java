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
package com.alipay.sofa.test.usercases;

import com.alipay.sofa.SOFABootWebApplication;
import com.alipay.sofa.facade.StudentRpcService;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.test.base.AbstractTestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 集成测试
 * JsonSampleControllerTest
 * @author ruoshan
 */
public class JsonSampleControllerTest extends AbstractTestBase {

    @Test
    public void testRequestJson() {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(urlHttpPrefix
                                                                              + "/json",
            String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        String responseBody = responseEntity.getBody();
        LOGGER.info(responseBody);
        Assert.assertTrue(responseBody.contains("zhangsan"));
    }

    @Test
    public void testStudentRpcService() {
        ConsumerConfig<StudentRpcService> consumerConfig = new ConsumerConfig<StudentRpcService>()
                .setInterfaceId(StudentRpcService.class.getName()) // 指定接口
                .setProtocol("bolt") // 指定协议
                .setDirectUrl("bolt://127.0.0.1:12200") // 指定直连地址
                .setConnectTimeout(10 * 1000);

        StudentRpcService studentRpcService = consumerConfig.refer();

        Assert.assertTrue("hello student rpc service".equals(studentRpcService.sayName()));
    }
}
