package controller.reports;

import Managers.AppointmentManager;
import Managers.ContactManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import model.Appointment;
import model.AppointmentMonthStat;
import model.Contacts;

/**
 * This class is the controller for the ReportsInfoDisplay.fxml view
 */
public class ReportsInfoDisplayController {
    Stage stage;
    Parent scene;

    @FXML
    private ChoiceBox<String> appointmentTypeChoiceBox;

    @FXML
    private TableView<Appointment> contactScheduleTable;

    @FXML
    private TableColumn<Appointment, Integer> csAppointmentIDCol;

    @FXML
    private ChoiceBox<Contacts> contactSelection;

    @FXML
    private TableColumn<Appointment, Integer> csCustomerIDCol;

    @FXML
    private TableColumn<Appointment, String> csDescriptionCol;

    @FXML
    private TableColumn<Appointment, Timestamp> csEndTimeCol;

    @FXML
    private TableColumn<Appointment, Timestamp> csStartTimeCol;

    @FXML
    private TableColumn<Appointment, String> csTitleCol;

    @FXML
    private TableColumn<Appointment, String> csTypeCol;

    @FXML
    private TableColumn<AppointmentMonthStat, String> tcMonthCol;

    @FXML
    private TableColumn<AppointmentMonthStat, Integer> tcTotalCol;

    @FXML
    private TableColumn<AppointmentMonthStat, String> tcTypeCol;

    @FXML
    private TableView<AppointmentMonthStat> totalAppointmentsByMonth;

    @FXML
    void onContactChanged(ActionEvent event) {
        System.out.println("Contact changed");
    }
    /**
     * This method switches the scene to the reportsFirstScreen.fxml view
     * @param event
     * @throws IOException
     */

    @FXML
    void onActionBackButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsFirstScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**
     * This method updates the contact schedule table
     * @throws SQLException
     */

    private Contacts selectedContact;
    private String selectedType;

    /**
     * This method initializes the ReportsInfoDisplayController
     * @throws SQLException
     */

    public void initialize() throws SQLException {

        // Contact Schedule report setup
        ObservableList<Contacts> contactOptions = ContactManager.getContactList();

        contactSelection.setItems(contactOptions);
        contactSelection.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                // set the selected contact
                selectedContact = contactOptions.get((Integer) number2);
                System.out.println("Selected contact: " + selectedContact.getContactName());

                try {
                    updateContactSchedule();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        csAppointmentIDCol.setCellValueFactory(new PropertyValueFactory<Appointment,Integer>("id"));
        csTitleCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        csDescriptionCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        csTypeCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        csStartTimeCol.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("startTime"));
        csEndTimeCol.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("endTime"));
        csCustomerIDCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerID"));

        updateContactSchedule();

        // Total Appointments by Type by Month report setup
        tcMonthCol.setCellValueFactory(new PropertyValueFactory<AppointmentMonthStat, String>("month"));
        tcTotalCol.setCellValueFactory(new PropertyValueFactory<AppointmentMonthStat, Integer>("total"));
        tcTypeCol.setCellValueFactory(new PropertyValueFactory<AppointmentMonthStat, String>("type"));

        // set the appointment type options
        ObservableList<String> typeOptions = AppointmentManager.getAllAppointmentTypes();
        appointmentTypeChoiceBox.setItems(typeOptions);

        appointmentTypeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                // set the selected appointment type
                selectedType = typeOptions.get((Integer) number2);

                try {
                    generateAppointmentMonthStats();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        generateAppointmentMonthStats();
    }
    /**
     * This method updates the contact schedule table
     * @throws SQLException
     */

    private void updateContactSchedule() throws SQLException {

        if(selectedContact == null) {
            return;
        }

        ObservableList<Appointment> appointments = AppointmentManager.getAllAppointmentsForContact(selectedContact);

        contactScheduleTable.setItems(appointments);
    }
    /**
     * This method generates the appointment month stats
     * @throws SQLException
     */

    private void generateAppointmentMonthStats() throws SQLException {
        // get all the appointments
        ObservableList<Appointment> appointments = AppointmentManager.getAppointmentList();


        // dictionary of all the months
        // key: month-type
        // value: count
        Map<String, Integer> months = new HashMap<>() {{
            // add all the months to the dictionary

        }};

        // loop through all of the appointments
        for(Appointment a : appointments) {
            // check if it is the selected type
            if(selectedType != null && !selectedType.equals("All") && !a.getType().equals(selectedType)) {
                continue;
            }
            // get the month of the appointment
            String monthAndType = a.getStartTime().getMonth().toString().toLowerCase() + "-" + a.getType().toLowerCase();
            // check if the month-type is in the dictionary
            if(!months.containsKey(monthAndType)) {
                // if not, add it with a count of 0
                months.put(monthAndType, 0);
            }

            // get the current count of the month
            int count = months.get(monthAndType);
            // increment the count
            count++;
            // update the count in the dictionary
            months.put(monthAndType, count);
        }

        // create list of stats
        ObservableList<AppointmentMonthStat> stats = FXCollections.observableArrayList();

        // loop through the months dictionary
        for(Map.Entry<String, Integer> entry : months.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            // split the key into month and type
            String[] parts = entry.getKey().split("-");
            String month = parts[0];
            if(parts.length < 2) {
                continue;
            }
            String type = parts[1];
            // create a stats object
            AppointmentMonthStat stat = new AppointmentMonthStat(month, entry.getValue(), type);

            // add the stat to the list
            stats.add(stat);

        }
        // update the table
        totalAppointmentsByMonth.setItems(stats);
    }
}
