package controller;

import Managers.UserManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.User;

import java.sql.SQLException;
import java.time.ZoneId;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    Stage stage;
    Parent scene;

    String username = "";
    String password = "";

    @FXML
    private Label errorLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private TextField userNameTxt;

    @FXML
    void loginButtonPressed(ActionEvent event) throws SQLException, IOException {
        //check if the fields are valid
        if(fieldsAreValid()){
            System.out.println("fields are valid");
            errorLabel.setVisible(false);
            //if they are valid
            attemptLogin(event);
        }

        //if fields are not valid
        else{
            System.out.println("fields are not valid");
            //display error message
            errorLabel.setVisible(true);
        }




        System.out.println("login button activated!");

    }

    @FXML
    void onPasswordInputChanged(KeyEvent event) {
        password = passwordTxt.getText();
        System.out.println(password);
        System.out.println("password input changed!");

    }

    @FXML
    void onUserNameInputChanged(KeyEvent event) {
        username = userNameTxt.getText();
        System.out.println(username);

        System.out.println("Username input changed!");

    }


    boolean fieldsAreValid(){
        //check for empty username
        System.out.println(username);
        System.out.println(password);
        if(username == ""){
            return false;
        }
        //check for empty passsword
        if(password == ""){
            return false;
        }
        return true;
    }
    ResourceBundle langBundle = ResourceBundle.getBundle("lang");

    void attemptLogin(ActionEvent event) throws SQLException, IOException {
        ObservableList<User> allUsers = UserManager.getUserList();
        System.out.println(allUsers.size());
        //look through the users

        boolean didFindMatch = false;
        for(User user : allUsers){
//            System.out.println(user.getUsername());
//            System.out.println(user.getPassword());
//            System.out.println(username);
//            System.out.println(password);
            //check to see if username AND password match
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                System.out.println("username and password matched");
                //if they match go to the next screen
                didFindMatch = true;
                break;
            } else{
                System.out.println("username and password did not match");
            }
        }
        //check to see if we found a match
        if(didFindMatch){
//            System.out.println("did find a match");
            //go to the next screen

            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/customer.fxml")));
            Scene scene = new Scene(parent);
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
//            System.out.println("print finished showing scene");

        } else{
//            System.out.println("Did not find a match!");
            //show the error message
            errorLabel.setVisible(true);
        }


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //display users location in the label
        locationLabel.setText(ZoneId.systemDefault().toString());

        //setting the labels to english/french
        loginButton.setText(langBundle.getString("loginButtonText"));
        userNameTxt.setPromptText(langBundle.getString("loginUserNameText"));
        passwordTxt.setPromptText(langBundle.getString("loginPasswordText"));
        errorLabel.setText(langBundle.getString("loginErrorMessage"));






        //This is a good place according to Mark Kinkead to check for the language that the application is in since it's the beginning of the app.



    }





//    public void login_button_pressed(ActionEvent event) throws IOException {
//
//        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
//        scene = FXMLLoader.load(getClass().getResource("/view/appointments.fxml"));
//        stage.setScene(new Scene(scene));
//        stage.show();
//
//        System.out.println("Switching to Appointments View!");
//        location_label.setText("France, bonjour!");
//
//
//
//
//    }




}
