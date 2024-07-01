package com.flipkart.restcontroller;


import com.flipkart.model.*;
import com.flipkart.service.*;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.validation.Validator;

import java.util.List;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerController {
    Validator validator;
    FlipFitUserServices userServices;

    public CustomerController(FlipFitUserServiceOperations userServices) {
        this.userServices = userServices;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(Credentials credentials){
        System.out.println(credentials.getUser()+credentials.getPassword());
        if(userServices.validateUser(credentials.getUser(),credentials.getPassword())){
            return Response.ok("Login Successful").build();
        }
        else return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/viewAllGymswithSlots")
    public List<FlipFitGym> viewAllGymswithSlots(){
        List<FlipFitGym> gymList = userServices.getAllGymsWithSlots();
        return gymList;
//        if(gymList != null)
//            return Response.ok(gymList).build();
//        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/bookSlot/gymId/{id}/time/{time}/email/{email}")
    public Response bookSlot(@PathParam("id") Integer gymId, @PathParam("time") Integer time,@PathParam("email") String email){
        System.out.println(gymId + " " + time + " " + email);
        boolean booked = userServices.bookSlots(gymId, time, email);
        if(booked)
            return Response.ok().build();
        else {
            System.out.println("Booking Unsuccessful!!");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cancelSlot/{id}")
    public Response cancelSlot(@PathParam("id") Integer id){
        boolean booked = userServices.cancelSlots(id);
        if(booked)
            return Response.ok().build();
        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllBookings/{id}")
    public List<FlipFitBookings> getAllBookings(@PathParam("id") String id){
        List<FlipFitBookings> myBookings = userServices.getAllBookings(id);
//        if(!myBookings.isEmpty())
//            return Response.ok(myBookings).build();
//        else return Response.status(Response.Status.NOT_FOUND).build();
        return myBookings;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response createCustomer(FlipFitUser user){
        userServices.createUser(user);
        return Response.ok().build();
    }

}
