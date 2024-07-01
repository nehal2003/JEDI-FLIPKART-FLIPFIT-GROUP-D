package com.flipkart.restcontroller;

import com.flipkart.model.*;
import com.flipkart.service.*;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.validation.Validator;

import java.util.List;

@Path("/gymowner")
@Produces(MediaType.APPLICATION_JSON)
public class GymOwnerController {
    Validator validator;
    FlipFitGymOwnerService gymOwnerService;

    public GymOwnerController(FlipFitGymOwnerServiceOperation gymOwnerService) {
        this.gymOwnerService = gymOwnerService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(Credentials credentials){
        System.out.println(credentials.getUser()+credentials.getPassword());
        if(gymOwnerService.validateLogin(credentials.getUser(), credentials.getPassword())){
            System.out.println("Login Successful");
            return Response.ok().build();
        }
        else{
            System.out.println("Login Unsuccessful");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addgymwithslots")
    public Response addGymWithSlots(FlipFitGym gym){
        System.out.println(gym.getSlots()+" Kk");
        gymOwnerService.addGymWithSlots(gym);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response createGymOwner(FlipFitGymOwner gymOwner){
        gymOwnerService.createGymOwner(gymOwner);
        return Response.ok().build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/viewMyGyms/{id}")
    public Response viewMyGyms(@PathParam("id") String id){
        List<FlipFitGym> gymList = gymOwnerService.viewMyGyms(id);
        return Response.ok(gymList).build();
    }
}
