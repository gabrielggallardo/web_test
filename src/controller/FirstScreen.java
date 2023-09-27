package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstScreen implements Initializable {

    Stage stage;
    Parent scene;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am working! Woo hoo!");

    }
    @FXML
    private TextField usernameTxt;
    @FXML
    private TextField passwordTxt;

    @FXML
    private Label location_label;
    //this will need to change time zones based off of computers time zone to be changed down the road

    @FXML
    private Button login_button; //most likely not needed...




    public void login_button_pressed(ActionEvent event) throws IOException {
        System.out.println("I am pressed");
        location_label.setText("France, bonjour!");


//
//        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
//        scene = FXMLLoader.load(getClass().getResource("/view/appointments.fxml"));
//        stage.setScene(new Scene(scene));
//        stage.show();
//

    }




}
