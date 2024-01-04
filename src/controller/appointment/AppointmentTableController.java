package controller.appointment;

import Managers.AppointmentManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentTableController {
    Stage stage;
    Parent scene;


    @FXML
    private TableView<Appointment> allAppointmentsTable;
    @FXML
    private RadioButton allAppointmentRadioButton;
    @FXML
    private TableColumn<Appointment, Integer> appointmentIDColumn;
    @FXML
    private TableColumn<Appointment, Integer> appointmentCustomerIDColumn;
    @FXML
    private ToggleGroup appointmentPeriodTG;
    @FXML
    private TableColumn<Appointment, String> descriptionColumn;
    @FXML
    private TableColumn<Appointment, Timestamp> endColumn;
    @FXML
    private TableColumn<Appointment, String> locationColumn;
    @FXML
    private RadioButton monthAppointmentRadioButton;
    @FXML
    private TableColumn<Appointment, Timestamp> startColumn;
    @FXML
    private TableColumn<Appointment,String> titleColumn;
    @FXML
    private TableColumn<Appointment, String> typeColumn;
    @FXML
    private RadioButton weekAppointmentRadioButton;




    private ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();


    public void initialize() throws SQLException{
        try {
            appointmentList = AppointmentManager.getAppointmentList();
        } catch (SQLException e){
            System.out.println("Error occurred when getting AppointmentList");
        }
        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment,Integer>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("startTime"));
        endColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("endTime"));
        appointmentCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerID"));

        allAppointmentsTable.setItems(appointmentList);

    }


    @FXML
    void onActionAddAppointmentsButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        //appointmentList.add(new Appointment(7, "title", "description", "location", "type", LocalDateTime.now(), LocalDateTime.now(), 1,1,1));





    }

    @FXML
    void onActionCustomersButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("Switching to the Customer.fxml view");

    }

    @FXML
    void onActionDeleteAppointmentsButton(ActionEvent event) {

    }

    @FXML
    void onActionModifyAppointmentsButton(ActionEvent event) {

    }

    @FXML
    void onActionReportsButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsFirstScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("Switching to the reportsFirstScreen.fxml view");

    }

    public void onActionLogoutButton(ActionEvent event) {
        //This is the code to shutdown the application
        System.out.println("Shutting down! Goodbye.");
        System.exit(0);


    }

    void setAllAppointmentRadioButton(ActionEvent event) throws SQLException{

    }


}
