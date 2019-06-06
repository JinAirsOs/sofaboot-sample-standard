package com.alipay.sofa.service.impl;

import com.alipay.sofa.common.dal.dao.UserDAO;
import com.alipay.sofa.common.dal.tables.User;
import com.alipay.sofa.facade.UserAuthorizationService;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;
import com.alipay.sofa.common.util.AES;
import com.alipay.sofa.common.util.Result;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;

import com.alipay.sofa.common.util.JWT;
import com.alipay.sofa.facade.model.request.RegisterUserRequest;
import com.alipay.sofa.facade.model.request.LoginRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static org.apache.commons.lang3.StringUtils.isNotBlank;


@Service
@SofaService(bindings= {@SofaServiceBinding(bindingType = "bolt"),@SofaServiceBinding(bindingType = "rest")})
public class UserAuthorizationServiceImpl implements UserAuthorizationService {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthorizationServiceImpl.class);

    @Resource
    private UserDAO userDAO;

    private String secret;

    @Resource
    private Environment environment;

    public Result login(LoginRequest loginRequest){
        try{
            checkNotNull(loginRequest);
            checkState(isNotBlank(loginRequest.getName()),"user name can't be empty");
            checkState(isNotBlank(loginRequest.getPassword()),"user password can't be empty");
        } catch (Exception e){
            return Result.failed(e);
        }
        Map<String,String> data = new HashMap<>();
        String name = loginRequest.getName();
        String password = loginRequest.getPassword();
        User user = new User();
        user.setName(name);
        Optional<List<User>> userOptional = userDAO.findByName(name);
        if(userOptional.isPresent()){
            List<User> userList = userOptional.get();
            String passwdHash = userList.get(0).getPassword();
            secret = environment.getProperty("spring.application.secret");
            logger.info("secret {}",secret);
            String verify = AES.decrypt(passwdHash,secret);
            if (password.equals(verify)){
                //15分钟
                String jwt = JWT.createJWT(15*60*1000,secret,userList.get(0));
                data.put("authorization",jwt);
                return Result.success(data);
            } else {
                return Result.failed("user and password not match");
            }
        } else {
            return Result.failed("user not found");
        }
    }

    public Result register(RegisterUserRequest registerUserRequest){
        try{
            checkNotNull(registerUserRequest,"empty request");
            checkState(isNotBlank(registerUserRequest.getName()),"user name can't be empty");
            checkState(isNotBlank(registerUserRequest.getPassword()),"user password can't be empty");
            checkState(isNotBlank(registerUserRequest.getRepeatPassword()),"user re-password can't be empty");
            checkState(registerUserRequest.getRepeatPassword().equals(registerUserRequest.getPassword()),"user re-password not same");
        } catch (Exception e){
            return Result.failed(e);
        }
        Map<String,String> data = new HashMap<>();
        String name = registerUserRequest.getName();
        String password = registerUserRequest.getPassword();
        String repeatPassword = registerUserRequest.getRepeatPassword();
        if(!password.equals(repeatPassword)){
            return Result.failed("password not match");
        }

        User user = new User();
        user.setName(name);
        Optional<List<User>> userOptional = userDAO.findByName(name);
        if(userOptional.isPresent()){
            List<User> userList = userOptional.get();
            if(userList.size() > 0){
                return Result.failed("user exists");
            }
        }
        secret = environment.getProperty("spring.application.secret");
        String passwdHash = AES.encrypt(password,secret);
        user.setPassword(passwdHash);
        user.setCreatedAt(new Date());
        user.setEmail(registerUserRequest.getEmail());
        user.setPhone(registerUserRequest.getPhone());

        try {
            User user1 = userDAO.save(user);
            if(user1.getId() > 0) {
                return Result.success("success");
            } else {
                return Result.failed("db error");
            }
        }catch (Exception e) {
            logger.info(e.toString());
            return Result.failed(e);
        }
    }

    public Result getUser(String id) {
        Long userId = Long.parseLong(id);
        Optional<User> userOptional = userDAO.findById(userId);
        logger.info("api/v1/getuser {}",id);
        if(userOptional.isPresent()){
            return Result.success(userOptional.get());
        }else {
            return Result.failed("user not found");
        }
    }

    public Result me(HttpHeaders httpHeaders){
        String authorizationHeader = httpHeaders.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader == null) {
            return Result.failed("no Authorization header");
        }

        try {
            // Extract the token from the HTTP Authorization header
            String token = authorizationHeader.substring("Bearer".length()).trim();

            secret = environment.getProperty("spring.application.secret");

            Claims claims = JWT.parseJWT(token,secret);
            logger.info("valid token : " + token);
            Date now = new Date();
            if(now.after(claims.getExpiration())) {
                throw new Exception("token expired");
            }
            String userId = claims.getSubject();
            Long id = Long.parseLong(userId);
            Optional userOptional = userDAO.findById(id);
            if(!userOptional.isPresent()){
                //no such user
                throw new Exception("token invalid");
            }
            return Result.success(userOptional.get());

        } catch (Exception e) {
            return Result.failed(e);
        }
    }

    public Result echoWithJWTToken(HttpHeaders httpHeaders) {

        String authorizationHeader = httpHeaders.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader == null) {
            return Result.failed("no Authorization header");
        }

        try {
            // Extract the token from the HTTP Authorization header
            String token = authorizationHeader.substring("Bearer".length()).trim();

            secret = environment.getProperty("spring.application.secret");

            Claims claims = JWT.parseJWT(token,secret);
            logger.info("valid token : " + token);
            Date now = new Date();
            if(now.after(claims.getExpiration())) {
                throw new Exception("token expired");
            }
            String userId = claims.getSubject();
            Long id = Long.parseLong(userId);
            Optional userOptional = userDAO.findById(id);
            if(!userOptional.isPresent()){
                //no such user
                throw new Exception("token invalid");
            }
            return Result.success(userId);

        } catch (Exception e) {
            return Result.failed(e);
        }
    }
}
