package com.flipkart.dao;

public interface UpdatePasswordDAOInterface {

    /**
     * Update the password of a gym owner.
     * @param email Email of the gym owner
     * @param password Current password of the gym owner
     * @param updatedPassword New password to set
     */
    void updateGymOwnerPassword(String email, String password, String updatedPassword);

    /**
     * Update the password of a gym user.
     * @param email Email of the gym user
     * @param password Current password of the gym user
     * @param updatedPassword New password to set
     */
    void updateGymUserPassword(String email, String password, String updatedPassword);

    /**
     * Verify if the provided password matches the gym owner's password.
     * @param email Email of the gym owner
     * @param password Password to verify
     * @return true if the password matches, false otherwise
     */
    boolean verifyGymOwnerPassword(String email, String password);

    /**
     * Verify if the provided password matches the gym user's password.
     * @param email Email of the gym user
     * @param password Password to verify
     * @return true if the password matches, false otherwise
     */
    boolean verifyGymUserPassword(String email, String password);
}
