package controller.reports;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportsGraphController {
    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<?, ?> trNumAppointmentsColumn;

    @FXML
    private TableColumn<?, ?> trTimeIntervalColumn;

    @FXML
    void onActionBackButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsFirstScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("Switching back to the reportsFirstScreen.fxml view");


    }

    @FXML
    void trTimesBtn(ActionEvent event) {

    }
}
