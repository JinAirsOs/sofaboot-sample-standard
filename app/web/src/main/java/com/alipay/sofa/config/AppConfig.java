package com.alipay.sofa.config;

import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@Component
public class AppConfig {

    @Resource
    private Environment environment;

    public String getProperty(String key) {
        return environment.getProperty(key);
    }
}