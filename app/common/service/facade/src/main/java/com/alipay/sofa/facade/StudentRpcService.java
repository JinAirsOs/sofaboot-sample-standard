package com.alipay.sofa.facade;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("studentRestService")
@Produces(APPLICATION_JSON)
public interface StudentRpcService {
    @GET
    @Path("sayName")
    String sayName();

    @GET
    @Path("getStudentNameById")
    String getStudentNameById(@QueryParam("id") int id);
}
