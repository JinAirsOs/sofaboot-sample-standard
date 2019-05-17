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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@SofaService(bindings= {@SofaServiceBinding(bindingType = "bolt"),@SofaServiceBinding(bindingType = "rest")})
public class UserAuthorizationServiceImpl implements UserAuthorizationService {


    @Resource
    private UserDAO userDAO;

    private String secret="45c6bbd1c529e4d3231870ffa752f82ee74b7c0910ed6124b88c2b20edf549f4";

    @Resource
    private Environment environment;

    public Result login(){
        Map<String,String> data = new HashMap<>();
        /*String name = request.getParameter("name");
        String password = request.getParameter("password");*/
        String name = "sc2lover";
        String password="password";
        User user = new User();
        user.setName(name);
        Optional<User> userOptional = userDAO.findOne(Example.of(user));
        if(userOptional.isPresent()){
            user = userOptional.get();
            String passwdHash = user.getPassword();
            secret = environment.getProperty("spring.application.secret");
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
}
