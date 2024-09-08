package controller.appointment;

import Managers.AppointmentManager;
import Managers.ContactManager;
import Managers.CustomerManager;
import Managers.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contacts;
import model.Customer;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is the controller for the AddAppointments.fxml view
 */

public class UpdateAppointmentsController {
    Stage stage;
    Parent scene;

    @FXML
    private TextField appointmentIDTxt;

    @FXML
    private ComboBox<Contacts> contactIDComboBox;

    @FXML
    private ComboBox<Customer> customerIDComboBox;

    @FXML
    private TextField descriptionTxt;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<String> endTimeComboBox; //

    @FXML
    private TextField locationTxt;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private ComboBox<String> startTimeComboBox;

    @FXML
    private TextField titleTxt;

    @FXML
    private TextField typeTxt;

    @FXML
    private ComboBox<User> userIDComboBox;
    /**
     * This method changes the scene to the appointments view
     * @param event
     * @throws IOException
     */

    @FXML
    void onActionBackBtn(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    /**
     * This method saves the appointment to the database
     * @param event
     * @throws IOException
     */

    @FXML
    void onActionSaveAppointmentBtn(ActionEvent event) throws IOException {

        // get the values from the text fields
        int id = Integer.parseInt(appointmentIDTxt.getText());
        String title = titleTxt.getText();
        String description = descriptionTxt.getText();
        String location = locationTxt.getText();
        String type = typeTxt.getText();
        String startDate = startDatePicker.getValue().toString();
        String startTime = startTimeComboBox.getValue();
        String endDate = endDatePicker.getValue().toString();
        String endTime = endTimeComboBox.getValue();
        int customerID = customerIDComboBox.getValue().getId();
        int userID = userIDComboBox.getValue().getId();
        int contactID = contactIDComboBox.getValue().getContactID();

        // check if the start time is before the end time
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);

        if(start.isAfter(end)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("The start time must be before the end time");
            alert.showAndWait();
            return;
        }

        // check if the appointment is within business hours
        if(start.isBefore(LocalTime.parse("08:00")) || end.isAfter(LocalTime.parse("22:00"))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("The appointment must be within business hours");
            alert.showAndWait();
            return;
        }

        // check if the appointment is overlapping
        try {
            ObservableList<Appointment> allAppointments = AppointmentManager.getAppointmentList();

            for(Appointment appointment : allAppointments){
                if(appointment.getCustomerID() == customerID && appointment.getId() != id){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    if(appointment.getStartTime().isBefore(LocalDateTime.parse(endDate + " " + endTime, formatter)) && appointment.getEndTime().isAfter(LocalDateTime.parse(startDate + " " + startTime, formatter))){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setContentText("The appointment is overlapping with another appointment");
                        alert.showAndWait();
                        return;
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // combine start/end date and start/end time into LocalDateTime
        startDate += " " + startTime;
        endDate += " " + endTime;

        // create LocalDateTime object from the string
        // Define the format pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);



        // save the appointment to the database
        try {
            AppointmentManager.updateAppointment(id, title, description, location, type, startDateTime, endDateTime, customerID, userID, contactID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("There was an error saving the appointment");
            alert.showAndWait();
            return;
        }

        // change the scene back to the appointments view
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    private ObservableList<Customer> customerOptions = FXCollections.observableArrayList();

    /**
     * This method initializes the view
     * @throws SQLException
     */
    public void initialize() throws SQLException{
        ObservableList<Contacts> allContacts = ContactManager.getContactList();
        ObservableList<User> allUsers = UserManager.getUserList();
        ObservableList<Customer> allCustomers = CustomerManager.getCustomerList();


        startTimeComboBox.setItems(Appointment.getAppointmentTimeSlots());
        endTimeComboBox.setItems(Appointment.getAppointmentTimeSlots());
        contactIDComboBox.setItems(allContacts);
        userIDComboBox.setItems(allUsers);
        customerIDComboBox.setItems(allCustomers);

    }

    /**
     * This method sends the appointment to the view
     * @param appointment
     */

    public void sendAppointment(Appointment appointment){
        appointmentIDTxt.setText(String.valueOf(appointment.getId()));
        titleTxt.setText(appointment.getTitle());
        descriptionTxt.setText(appointment.getDescription());
        locationTxt.setText(appointment.getLocation());
        typeTxt.setText(appointment.getType());
        startDatePicker.setValue(appointment.getStartTime().toLocalDate());
        startTimeComboBox.setValue(appointment.getStartTime().toLocalTime().toString());
        endDatePicker.setValue(appointment.getEndTime().toLocalDate());
        endTimeComboBox.setValue(appointment.getEndTime().toLocalTime().toString());
        customerIDComboBox.setValue(CustomerManager.getCustomer(appointment.getCustomerID()));
        userIDComboBox.setValue(UserManager.getUser(appointment.getUserID()));
        contactIDComboBox.setValue(ContactManager.getContact(appointment.getContactID()));
    }

}
