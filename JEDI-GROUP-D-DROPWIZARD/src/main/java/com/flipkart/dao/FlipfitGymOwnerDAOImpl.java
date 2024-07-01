package com.flipkart.dao;

import com.flipkart.model.*;
import com.flipkart.constants.SQLConstants;
import com.flipkart.exception.RegistrationFailedException;
import com.flipkart.exception.SlotInsertionFailedException;
import com.flipkart.utils.DatabaseConnector;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlipfitGymOwnerDAOImpl implements FlipFitGymOwnerDAOInterface {
    Connection conn;

    DatabaseConnector connector;
    @Override
    public void addGym(FlipFitGym flipFitGym){
        conn = DatabaseConnector.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        int id = 0;
        try {
            statement = conn.createStatement();
            preparedStatement =  conn.prepareStatement(SQLConstants.GYM_OWNER_INSERT_GYM, statement.RETURN_GENERATED_KEYS);


            preparedStatement.setString(1, flipFitGym.getGymAddress());
            preparedStatement.setString(2, flipFitGym.getLocation());
            preparedStatement.setString(3, flipFitGym.getGymName());
            preparedStatement.setString(4, flipFitGym.getStatus());
            preparedStatement.setString(5, flipFitGym.getOwnerId());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Record inserted successfully!");
            } else {
                throw new RegistrationFailedException();
////                System.out.println("Failed to insert the record.");
//                return ;
            }
            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                id = rs.getInt(1);
            }


        } catch (RegistrationFailedException ex){
            System.out.println("Gym "+ex.getMessage());

        }
        catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        insertSlots(flipFitGym.getSlots(),id);

    }

    @Override
    public void newGymOwner(FlipFitGymOwner flipFitGymOwner) {
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        conn = DatabaseConnector.getConnection();
      System.out.println("check"+flipFitGymOwner.getPAN());

        try {
            statement = conn.createStatement();
//            resultSet = statement.executeQuery(insertQuery);
            preparedStatement =  conn.prepareStatement(SQLConstants.GYM_OWNER_INSERT);

            // 5. Set values for the placeholders in the prepared statement

            preparedStatement.setString(1, flipFitGymOwner.getOwnerEmail());
            preparedStatement.setString(2, flipFitGymOwner.getOwnerName());
            preparedStatement.setString(3, flipFitGymOwner.getPassword());
            preparedStatement.setString(4, flipFitGymOwner.getPhoneNo());
            preparedStatement.setString(5, flipFitGymOwner.getPAN());
            preparedStatement.setString(7, flipFitGymOwner.getGST());
            preparedStatement.setString(6,flipFitGymOwner.getNationalId());
            preparedStatement.setString(8, flipFitGymOwner.getStatus());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
            	 LocalDateTime now = LocalDateTime.now();
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                 String formattedDateTime = now.format(formatter);
                System.out.println("Gym Owner registered successfully!\n"+formattedDateTime);
            } else {
                throw new RegistrationFailedException();
//                System.out.println("Failed to insert the record.");
//                return ;
            }

        }catch(RegistrationFailedException ex){
            System.out.println("Gym Owner" + ex.getMessage());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean validateLogin(String email, String password) {
        conn = DatabaseConnector.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            statement = conn.createStatement();
            preparedStatement = conn.prepareStatement(SQLConstants.GYM_USER_VERIFY_PASSWORD, statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);


            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public void insertSlots(List<FlipFitSlots> flipFitSlots, int gymId){
        conn = DatabaseConnector.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        for( FlipFitSlots slot: flipFitSlots){
            String insertQuery = "INSERT INTO slots (startTime, seatCount, gymId) VALUES (?, ?, ?)";

            try {
                statement = conn.createStatement();
//                resultSet = statement.executeQuery(insertQuery);
                preparedStatement =  conn.prepareStatement(insertQuery);

                // 5. Set values for the placeholders in the prepared statement

                preparedStatement.setInt(1, slot.getStartTime());
                preparedStatement.setInt(2, slot.getSeatCount());
                preparedStatement.setInt(3, gymId);


                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Record inserted successfully!");
                } else {
                    throw new SlotInsertionFailedException();

//                    System.out.println("Failed to insert the record.");
//                    return ;
                }

            }catch(SlotInsertionFailedException | SQLException ex){
                System.out.println(ex.getMessage());
            }
//
        }


    }

    @Override
    public List<FlipFitGym> viewGymSlots(String gymOwnerID) {
        conn = DatabaseConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<FlipFitGym> flipFitGyms = new ArrayList<>();

        try {
            String sqlQuery = "SELECT * FROM gyms WHERE ownerId=?";
            preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, gymOwnerID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("gymId");
                String gymAddress = resultSet.getString("gymAddress");
                String location = resultSet.getString("location");
                String gymName = resultSet.getString("gymName");
                String status = resultSet.getString("status");
                FlipFitGym flipFitGym = new FlipFitGym();
                flipFitGym.setGymName(gymName);
                flipFitGym.setGymAddress(gymAddress);
                flipFitGym.setOwnerId(gymOwnerID);
                flipFitGym.setLocation(location);
                flipFitGym.setStatus(status);
                flipFitGyms.add(flipFitGym);

                List<FlipFitSlots> flipFitSlots = getGymSlotsWithGymId(id);
                flipFitGym.setSlots(flipFitSlots);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return flipFitGyms;
    }

    public List<FlipFitSlots> getGymSlotsWithGymId(int id){
        conn = DatabaseConnector.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List<FlipFitSlots> slotList = new ArrayList<>();
        try {
            String sqlQuery = "SELECT * FROM slots WHERE gymId= " + id;
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
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