package com.flipkart.business;

import java.util.List;
import com.flipkart.bean.FlipFitBookings;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitUser;

/**
 * Interface for FlipFit user-related services.
 */
public interface FlipFitUserServices {

    /**
     * Cancels a booking slot identified by slotId.
     * 
     * @param slotId The ID of the slot to cancel
     * @return true if the cancellation was successful, false otherwise
     */
    boolean cancelSlots(int slotId);

    /**
     * Retrieves all bookings associated with a specific userId.
     * 
     * @param userId The ID of the user to retrieve bookings for
     * @return List of FlipFitBookings associated with the user
     */
    List<FlipFitBookings> getAllBookings(String userId);

    /**
     * Retrieves all gyms that have available slots.
     * 
     * @return List of FlipFitGym objects with available slots
     */
    List<FlipFitGym> getAllGymsWithSlots();

    /**
     * Retrieves all gyms located in a specific area.
     * 
     * @param area The geographical area to filter gyms by
     * @return List of FlipFitGym objects in the specified area
     */
    List<FlipFitGym> getAllGymsByArea(String area);

    /**
     * Books a slot at a gym for a user identified by email.
     * 
     * @param gymId The ID of the gym where the slot is being booked
     * @param time The time slot to book
     * @param email The email of the user booking the slot
     * @return true if the booking was successful, false otherwise
     */
    boolean bookSlots(int gymId, int time, String email);

    /**
     * Validates a user's credentials against stored data.
     * 
     * @param username The username to validate
     * @param pass The password associated with the username
     * @return true if the credentials are valid, false otherwise
     */
    boolean validateUser(String username, String pass);

    /**
     * Creates a new user account with the provided details.
     * 
     * @param flipFitUser The FlipFitUser object containing user details
     */
    void createUser(FlipFitUser flipFitUser);

    /**
     * Verifies a gym user's password against an updated password.
     * 
     * @param email The email of the gym user
     * @param password The current password of the gym user
     * @param updatedPassword The new password to verify against
     * @return true if the verification is successful, false otherwise
     */
    boolean verifyGymUserPassword(String email, String password, String updatedPassword);

    /**
     * Updates a gym user's password with a new password.
     * 
     * @param email The email of the gym user
     * @param password The current password of the gym user
     * @param updatedPassword The new password to update
     */
    void updateGymUserPassword(String email, String password, String updatedPassword);
}
