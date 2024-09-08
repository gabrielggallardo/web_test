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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Contacts;
import model.Customer;
import model.User;

/**
 * This class is the controller for the AppointmentTable.fxml view
 */


public class AppointmentTableController {
    Stage stage;
    Parent scene;



    @FXML
    private TableColumn<Appointment, Integer> appointmentCustomerIDColumn1;

    @FXML
    private TableColumn<Appointment, Integer> appointmentCustomerIDColumn11;


    @FXML
    private TableView<Appointment> allAppointmentsTable;

    @FXML
    private TableColumn<Appointment, Integer> appointmentCustomerIDColumn;

    @FXML
    private TableColumn<Appointment, Integer> appointmentIDColumn;

    @FXML
    private RadioButton AllRadioBtn;

    @FXML
    private RadioButton MonthRadioBtn;

    @FXML
    private RadioButton WeekRadioBtn;

    @FXML
    private Button dateRangeBackBtn;

    @FXML
    private Button dateRangeForwardBtn;

    @FXML
    private Label dateRangeLabel;

    @FXML
    private TableColumn<Appointment, String> descriptionColumn;

    @FXML
    private TableColumn<Appointment, Timestamp> endColumn;

    @FXML
    private TableColumn<Appointment, String> locationColumn;

    @FXML
    private TableColumn<Appointment, Timestamp> startColumn;

    @FXML
    private TableColumn<Appointment, String> titleColumn;

    @FXML
    private TableColumn<Appointment, String> typeColumn;

    private ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

    // selected date range
    private int selectedMonth;
    private int selectedWeek;
    private int selectedYear;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
    private LocalDate selectedStart;
    private LocalDate selectedEnd;

