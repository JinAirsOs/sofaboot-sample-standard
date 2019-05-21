package com.alipay.sofa.common.util.annotation;

import com.alipay.sofa.common.dal.dao.UserDAO;
import io.jsonwebtoken.Claims;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import com.alipay.sofa.common.util.JWT;

import javax.annotation.Resource;
import javax.ws.rs.ext.Provider;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerRequestContext;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.Date;

import java.io.IOException;
import java.util.Optional;

@Provider
@JWTAuth
@Priority(Priorities.AUTHENTICATION)
public class JWTAuthFilter implements ContainerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(JWTAuthFilter.class);
    @Resource
    private Environment environment;

    private String secret;

    private UserDAO userDAO;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        try {
            // Extract the token from the HTTP Authorization header
            String token = authorizationHeader.substring("Bearer".length()).trim();

            secret = environment.getProperty("spring.application.secret");

            Claims claims = JWT.parseJWT(token,secret);
            logger.info("valid token : " + token);
            Date now = new Date();
            if(claims.getExpiration().after(now)) {
                throw new Exception("token expired");
            }
            String userId = claims.getId();
            Long id = Long.parseLong(userId);
            Optional userOptional = userDAO.findById(id);
            if(!userOptional.isPresent()){
                //no such user
                throw new Exception("token invalid");
            } else {
                requestContext.setProperty("user",userOptional.get());
            }

        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}