package com.alipay.sofa.service.impl;

import com.alipay.sofa.common.dal.dao.UserDAO;
import com.alipay.sofa.common.dal.tables.User;
import com.alipay.sofa.facade.UserAuthorizationService;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import org.jboss.resteasy.util.HttpServletRequestDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;
import com.alipay.sofa.common.util.AES;
import com.alipay.sofa.common.util.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.alipay.sofa.common.util.JWT;
import com.alipay.sofa.facade.model.user.RegisterUserRequest;
import com.alipay.sofa.facade.model.user.LoginRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Date;
import java.util.List;


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
        Map<String,String> data = new HashMap<>();
        /*String name = request.getParameter("name");
        String password = request.getParameter("password");*/
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
                String jwt = JWT.createJWT(15*60*1000,secret,user);
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

        User user1 = userDAO.save(user);
        if(user1.getId() > 0) {
            return Result.success("success");
        } else {
            return Result.failed("db error");
        }
    }
}
