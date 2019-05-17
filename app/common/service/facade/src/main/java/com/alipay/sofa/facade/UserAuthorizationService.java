package com.alipay.sofa.facade;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    Map<String,String> login(HttpServletRequest request);
}
