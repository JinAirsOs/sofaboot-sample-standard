package com.alipay.sofa.facade;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("studentRestService")
@Consumes("application/json;charset=UTF-8")
@Produces("application/json;charset=UTF-8")
public interface StudentRpcService {
    @GET
    @Path("sayName")
    String sayName();

    @GET
    @Path("getStudentNameById")
    String getStudentNameById(@QueryParam("id") int id);
}
