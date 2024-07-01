
package com.flipkart.model;

public class FlipFitBookings {
    private int bookingId;
    private int userId;
    private int createdAt;
    private int bookingStatus;
    private int date;
    private int time;
    private int slotId;
    private int gymId;
    private String status;

    public int getDate() {
        return date; // Retrieves the date of the booking.
    }

    public void setDate(int date) {
        this.date = date; // Sets the date of the booking.
    }

    public int getSlotId() {
        return slotId; // Retrieves the ID of the time slot booked.
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId; // Sets the ID of the time slot booked.
    }

    public int getGymId() {
        return gymId; // Retrieves the ID of the gym where the booking is made.
    }

    public void setGymId(int gymId) {
        this.gymId = gymId; // Sets the ID of the gym where the booking is made.
    }

    public String getStatus() {
        return status; // Retrieves the status of the booking.
    }

    public void setStatus(String status) {
        this.status = status; // Sets the status of the booking.
    }

    public int getTime() {
        return time; // Retrieves the time of the booking.
    }

    public void setTime(int time) {
        this.time = time; // Sets the time of the booking.
    }

    public int getBookingId() {
        return bookingId; // Retrieves the bookingId.
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId; // Sets the bookingId.
    }

    public int getUserId() {
        return userId; // Retrieves the userId associated with the booking.
    }

    public void setUserId(int userId) {
        this.userId = userId; // Sets the userId associated with the booking.
    }

    public int getCreatedAt() {
        return createdAt; // Retrieves the timestamp when the booking was created.
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt; // Sets the timestamp when the booking was created.
    }

    public int getBookingStatus() {
        return bookingStatus; // Retrieves the status of the booking.
    }

    public void setBookingStatus(int bookingStatus) {
        this.bookingStatus = bookingStatus; // Sets the status of the booking.
    }
}
