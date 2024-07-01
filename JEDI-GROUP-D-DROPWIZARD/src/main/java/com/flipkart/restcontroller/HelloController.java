package com.flipkart.restcontroller;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/helloApi")
@Produces(MediaType.APPLICATION_JSON)
public class HelloController {
    // Drop wizard provide various kind of method for create, update, delete operations
    // 1. GET --> for fetch
    // 2. POST --> for create
    // 3. PUT --> for update
    // 4. DELETE --> for delete
    // repository means service or dao layer

    @GET
    @Path("/helloService")
    public Response getHello() {

        return Response.ok("Hello My First fetch service").build();
    }
}