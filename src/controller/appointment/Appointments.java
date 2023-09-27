package controller.appointment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;

public class Appointments {

    @FXML
    private RadioButton allAppointmentRadioButton;
    @FXML
    private TableColumn<?, ?> apointmentIDColumn;
    @FXML
    private TableColumn<?, ?> appointmentCustomerIDColumn;
    @FXML
    private ToggleGroup appointmentPeriodTG;
    @FXML
    private TableColumn<?, ?> descriptionColumn;
    @FXML
    private TableColumn<?, ?> endColumn;
    @FXML
    private TableColumn<?, ?> locationColumn;
    @FXML
    private RadioButton monthAppointmentRadioButton;
    @FXML
    private TableColumn<?, ?> startColumn;
    @FXML
    private TableColumn<?, ?> titleColumn;
    @FXML
    private TableColumn<?, ?> typeColumn;
    @FXML
    private RadioButton weekAppointmentRadioButton;


    @FXML
    void onActionAddAppointmentsButton(ActionEvent event) {

    }

    @FXML
    void onActionCustomersButton(ActionEvent event) {

    }

    @FXML
    void onActionDeleteAppointmentsButton(ActionEvent event) {

    }

    @FXML
    void onActionModifyAppointmentsButton(ActionEvent event) {

    }

    @FXML
    void onActionReportsButton(ActionEvent event) {

    }
}
