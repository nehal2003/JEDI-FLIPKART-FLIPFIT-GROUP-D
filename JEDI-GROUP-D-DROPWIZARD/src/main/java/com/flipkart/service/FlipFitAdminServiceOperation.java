package com.flipkart.service;

import com.flipkart.model.*;
import com.flipkart.model.FlipFitGymOwner;
import com.flipkart.dao.*;
import com.flipkart.dao.FlipFitAdminDAOInterface;

import java.util.List;
import java.util.Scanner;

/**
 * Implementation of FlipFitAdminService for administrative operations.
 */
public class FlipFitAdminServiceOperation implements FlipFitAdminService {

    // DAO interface instance to interact with database
    FlipFitAdminDAOInterface adminDaoInterface = new FlipFitAdminDAOImpl();

    // Scanner object for user input
    Scanner obj = new Scanner(System.in);

    @Override
    public List<FlipFitGymOwner> viewGymOwners() {
        return adminDaoInterface.viewGymOwners(); // Delegate to DAO to view gym owners
    }

    @Override
    public List<FlipFitGym> viewGyms() {
        return adminDaoInterface.viewGyms(); // Delegate to DAO to view gyms
    }

    @Override
    public List<FlipFitUser> viewUsers() {
        return adminDaoInterface.viewUsers(); // Delegate to DAO to view users
    }

    @Override
    public String verifyGym(int gymId) {
        return adminDaoInterface.verifyGyms(gymId); // Delegate to DAO to verify a gym by ID
    }

    @Override
    public String verifyGymOwner(int gymOwnerId) {
        return adminDaoInterface.verifyGymOwners(gymOwnerId); // Delegate to DAO to verify a gym owner by ID
    }

    @Override
    public List<FlipFitGymOwner> getUnverifiedGymOwners() {
        return adminDaoInterface.getUnverifiedGymOwner(); // Retrieve unverified gym owners from DAO
    }

    @Override
    public List<FlipFitGym> getUnverifiedGyms() {
        return adminDaoInterface.getUnverifiedGyms(); // Retrieve unverified gyms from DAO
    }
}
