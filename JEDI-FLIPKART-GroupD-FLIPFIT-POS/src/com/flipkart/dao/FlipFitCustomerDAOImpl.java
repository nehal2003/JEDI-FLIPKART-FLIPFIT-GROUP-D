package com.flipkart.dao;

import com.flipkart.bean.FlipFitBookings;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitSlots;
import com.flipkart.bean.FlipFitUser;
import com.flipkart.exception.BookingCancellationFailedException;
import com.flipkart.exception.RegistrationFailedException;
import com.flipkart.exception.SlotsUnavailableException;
import com.flipkart.utils.DatabaseConnector;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of FlipFitCustomerDAOInterface for customer operations.
 */
public class FlipFitCustomerDAOImpl implements FlipFitCustomerDAOInterface {

    DatabaseConnector connector;
    Connection conn;

    @Override
    public List<FlipFitGym> getAllGymsByArea() {
        conn = DatabaseConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<FlipFitGym> flipFitGyms = new ArrayList<>();

        try {
            String sqlQuery = "SELECT * FROM gyms";
            preparedStatement = conn.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("gymId");
                String gymAddress = resultSet.getString("gymAddress");
                String location = resultSet.getString("location");
                String gymName = resultSet.getString("gymName");
                String status = resultSet.getString("status");
                String gymOwnerID = resultSet.getString("ownerid");

                // Skip gyms that are already verified
                if (Objects.equals(status, "unverified")) continue;

                FlipFitGym flipFitGym = new FlipFitGym();
                flipFitGym.setGymId(id);
                flipFitGym.setGymName(gymName);
                flipFitGym.setGymAddress(gymAddress);
                flipFitGym.setOwnerId(gymOwnerID);
                flipFitGym.setLocation(location);
                flipFitGym.setStatus(status);

                // Fetch and set slots for this gym
                List<FlipFitSlots> flipFitSlots = getGymSlotsWithGymId(id);
                flipFitGym.setSlots(flipFitSlots);

                flipFitGyms.add(flipFitGym);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flipFitGyms;
    }

    @Override
    public boolean bookSlot(int gymId, int time, String email) {
        conn = DatabaseConnector.getConnection();
        PreparedStatement preparedStatement = null;
        String insertQuery = "INSERT INTO Booking (userId, status, date, time, slotId, GymId ) VALUES(?,?,?,?,?,?)";

        try {
            // Check if slots are available
            int remaining = getSeatNumberWithGymIDandSlotIdFromSlots(gymId, time);
            if (remaining <= 0) {
                throw new SlotsUnavailableException();
            }

            preparedStatement = conn.prepareStatement(insertQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, "CONFIRMED");
            preparedStatement.setInt(3, 11); // Assuming date is fixed for this example
            preparedStatement.setInt(4, time);
            preparedStatement.setInt(5, time);
            preparedStatement.setInt(6, gymId);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                // Successfully booked
                alterSeatsWithGymIDSlotID(gymId, time, remaining - 1);
                System.out.println("Booking successful!");
                return true;
            } else {
                throw new SlotsUnavailableException();
            }

        } catch (SlotsUnavailableException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    private int getSeatNumberWithGymIDandSlotIdFromSlots(int gymId, int time) {
        conn = DatabaseConnector.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        int remainingSeats = 0;

        try {
            String sqlQuery = "SELECT seatCount FROM slots WHERE gymId = ? AND startTime = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, gymId);
            preparedStatement.setInt(2, time);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                remainingSeats = resultSet.getInt("seatCount");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return remainingSeats;
    }

    private void alterSeatsWithGymIDSlotID(int gymId, int time, int remainingSeats) {
        conn = DatabaseConnector.getConnection();

        try {
            String sqlQuery = "UPDATE slots SET seatCount = ? WHERE gymId = ? AND startTime = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, remainingSeats);
            preparedStatement.setInt(2, gymId);
            preparedStatement.setInt(3, time);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Seats updated successfully!");
            } else {
                System.out.println("Failed to update seats.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<FlipFitBookings> getAllBookingByUserID(String userId) {
        conn = DatabaseConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<FlipFitBookings> flipFitBookings = new ArrayList<>();

        try {
            String sqlQuery = "SELECT * FROM Booking WHERE userId = ?";
            preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("bookingId");
                int date = resultSet.getInt("date");
                int time = resultSet.getInt("time");
                int slotId = resultSet.getInt("slotId");
                String status = resultSet.getString("status");
                int gymId = resultSet.getInt("gymId");

                FlipFitBookings booking = new FlipFitBookings();
                booking.setBookingId(id);
                booking.setDate(date);
                booking.setTime(time);
                booking.setSlotId(slotId);
                booking.setStatus(status);
                booking.setGymId(gymId);

                flipFitBookings.add(booking);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flipFitBookings;
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        conn = DatabaseConnector.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String deleteQuery = "DELETE FROM Booking WHERE bookingId = ?";
            preparedStatement = conn.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, bookingId);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Booking cancellation successful!");
                return true;
            } else {
                throw new BookingCancellationFailedException();
            }

        } catch (BookingCancellationFailedException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean validateUser(String username, String pass) {
        conn = DatabaseConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sqlQuery = "SELECT password FROM User WHERE email = ?";
            preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String passwordFromDB = resultSet.getString("password");
                return passwordFromDB.equals(pass);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void createUser(FlipFitUser flipFitUser) {
        conn = DatabaseConnector.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String insertQuery = "INSERT INTO User (userName, email, password, phoneNumber, Address, location) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(insertQuery);
            preparedStatement.setString(1, flipFitUser.getUserName());
            preparedStatement.setString(2, flipFitUser.getEmail());
            preparedStatement.setString(3, flipFitUser.getPassword());
            preparedStatement.setString(4, flipFitUser.getPhoneNumber());
            preparedStatement.setString(5, flipFitUser.getAddress());
            preparedStatement.setString(6, flipFitUser.getLocation());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
            	LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = now.format(formatter);
                System.out.println("User registration successful!\n"+formattedDateTime);
            } else {
                throw new RegistrationFailedException();
            }

        } catch (RegistrationFailedException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<FlipFitSlots> getGymSlotsWithGymId(int id) {
        conn = DatabaseConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<FlipFitSlots> slotList = new ArrayList<>();

        try {
            String sqlQuery = "SELECT * FROM slots WHERE gymId = ?";
            preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                
                int startTime = resultSet.getInt("startTime");
              int seats = resultSet.getInt("seatCount");
              FlipFitSlots flipFitSlots = new FlipFitSlots(1,startTime,seats);

              slotList.add(flipFitSlots);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return slotList;
    }
}

