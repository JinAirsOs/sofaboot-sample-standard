package com.alipay.sofa.config;

import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

@Service("appConfig")
public class AppConfig {

    @Resource
    private Environment environment;

    @Value("${spring.application.secret}")
    private String secret;

    public String getProperty(String key) {
        return environment.getProperty(key);
    }

    public String getSecret(){
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}