package com.flipkart.business;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;

import java.util.List;

/**
 * Interface defining operations for FlipFit admin service.
 */
public interface FlipFitAdminService {

    /**
     * View all registered gym owners.
     */
    public void viewGymOwners();

    /**
     * View all registered gyms.
     */
    public void viewGyms();

    /**
     * View all registered users.
     */
    public void viewUsers();

    /**
     * Verify a gym by its unique identifier.
     * @param gymId The unique identifier of the gym to verify
     */
    public void verifyGym(int gymId);

    /**
     * Verify a gym owner by their unique identifier.
     * @param gymOwnerId The unique identifier of the gym owner to verify
     */
    public void verifyGymOwner(int gymOwnerId);

    /**
     * Get a list of unverified gym owners.
     * @return List of unverified gym owners
     */
    public List<FlipFitGymOwner> getUnverifiedGymOwners();

    /**
     * Get a list of unverified gyms.
     * @return List of unverified gyms
     */
    public List<FlipFitGym> getUnverifiedGyms();
}
