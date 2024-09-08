package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int customerID;
    private int userID;
    private int contactID;

    /**
     * This is the constructor for the Appointment class
     * @param id
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startTime
     * @param endTime
     * @param customerID
     * @param userID
     * @param contactID
     */
    public Appointment(int id, String title, String description, String location,
                       String type, LocalDateTime startTime, LocalDateTime endTime, int customerID,
                       int userID, int contactID){
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }
    //getters and setters for Appointments

    /** this are the getters and setters for the Appointment class
     * @return
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }


    /**
     * This is a method that returns a list of appointment types
     * @return
     */
    // method that returns a list of time slots for appointments
    // options are from (7:00 AM to 11:00 PM)
    public static ObservableList<String> getAppointmentTimeSlots(){
        ObservableList<String> timeSlots = FXCollections.observableArrayList();
        timeSlots.add("07:00");
        timeSlots.add("07:30");
        timeSlots.add("08:00");
        timeSlots.add("08:30");
        timeSlots.add("09:00");
        timeSlots.add("09:30");
        timeSlots.add("10:00");
        timeSlots.add("10:30");
        timeSlots.add("11:00");
        timeSlots.add("11:30");
        timeSlots.add("12:00");
        timeSlots.add("12:30");
        timeSlots.add("13:00");
        timeSlots.add("13:30");
        timeSlots.add("14:00");
        timeSlots.add("14:30");
        timeSlots.add("15:00");
        timeSlots.add("15:30");
        timeSlots.add("16:00");
        timeSlots.add("16:30");
        timeSlots.add("17:00");
        timeSlots.add("17:30");
        timeSlots.add("18:00");
        timeSlots.add("18:30");
        timeSlots.add("19:00");
        timeSlots.add("19:30");
        timeSlots.add("20:00");
        timeSlots.add("20:30");
        timeSlots.add("21:00");
        timeSlots.add("21:30");
        timeSlots.add("22:00");
        timeSlots.add("22:30");
        timeSlots.add("23:00");
        return timeSlots;
    }
}
