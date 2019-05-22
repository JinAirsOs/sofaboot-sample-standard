package com.alipay.sofa.facade;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import com.alipay.sofa.common.util.Result;
import com.alipay.sofa.common.util.annotation.JWTAuth;
import com.alipay.sofa.facade.model.request.LoginRequest;
import com.alipay.sofa.facade.model.request.RegisterUserRequest;

import java.lang.annotation.Inherited;

/**
 * @author kerry
 * @date 2019-5-17 11:01:23
 */
@Path("api/v1")
@Produces("application/json;charset=UTF-8")
public interface UserAuthorizationService {

    @POST
    @Path("login")
    Result login(LoginRequest loginRequest);

    @POST
    @Path("register")
    Result register(RegisterUserRequest registerUserRequest);

    @GET
    @Path("user/{id}")
    @JWTAuth
    Result getUser(@PathParam("id") String id);

    @GET
    @Path("me")
    @JWTAuth
    Result me(@Context HttpHeaders httpHeaders);

    @GET
    @Path("jwt")
    Result echoWithJWTToken(@Context HttpHeaders httpHeaders);
}
