package com.alipay.sofa.service.impl;

import com.alipay.sofa.common.util.annotation.JWTAuth;
import com.alipay.sofa.common.util.annotation.JWTAuthFilter;
import com.alipay.sofa.facade.UserAuthorizationService;
import org.junit.Before;
import org.junit.Test;
import javax.ws.rs.*;
import java.lang.annotation.Annotation;

import java.lang.reflect.Method;
import static org.junit.Assert.*;

public class UserAuthorizationServiceImplTest {

    private UserAuthorizationServiceImpl userAuthorizationService = new UserAuthorizationServiceImpl();

    @Before
    public void setUp()throws Exception{


        Method[] methods = UserAuthorizationServiceImpl.class.getDeclaredMethods();

        Annotation[] annotations = UserAuthorizationServiceImpl.class.getAnnotations();
        //Annotation[] annotations = JWTAuthFilter.class.getAnnotations();
        for(Annotation annotation:annotations){
            System.out.println(annotation.toString());
        }

        for(Method method:methods){
            if(method.getName().contains("me")){
                Annotation[] annotations1 = method.getAnnotations();
                for(Annotation annotation:annotations1){
                    System.out.println(annotation.toString());
                }
            }
        }
    }

    @Test
    public void me(){

    }
}