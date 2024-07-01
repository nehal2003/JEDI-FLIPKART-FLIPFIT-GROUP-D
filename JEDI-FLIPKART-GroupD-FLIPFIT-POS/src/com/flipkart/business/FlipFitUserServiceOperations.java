package com.flipkart.business;

import java.util.List;
import com.flipkart.bean.FlipFitBookings;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitUser;
import com.flipkart.dao.FlipFitCustomerDAOImpl;
import com.flipkart.dao.FlipFitCustomerDAOInterface;
import com.flipkart.dao.UpdatePasswordDAOImpl;
import com.flipkart.dao.UpdatePasswordDAOInterface;

/**
 * Implementation of user-related operations for FlipFit application.
 */
public class FlipFitUserServiceOperations implements FlipFitUserServices {

    // DAO interface for interacting with customer data
    FlipFitCustomerDAOInterface flipFitCustomerDAOInterface = new FlipFitCustomerDAOImpl();

    // DAO interface for managing password updates
    UpdatePasswordDAOInterface updatePasswordInterface = new UpdatePasswordDAOImpl();

    @Override
    public boolean verifyGymUserPassword(String email, String password, String updatedPassword) {
       
        return false;
    }

    @Override
    public boolean cancelSlots(int bookingId) {
        // Cancels a booking identified by bookingId
        return flipFitCustomerDAOInterface.cancelBooking(bookingId);
    }

    @Override
    public List<FlipFitBookings> getAllBookings(String userId) {
        // Retrieves all bookings associated with a userId
        return flipFitCustomerDAOInterface.getAllBookingByUserID(userId);
    }

    @Override
    public List<FlipFitGym> getAllGymsWithSlots() {
        // Retrieves all gyms that have available slots
        return flipFitCustomerDAOInterface.getAllGymsByArea();
    }

    @Override
    public List<FlipFitGym> getAllGymsByArea(String area) {
        // Retrieves all gyms located in a specific area
        return flipFitCustomerDAOInterface.getAllGymsByArea();
    }

    @Override
    public boolean bookSlots(int gymId, int time, String email) {
        // Books slots at a gym for a user identified by email
        return flipFitCustomerDAOInterface.bookSlot(gymId, time, email);
    }

    @Override
    public boolean validateUser(String username, String pass) {
        // Validates user credentials against stored data
        return flipFitCustomerDAOInterface.validateUser(username, pass);
    }

    @Override
    public void createUser(FlipFitUser flipFitUser) {
        // Creates a new user account with provided details
        flipFitCustomerDAOInterface.createUser(flipFitUser);
    }

    @Override
    public void updateGymUserPassword(String email, String password, String updatedPassword) {
        // Updates the password of a user identified by email
        updatePasswordInterface.updateGymUserPassword(email, password, updatedPassword);
    }

}
