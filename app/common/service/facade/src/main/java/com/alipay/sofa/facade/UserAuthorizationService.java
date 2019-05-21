package com.alipay.sofa.facade;


import javax.ws.rs.*;
import com.alipay.sofa.common.util.Result;
import com.alipay.sofa.facade.model.request.LoginRequest;
import com.alipay.sofa.facade.model.request.RegisterUserRequest;

/**
 * @author kerry
 * @date 2019-5-17 11:01:23
 */
@Path("api/v1")
@Consumes("application/json;charset=UTF-8")
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
    Result getUser(@PathParam("id") String id);
}
