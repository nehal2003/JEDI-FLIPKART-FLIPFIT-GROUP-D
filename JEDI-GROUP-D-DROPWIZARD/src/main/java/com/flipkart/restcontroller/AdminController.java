package com.flipkart.restcontroller;

import com.flipkart.model.*;
import com.flipkart.service.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Properties;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.validation.Validator;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminController {

    private Validator validator;
    private FlipFitAdminService adminService;

    public AdminController(FlipFitAdminServiceOperation adminService){
        this.adminService = adminService;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Credentials credentials){
        //Properties prop = new Properties(); // Properties is used to read files
        //java.net.URL url = ClassLoader.getSystemResource("config.properties");
        /*try {
            prop.load(url.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        String admin_id = "admin";
        String admin_password = "admin123";

        if (credentials.getUser().equals(admin_id) && credentials.getPassword().equals(admin_password)){
            System.out.println("Admin logged in");
            return Response.ok().build();
        }
       else
            return Response.status(Response.Status.UNAUTHORIZED).build();

    }

    @GET
    @Path("/viewGymOwners")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FlipFitGymOwner> viewGymOwners(){
        List<FlipFitGymOwner> gymOwnerList =  adminService.viewGymOwners();
        System.out.println("Check");
        return gymOwnerList;
        //return Response.ok().build();
    }


    @GET
    @Path("/viewGyms")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FlipFitGym> viewGyms(){
        List<FlipFitGym> gyms =  adminService.viewGyms();

        return gyms;
    }

    @GET
    @Path("/viewUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FlipFitUser> viewUsers(){
        List<FlipFitUser> users = adminService.viewUsers();
        return users;
    }

    @PUT
    @Path("/verifyGym/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verifyGym(@PathParam("id") int id){
        String res =  adminService.verifyGym(id);
        return Response.ok(res).build();
    }

    @PUT
    @Path("/verifyGymOwner/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verifyGymOwner(@PathParam("id") int id){
        String res =adminService.verifyGymOwner(id);
        return Response.ok(res).build();
    }

    @GET
    @Path("/getUnverifiedGyms")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FlipFitGym> getUnverifiedGyms(){
        List<FlipFitGym> gym = adminService.getUnverifiedGyms();
        return gym;
    }
}
