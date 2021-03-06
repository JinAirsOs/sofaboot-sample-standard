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
package com.alipay.sofa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.ImportResource;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * SOFABootWebApplication
 * <p>
 * <p>
 * Created by yangguanchao on 16/12/9.
 */
@EnableScheduling
@org.springframework.boot.autoconfigure.SpringBootApplication
@ImportResource({ "rpc-client.xml" })
@EntityScan(basePackageClasses = {
        SOFABootWebApplication.class,
        Jsr310JpaConverters.class
})
public class SOFABootWebApplication {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = SpringApplication.run(SOFABootWebApplication.class, args);
        //zookeeper 注册中心获取service，其余应用可以这样调用StudentRpcService
        /*RegistryConfig registryConfig = new RegistryConfig()
                .setProtocol(RpcConstants.REGISTRY_PROTOCOL_ZK)
                .setAddress("127.0.0.1:2181");//zookeeper地址

        ConsumerConfig<StudentRpcService> consumerConfig = new ConsumerConfig<StudentRpcService>()
                .setInterfaceId(StudentRpcService.class.getName())
                .setRegistry(registryConfig)
                .setTimeout(3000);

        StudentRpcService studentRpcService = consumerConfig.refer();*/
        //zookeeper注册中心获取bean。
        /*StudentRpcService studentRpcService = (StudentRpcService) applicationContext.getBean("studentRpcService");
        System.out.println(studentRpcService.ping());
        System.out.println(studentRpcService.getStudentInfoById(1));
        AppConfig appConfig = (AppConfig) applicationContext.getBean("appConfig");
        System.out.println(appConfig.getProperty("spring.application.secret"));*/
    }
}
