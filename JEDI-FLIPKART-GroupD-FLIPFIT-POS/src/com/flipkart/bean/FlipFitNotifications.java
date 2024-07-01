

package com.flipkart.bean;

/**
 * Represents a notification in the FlipFit system.
 */
public class FlipFitNotifications {

    private String category; // Category of the notification
    private String message; // Message content of the notification
    private int notificationId; // Unique identifier for the notification

    public int getNotificationId() {
        return notificationId; // Retrieves the unique identifier for the notification
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId; // Sets the unique identifier for the notification
    }

    public String getCategory() {
        return category; // Retrieves the category of the notification
    }

    public void setCategory(String category) {
        this.category = category; // Sets the category of the notification
    }

    public String getMessage() {
        return message; // Retrieves the message content of the notification
    }

    public void setMessage(String message) {
        this.message = message; // Sets the message content of the notification
    }
}