    /**
     * This method initializes the AppointmentTableController
     * @throws SQLException
     */

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
        appointmentCustomerIDColumn1.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("contactID"));
        appointmentCustomerIDColumn11.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userID"));

        allAppointmentsTable.setItems(appointmentList);

        // set the default radio button to be selected
        AllRadioBtn.setSelected(true);

        // set the starting date range to be the current month and year
        selectedMonth = LocalDateTime.now().getMonthValue();
        selectedYear = LocalDateTime.now().getYear();

        // disable date range buttons
        dateRangeBackBtn.setDisable(true);
        dateRangeForwardBtn.setDisable(true);

    }
    /**
     * This method is used to change the scene to the AddAppointment.fxml view
     * @throws IOException
     */

    void updateDateRangeLabel() {

        if(MonthRadioBtn.isSelected()) {
            // determine the start and end dates of the selected month
            selectedStart = LocalDate.of(selectedYear, selectedMonth, 1);
            selectedEnd = LocalDate.of(selectedYear, selectedMonth, selectedStart.lengthOfMonth());

            // update the l
            dateRangeLabel.setText(selectedStart.format(formatter) + " - " + selectedEnd.format(formatter));
        }
        else if(WeekRadioBtn.isSelected()) {
            // determine the start and end dates of the selected week
            selectedStart = LocalDate.of(selectedYear, 1, 1).plusWeeks(selectedWeek);
            selectedEnd = selectedStart.plusDays(6);

            // update the l
            dateRangeLabel.setText(selectedStart.format(formatter) + " - " + selectedEnd.format(formatter));
        }
        else if(AllRadioBtn.isSelected()) {
            // set the selected date range to be null
            dateRangeLabel.setText(null);

            selectedStart = null;
            selectedEnd = null;
        }



    }
    /**
     * This method is used to change the scene to the AddAppointment.fxml view
     * @throws IOException
     */

    void updateAppointmentsData() {
        // check if there is a start or end date
        if(selectedStart != null && selectedEnd != null) {
            // fetch the appointments
            try {
                appointmentList = AppointmentManager.getAppointmentsInDateRange(selectedStart, selectedEnd);
            } catch (SQLException e){
                System.out.println("Error occurred when getting AppointmentList");
            }

        }
        else {
            // fetch all the appointments
            try {
                appointmentList = AppointmentManager.getAppointmentList();
            } catch (SQLException e){
                System.out.println("Error occurred when getting AppointmentList");
            }

        }
        allAppointmentsTable.setItems(appointmentList);
    }
    /**
     * This method is used to change the scene to the AddAppointment.fxml view
     * @throws IOException
     */

    @FXML
    void onBackDateRangeBtnClicked(ActionEvent event) {
        // figure out which radio button is selected
        if (AllRadioBtn.isSelected()){
            updateDateRangeLabel();

            updateAppointmentsData();

        } else if (MonthRadioBtn.isSelected()){
            // update the selected month
            if (selectedMonth == 1){
                selectedMonth = 12;
                selectedYear--;
            } else {
                selectedMonth--;
            }

            updateDateRangeLabel();

            updateAppointmentsData();

        } else if (WeekRadioBtn.isSelected()){
            // update the selected week
            if (selectedWeek == 1){
                selectedWeek = 52;
                selectedYear--;
            } else {
                selectedWeek--;
            }

            // update the month based on the week
            LocalDate selectedDate = LocalDate.of(selectedYear, 1, 1).plusWeeks(selectedWeek);
            selectedMonth = selectedDate.getMonthValue();

            updateDateRangeLabel();

            updateAppointmentsData();

        }
    }
    /**
     * This method is used to change the scene to the AddAppointment.fxml view
     * @throws IOException
     */

    @FXML
    void onNextDateRangeBtnClicked(ActionEvent event) {
        // figure out which radio button is selected
        if (AllRadioBtn.isSelected()){
            updateDateRangeLabel();

            updateAppointmentsData();

        } else if (MonthRadioBtn.isSelected()){
            // update the selected month
            if (selectedMonth == 12){
                selectedMonth = 1;
                selectedYear++;
            } else {
                selectedMonth++;
            }

            updateDateRangeLabel();

            updateAppointmentsData();

        } else if (WeekRadioBtn.isSelected()){
            // update the selected week
            if (selectedWeek == 52){
                selectedWeek = 1;
                selectedYear++;
            } else {
                selectedWeek++;
            }

            // update the month based on the week
            LocalDate selectedDate = LocalDate.of(selectedYear, 1, 1).plusWeeks(selectedWeek);
            selectedMonth = selectedDate.getMonthValue();

            updateDateRangeLabel();

            updateAppointmentsData();

        }
    }

    /**
     * This method is used to change the scene to the AddAppointment.fxml view
     * @throws IOException
     */
    @FXML
    void onAllRadioBtnSelected(ActionEvent event) {
        System.out.println("All Radio Button Selected");
        // deselect other radio buttons
        MonthRadioBtn.setSelected(false);
        WeekRadioBtn.setSelected(false);

        // set the selected date range to be null
        selectedStart = null;
        selectedEnd = null;

        // disable date range buttons
        dateRangeBackBtn.setDisable(true);
        dateRangeForwardBtn.setDisable(true);

        updateDateRangeLabel();
        updateAppointmentsData();
    }
    /**
     * This method is used to change the scene to the AddAppointment.fxml view
     * @throws IOException
     */

    @FXML
    void onMonthRadioBtnSelected(ActionEvent event) {
        System.out.println("Month Radio Button Selected");

        // deselect other radio buttons
        AllRadioBtn.setSelected(false);
        WeekRadioBtn.setSelected(false);

        // enable date range buttons
        dateRangeBackBtn.setDisable(false);
        dateRangeForwardBtn.setDisable(false);

        // determine the start and end dates of the selected month
        selectedStart = LocalDate.of(selectedYear, selectedMonth, 1);
        selectedEnd = LocalDate.of(selectedYear, selectedMonth, selectedStart.lengthOfMonth());

        updateDateRangeLabel();
        updateAppointmentsData();

    }
    /**
     * This method is used to change the scene to the AddAppointment.fxml view
     * @throws IOException
     */

    @FXML
    void onWeekRadioBtnSelected(ActionEvent event) {
        System.out.println("Week Radio Button Selected");

        // deselect other radio buttons
        AllRadioBtn.setSelected(false);
        MonthRadioBtn.setSelected(false);

        // enable date range buttons
        dateRangeBackBtn.setDisable(false);
        dateRangeForwardBtn.setDisable(false);

        // determine the start and end dates of the selected week
        selectedStart = LocalDate.of(selectedYear, 1, 1).plusWeeks(selectedWeek);
        selectedEnd = selectedStart.plusDays(6);

        updateDateRangeLabel();
        updateAppointmentsData();


    }


    /**
     * This method is used to change the scene to the AddAppointment.fxml view
     * @throws IOException
     */
    @FXML
    void onActionAddAppointmentsButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**
     * This method is used to change the scene to the AddAppointment.fxml view
     * @throws IOException
     */

    @FXML
    void onActionCustomersButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("Switching to the Customer.fxml view");

    }

    /**
     * This method warns before deleting an appointment and then deletes the appointment
     * @param event
     * @throws IOException
     */

    @FXML
    void onActionDeleteAppointmentsButton(ActionEvent event) {
        // get the selected appointment
        Appointment selectedAppointment = allAppointmentsTable.getSelectionModel().getSelectedItem();

        // show an alert to confirm the deletion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Appointment");
        alert.setHeaderText("Are you sure you want to delete the appointment with ID: " + selectedAppointment.getId() + "?");
        alert.setContentText("Press OK to confirm, or Cancel to go back.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // delete the appointment
                try {
                    AppointmentManager.deleteAppointment(selectedAppointment.getId());

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText("The appointment was successfully deleted.");
                    successAlert.setContentText("The appointment with ID: " + selectedAppointment.getId() + " was deleted.");
                    successAlert.showAndWait();

                    updateAppointmentsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText("There was an error deleting the appointment.");
                    errorAlert.setContentText("Please try again.");
                    errorAlert.showAndWait();
                }
            }
        });

    }

    /**
     * This method is used to change the scene to the UpdateAppointment.fxml view
     * @param event
     */

    @FXML
    void onActionModifyAppointmentsButton(ActionEvent event) {
        // take the user to the Update Appointment screen
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/updateAppointments.fxml"));
            loader.load();

            UpdateAppointmentsController updateAppointmentsController = loader.getController();
            updateAppointmentsController.sendAppointment(allAppointmentsTable.getSelectionModel().getSelectedItem());

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to change the scene to the AddAppointment.fxml view
     * @throws IOException
     */
    @FXML
    void onActionReportsButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsFirstScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("Switching to the reportsFirstScreen.fxml view");

    }
    /**
     * This method is used to change the scene to the AddAppointment.fxml view
     * @throws IOException
     */

    public void onActionLogoutButton(ActionEvent event) {
        //This is the code to shutdown the application
        System.out.println("Shutting down! Goodbye.");
        System.exit(0);
    }

}
