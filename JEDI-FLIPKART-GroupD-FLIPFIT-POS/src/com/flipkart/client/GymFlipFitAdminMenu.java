package com.flipkart.client;
import com.flipkart.bean.FlipFitAdmin;


import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitSlots;
import com.flipkart.business.FlipFitAdminService;
import com.flipkart.business.FlipFitAdminServiceOperation;
import com.flipkart.utils.DatabaseConnector;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/*
 * This class represents the administrative menu for the GymFlipFit application.
 * It provides various functions for managing gyms, gym owners, and admin credentials.
 */

public class GymFlipFitAdminMenu {

	    FlipFitAdminService flipFitAdminService = new FlipFitAdminServiceOperation();
	    
	    /*
	     * View all gyms in the system.
	     */
	    public void viewGyms() {
	        flipFitAdminService.viewGyms();
	    }
	    
	    /*
	     * View all registered users in the system.
	     */
	    public void viewUsers() {
	        flipFitAdminService.viewUsers();
	    }
	    /**
	     * View all registered gym owners in the system.
	     */
	    public void viewGymOwners() {
	        flipFitAdminService.viewGymOwners();
	    }

	    /**
	     * Verify a gym by its ID.
	     * @param id The ID of the gym to be verified.
	     */
	    public void verifyGym(int id) {
	        flipFitAdminService.verifyGym(id);
	    }

	    /*
	     * verify a gym owner by their id
	     * @param id is the ID of the gym owner to be verified
	     */
	    public void verifyGymOwner(int id) {
	        flipFitAdminService.verifyGymOwner(id);
	    }

	    /*
	     * view all unverified gyms in the system
	     */
	    public void viewUnverifiedGyms() {
	        List<FlipFitGym> flipFitGyms = flipFitAdminService.getUnverifiedGyms();
	        String leftAlignFormat = "| %-5d | %-20s | %-5d | %-40s | %-20s | %-15s |%n";
	        System.out.format("+-------+----------------------+-------+------------------------------------------+----------------------+------------------+\n");
	        System.out.format("| Gym   |     Name             | Gym Id|           Address                        |   Location           |     Status       |\n");
	        System.out.format("+-------+----------------------+-------+------------------------------------------+----------------------+------------------+\n");

	        int x = 1;
	        for (FlipFitGym gym1 : flipFitGyms) {
	            System.out.format(leftAlignFormat,x,gym1.getGymName(),gym1.getGymId(),gym1.getGymAddress(),gym1.getLocation(),gym1.getStatus()); 
	            x++;
	        }
	        System.out.format("-------------------------------------------------------------------\n");

	    }

	    /*
	     * view all unverified gym owners in the system
	     */
	    public void viewUnverifiedGymOwners() {
	        List<FlipFitGymOwner> g1 = flipFitAdminService.getUnverifiedGymOwners();
	        int x = 1;
	        for (FlipFitGymOwner flipFitGymOwner : g1) {
	            System.out.println("GymOwner " + x + "-->   Gym Owner Id " + flipFitGymOwner.getOwnerId() + "    Email: " + flipFitGymOwner.getOwnerEmail() + "    Phone No: " + flipFitGymOwner.getPhoneNo() + "   Status:" + flipFitGymOwner.getStatus());
	            x++;
	            System.out.println("\n-------------------------------------------------------------");
	        }
	    }

	   /*
	    * verify admin credentials.
	    * @param id The admin ID
	    * @Param pass The admin password
	    * @return True if admin credentials are valid, false otherwise.
	    */
	    public boolean verifyAdminCredentials(String id, String pass) {
	        try {
	            Properties pr = new Properties(); 
	            InputStream inputStream = DatabaseConnector.class.getClassLoader().getResourceAsStream("./config.properties");
	            pr.load(inputStream);
	            String admin_id = pr.getProperty("admin_id");
	            String admin_password = pr.getProperty("admin_password");

	            return (id.equals(admin_id) && pass.equals(admin_password));
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            return false;
	        }
	    }
	}

//}
