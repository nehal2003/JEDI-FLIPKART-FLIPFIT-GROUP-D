
package com.flipkart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FlipFitGymOwner {
    private int ownerId;
    private String ownerEmail;
    private String password;
    private String phoneNo;
    private String nationalId;
    @JsonProperty("gst")
    private String GST;
    private List<FlipFitGym> flipFitGyms;
    @JsonProperty("pan")
    private String PAN;
    private String ownerName;
    private String status;
    private String verificationStatus;

    public int getOwnerId() {
        return ownerId; // Retrieves the unique identifier for the gym owner
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId; // Sets the unique identifier for the gym owner
    }

    public String getOwnerEmail() {
        return ownerEmail; // Retrieves the email address of the gym owner
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail; // Sets the email address of the gym owner
    }

    public String getPassword() {
        return password; // Retrieves the password for the gym owner's account
    }

    public void setPassword(String password) {
        this.password = password; // Sets the password for the gym owner's account
    }

    public String getPhoneNo() {
        return phoneNo; // Retrieves the phone number of the gym owner
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo; // Sets the phone number of the gym owner
    }

    public String getNationalId() {
        return nationalId; // Retrieves the national ID of the gym owner
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId; // Sets the national ID of the gym owner
    }

    public String getGST() {
        return GST; // Retrieves the GST number of the gym owner
    }

    public void setGST(String GST) {
        this.GST = GST; // Sets the GST number of the gym owner
    }

    public List<FlipFitGym> getGyms() {
        return flipFitGyms; // Retrieves the list of gyms owned by the gym owner
    }

    public void setGyms(List<FlipFitGym> flipFitGyms) {
        this.flipFitGyms = flipFitGyms; // Sets the list of gyms owned by the gym owner
    }

    public String getPAN() {
        return PAN; // Retrieves the PAN of the gym owner
    }

    public void setPAN(String PAN) {
        this.PAN = PAN; // Sets the PAN of the gym owner
    }

    public String getOwnerName() {
        return ownerName; // Retrieves the name of the gym owner
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName; // Sets the name of the gym owner
    }

    public String getStatus() {
        return status; // Retrieves the current status of the gym owner
    }

    public void setStatus(String status) {
        this.status = status; // Sets the current status of the gym owner
    }

    public String getVerificationStatus() {
        return verificationStatus; // Retrieves the verification status of the gym owner
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus; // Sets the verification status of the gym owner
    }
}
