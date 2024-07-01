
package com.flipkart.model;

/**
 * Represents a time slot in FlipFit system.
 */
public class FlipFitSlots {

    private int slotsId; // Unique identifier for the slot
    private int startTime; // Start time of the slot
    private int seatCount; // Number of available seats in the slot

//    /**
//     * Constructor to initialize FlipFitSlots object with given values.
//     * @param slotsId Unique identifier for the slot
//     * @param startTime Start time of the slot
//     * @param seatCount Number of available seats in the slot
//     */
    public FlipFitSlots() {};
    public FlipFitSlots(int slotsId, int startTime, int seatCount) {
        this.setSlotsId(slotsId);
        this.setStartTime(startTime);
        this.setSeatCount(seatCount);
    }

    /**
     * Retrieves the unique identifier for the slot.
     * @return The unique identifier for the slot
     */
    public int getSlotsId() {
        return slotsId;
    }

    /**
     * Sets the unique identifier for the slot.
     * @param slotsId The unique identifier to set
     */
    public void setSlotsId(int slotsId) {
        this.slotsId = slotsId;
    }

    /**
     * Retrieves the start time of the slot.
     * @return The start time of the slot
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the slot.
     * @param startTime The start time to set
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    /**
     * Retrieves the number of available seats in the slot.
     * @return The number of available seats
     */
    public int getSeatCount() {
        return seatCount;
    }

    /**
     * Sets the number of available seats in the slot.
     * @param seatCount The number of seats to set
     */
    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }
}

