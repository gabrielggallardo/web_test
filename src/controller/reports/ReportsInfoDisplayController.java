package controller.reports;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportsInfoDisplayController {
    Stage stage;
    Parent scene;
    @FXML
    private TableColumn<?, ?> csAppointmentIDCol;

    @FXML
    private MenuButton csContactMenuBtn;

    @FXML
    private TableColumn<?, ?> csCustomerIDCol;

    @FXML
    private TableColumn<?, ?> csDescriptionCol;

    @FXML
    private TableColumn<?, ?> csEndDateCol;

    @FXML
    private TableColumn<?, ?> csEndTimeCol;

    @FXML
    private TableColumn<?, ?> csStartDateCol;

    @FXML
    private TableColumn<?, ?> csStartTimeCol;

    @FXML
    private TableColumn<?, ?> csTitleCol;

    @FXML
    private TableColumn<?, ?> csTypeCol;

    @FXML
    private TableColumn<?, ?> tcMonthCol;

    @FXML
    private TableColumn<?, ?> tcTotalCol;

    @FXML
    private TableColumn<?, ?> tcTypeCol;

    @FXML
    void onActionBackButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsFirstScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}
