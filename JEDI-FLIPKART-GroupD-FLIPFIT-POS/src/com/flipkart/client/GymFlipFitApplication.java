package com.flipkart.client;
import com.flipkart.business.FlipFitGymOwnerService;
import com.flipkart.business.FlipFitGymOwnerServiceOperation;
import com.flipkart.business.FlipFitUserServiceOperations;
import com.flipkart.business.FlipFitUserServices;
import com.flipkart.utils.DatabaseConnector;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Scanner;


public class GymFlipFitApplication {
    static GymFlipFitGymOwnerMenu owner = new GymFlipFitGymOwnerMenu();
    static GymFlipFitCustomerMenu customer = new GymFlipFitCustomerMenu();
    static FlipFitGymOwnerService flipFitGymOwnerService = new FlipFitGymOwnerServiceOperation();

    static FlipFitUserServices userService = new FlipFitUserServiceOperations();
    static Scanner obj = new Scanner(System.in);

    static Properties pr = new Properties();
    
    /*
     * @main application 
     * @param args
     */

   
    public static void main(String[] args) {
        System.out.println("************************************************************");
        System.out.println("        Welcome to the FlipFit Application!!");
        System.out.println("************************************************************");
        boolean exitFlag = false;
        while(true) {
            System.out.println("================================");
            System.out.println("Press 1 for Registration");
            System.out.println("Press 2 for Login");
            System.out.println("Press 3 for Update Password");
            System.out.println("Press 4 for Exit");
            int option= Integer.parseInt(obj.nextLine());
            switch (option) {
                case 2 :
                    System.out.println("Enter email");
                    String userId = obj.nextLine();
                    System.out.println("Enter password");
                    String password = obj.nextLine();
                    System.out.println("Enter role (Admin/Customer/GymOwner)");
                    String role = obj.nextLine();

                    switch (role) {
                        case "Admin" :
                            GymFlipFitAdminMenu admin = new GymFlipFitAdminMenu();

                            if(!admin.verifyAdminCredentials(userId,password)){
                                System.out.println("Invalid Credentials");
                                break;
                            }

                            boolean flag = true;
                            LocalDateTime now = LocalDateTime.now();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            String formattedDateTime = now.format(formatter);
                            System.out.println("Login Successful\n"+formattedDateTime);
                            while(flag) {

                                System.out.println("Press 1 for View all users");
                                System.out.println("Press 2 for View all Gyms");
                                System.out.println("Press 3 for View all Gym Owners");
                                System.out.println("Press 4 for Verify Gym");
//                                System.out.println("Press 5 for Verify GymOwner");
                                System.out.println("Press 5 for View pending Gyms");
//                                System.out.println("Press 7 for View pending Gym Owners");
                                System.out.println("Press 6 for Exit");

                                int k = Integer.parseInt(obj.nextLine());

                                switch (k) {
                                    case 1:
                                        admin.viewUsers();
                                        break;
                                    case 2:
                                        admin.viewGyms();
                                        break;
                                    case 3:
                                        admin.viewGymOwners();
                                        break;
                                    case 4:
                                        System.out.println("Enter the Gym Id to be verified ");
                                        int id1 = Integer.parseInt(obj.nextLine());
                                        admin.verifyGym(id1);
                                        break;
//                                    case 5:
//                                        System.out.println("Enter the Gym Owner Id to be verified ");
//                                        int id2 = Integer.parseInt(obj.nextLine());
//                                        admin.verifyGymOwner(id2);
//                                        break;
                                    case 5:
                                        admin.viewUnverifiedGyms();
                                        break;
//                                    case 7:
//                                        admin.viewUnverifiedGymOwners();
//                                        break;
                                    case 6:
                                        flag = false;
                                        break;
                                }
                                if(!flag) break;
                            }
                            break;

                        case "Customer" :
                            if(!customer.userLogin(userId,password))
                                System.out.println("Invalid credentials");
                            break;
                        case "GymOwner" :
                            if(!owner.gymOwnerLogin(userId,password)){
                                System.out.println("Invalid credentials");
                            }

                            break;
                        default:
                            System.out.println("Invalid Options Selected. Please Try Again:(");
                            break;

                    }

                    break;
                case 1 :
                    System.out.println("Press 1 to Register as a GymOwner");
                    System.out.println("Press 2 to Register as a Customer");
                    System.out.println("Press 3 to Go Back");
                    int k = Integer.parseInt(obj.nextLine());
                    switch(k){
                        case 2:
                            customer.createCustomer();
                            break;
                        case 1:
                            owner.createGymOwner();
                        default:
                            break;
                    }
                    break;
                case 3 :
                    System.out.println("-----------Password Change -----------------------");
                    System.out.println("Enter email");
                    String user = obj.nextLine();
                    System.out.println("Enter password");
                    String userPassword = obj.nextLine();
                    System.out.println("Enter role (Admin/Customer/GymOwner)");
                    String respectiveRole = obj.nextLine();
                    System.out.println("Enter New password");
                    String updatedPassword = obj.nextLine();

                    switch (respectiveRole) {
                        case "Customer" :
                            if(!customer.validateUser(user,userPassword))
                                System.out.println("Invalid credentials");
                            else{
                                userService.updateGymUserPassword(user,userPassword, updatedPassword);
                            }
                            break;
                        case "GymOwner" :
                            if(!owner.verifyGymOwner(user,userPassword)){
                                System.out.println("Invalid credentials");
                            }
                            else{
                                flipFitGymOwnerService.updateGymOwnerPassword(user,userPassword, updatedPassword);
                            }

                            break;
                    }
                    break;
                case 4 :
                    //end
                    exitFlag = true;
                    System.out.println("Thank you for using FlipFit :)");
                    break;
                default:
                    System.out.println("Invalid Options Selected. Please Try Again:( ");
                    break;
                }
            if(exitFlag)break;
        }


    }
}

