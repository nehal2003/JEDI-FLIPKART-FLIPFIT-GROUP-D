package com.flipkart.bean;

/**
 * Represents a user in the FlipFit system.
 */
public class FlipFitUser {
    private int userId; // Unique identifier for the user

    private String userName; // User's name
    private String phoneNumber; // User's phone number
    private String address; // User's address
    private String location; // User's location
    private String email; // User's email address
    private String password; // User's password

    /**
     * Retrieves the unique identifier for the user.
     * @return The unique identifier for the user
     */
    public int getuserId() {
        return userId;
    }

    /**
     * Sets the unique identifier for the user.
     * @param userId The unique identifier to set
     */
    public void setuserId(int userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the user's name.
     * @return The user's name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user's name.
     * @param userName The user's name to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Retrieves the user's email address.
     * @return The user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     * @param email The user's email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the user's phone number.
     * @return The user's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the user's phone number.
     * @param phoneNumber The user's phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Retrieves the user's address.
     * @return The user's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the user's address.
     * @param address The user's address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the user's location.
     * @return The user's location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the user's location.
     * @param location The user's location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Retrieves the user's password.
     * @return The user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     * @param password The user's password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
