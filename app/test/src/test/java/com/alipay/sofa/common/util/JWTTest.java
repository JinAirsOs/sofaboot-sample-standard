package com.alipay.sofa.common.util;

import com.alipay.sofa.common.dal.dao.UserDAO;
import com.alipay.sofa.common.dal.tables.Student;
import com.alipay.sofa.common.dal.tables.User;
import io.jsonwebtoken.Claims;
import org.junit.Before;
import org.junit.Test;
import com.alipay.sofa.common.util.JWT;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Date;

public class JWTTest {

    private String name = "test";
    private String secret = "testsecret";
    private User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("test");
    }

    @Test
    public void createJWT() {

        String jwtString = JWT.createJWT(15*60*1000,secret,user);
        System.out.println(jwtString);
        Claims claims = JWT.parseJWT(jwtString,secret);
        assertEquals(user.getId().toString(),claims.getSubject());
        Date now = new Date();
        assertTrue(now.before(claims.getExpiration()));

        assertTrue(user.getName().equals(claims.get("name")));
    }

    @Test
    public void parseJWT() {
        String jwtString = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwibmFtZSI6InRlc3QiLCJleHAiOjE1NTg0OTYyMzgsImlhdCI6MTU1ODQ5NTMzOCwianRpIjoiZjZkYjg0YmEtZWM2Yy00ZjVhLWIwZDMtZjUxZTY0ZGUzMjMyIn0.xNnNWnsNBUVr8V9dh6XiomoSCB9ey-NcbegmAQivmfs";

        Claims claims = JWT.parseJWT(jwtString,secret);
        assertEquals(user.getId().toString(),claims.getSubject());
        Date now = new Date();
        assertTrue(now.after(claims.getExpiration()));
        assertTrue(user.getName().equals(claims.get("name")));
    }
}