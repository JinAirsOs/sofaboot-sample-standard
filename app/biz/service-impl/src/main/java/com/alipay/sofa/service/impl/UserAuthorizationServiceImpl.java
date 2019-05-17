package com.alipay.sofa.service.impl;

import com.alipay.sofa.common.dal.dao.UserDAO;
import com.alipay.sofa.common.dal.tables.User;
import com.alipay.sofa.facade.UserAuthorizationService;
import org.springframework.data.domain.Example;
import com.alipay.sofa.config.AppConfig;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserAuthorizationServiceImpl implements UserAuthorizationService {
    @Resource
    private UserDAO userDAO;

    @Resource
    private AppConfig appConfig;
    //private String secret;

    public Map<String,String> login(HttpServletRequest request){
        Map<String,String> data = new HashMap<>();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = new User();
        user.setName(name);
        Optional<User> userOptional = userDAO.findOne(Example.of(user));
        if(userOptional.isPresent()){
            user = userOptional.get();
            String passwdHash = user.getPassword();

        } else {
            data.put("success","false");
            data.put("reason", "user password not match");
            return data;
        }
    }
}
