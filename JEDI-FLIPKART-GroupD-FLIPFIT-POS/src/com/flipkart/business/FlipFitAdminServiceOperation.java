package com.flipkart.business;

import java.util.List;
import java.util.Scanner;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.dao.FlipFitAdminDAOImpl;
import com.flipkart.dao.FlipFitAdminDAOInterface;

/**
 * Implementation of FlipFitAdminService for administrative operations.
 */
public class FlipFitAdminServiceOperation implements FlipFitAdminService {

    // DAO interface instance to interact with database
    FlipFitAdminDAOInterface adminDaoInterface = new FlipFitAdminDAOImpl();

    // Scanner object for user input
    Scanner obj = new Scanner(System.in);

    @Override
    public void viewGymOwners() {
        adminDaoInterface.viewGymOwners(); // Delegate to DAO to view gym owners
    }

    @Override
    public void viewGyms() {
        adminDaoInterface.viewGyms(); // Delegate to DAO to view gyms
    }

    @Override
    public void viewUsers() {
        adminDaoInterface.viewUsers(); // Delegate to DAO to view users
    }

    @Override
    public void verifyGym(int gymId) {
        adminDaoInterface.verifyGyms(gymId); // Delegate to DAO to verify a gym by ID
    }

    @Override
    public void verifyGymOwner(int gymOwnerId) {
        adminDaoInterface.verifyGymOwners(gymOwnerId); // Delegate to DAO to verify a gym owner by ID
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
