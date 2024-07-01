package com.flipkart.dao;

import com.flipkart.model.*;


import java.util.List;

/**
 * Interface for administrative operations related to gyms and gym owners.
 */
public interface FlipFitAdminDAOInterface {

    /**
     * Retrieves and displays details of all gyms.
     */
    public List<FlipFitGym> viewGyms();

    /**
     * Retrieves and displays details of all users.
     */
    public List<FlipFitUser> viewUsers();

    /**
     * Retrieves and displays details of all gym owners.
     */
    public List<FlipFitGymOwner> viewGymOwners();

    /**
     * Verifies a gym owner identified by the given id.
     *
     * @param id The ID of the gym owner to verify
     */
    public String verifyGymOwners(int id);

    /**
     * Verifies a gym identified by the given id.
     *
     * @param id The ID of the gym to verify
     */
    public String verifyGyms(int id);

    /**
     * Retrieves a list of gyms that are currently unverified.
     *
     * @return List of unverified gyms
     */
    public List<FlipFitGym> getUnverifiedGyms();

    /**
     * Retrieves a list of gym owners that are currently unverified.
     *
     * @return List of unverified gym owners
     */
    public List<FlipFitGymOwner> getUnverifiedGymOwner();
}
