package controller.reports;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

    /**
 * This class is the controller for the ReportsFirstScreen.fxml view
 */
public class ReportsFirstScreenController {
    Stage stage;
    Parent scene;

    /**
     * This method changes the scene to the CMS Report view
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionCMSReportButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsInfoDisplay.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("Let's load up the CMS Report");
    }
    /**
     * This method changes the scene to the Traffic Week Report view
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionExitReportButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("Back to the Appointments view we go!");
    }
    /**
     * This method changes the scene to the Traffic Week Report view
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionTrafficWeekReportButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsGraph.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}
