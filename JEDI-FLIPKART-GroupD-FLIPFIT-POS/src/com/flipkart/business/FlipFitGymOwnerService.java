package com.flipkart.business;

import java.util.List;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;

/**
 * Interface defining operations for managing gym owners in FlipFit system.
 */
public interface FlipFitGymOwnerService {

    /**
     * Adds a new gym with its associated time slots.
     * @param flipFitGym The gym object to add
     */
    void addGymWithSlots(FlipFitGym flipFitGym);

    /**
     * Retrieves the list of gyms associated with a specific gym owner.
     * @param userId The unique identifier of the gym owner
     * @return List of gyms owned by the gym owner
     */
    List<FlipFitGym> viewMyGyms(String userId);

    /**
     * Verifies gym owner's password.
     * @param email The email address of the gym owner
     * @param password The password to verify
     * @return true if password matches, false otherwise
     */
    boolean verifyGymOwnerPassword(String email, String password);

    /**
     * Validates gym owner's login credentials.
     * @param email The email address of the gym owner
     * @param password The password to validate
     * @return true if credentials are valid, false otherwise
     */
    boolean validateLogin(String email, String password);

    /**
     * Creates a new gym owner.
     * @param flipFitGymOwner The gym owner object to create
     */
    void createGymOwner(FlipFitGymOwner flipFitGymOwner);

    /**
     * Updates gym owner's password.
     * @param email The email address of the gym owner
     * @param password The current password
     * @param updatedPassword The new password to set
     */
    void updateGymOwnerPassword(String email, String password, String updatedPassword);
}
