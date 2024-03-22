package controller.appointment;

import Managers.ContactManager;
import Managers.CustomerManager;
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
import java.time.LocalTime;
/**
 * This class is the controller for the AddAppointments.fxml view
 */

public class AddAppointmentsController {
    Stage stage;
    Parent scene;

    @FXML
    private TextField appointmentIDTxt;

    @FXML
    private ComboBox<String> contactIDComboBox;

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

//        System.out.println(id);
//        System.out.println(title);

    }

    private ObservableList<Customer> customerOptions = FXCollections.observableArrayList();

    public void initialize() throws SQLException{

        ObservableList<Contacts> contactsObservableList = ContactManager.getContactList();
        ObservableList<String> allContactsNames = FXCollections.observableArrayList();

        contactsObservableList.forEach(contacts -> allContactsNames.add(contacts.getContactName()));
        ObservableList<String> appointmentTimes = FXCollections.observableArrayList();



        startTimeComboBox.setItems(appointmentTimes);
        endTimeComboBox.setItems(appointmentTimes);
        contactIDComboBox.setItems(allContactsNames);

        try {
            customerOptions = CustomerManager.getCustomerList();

        } catch (SQLException e){
            System.out.println("Error when getting Customers");
        }
        customerIDComboBox.setItems(customerOptions);



    }

}
