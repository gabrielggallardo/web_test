package controller;

import Managers.AppointmentManager;
import Managers.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Appointment;
import model.User;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    /**
     * This method checks if the username and password are valid
     * @param event
     * @throws SQLException
     * @throws IOException
     */

    @FXML
    void loginButtonPressed(ActionEvent event) throws SQLException, IOException {
        //check if the fields are valid
        if(fieldsAreValid()){
            System.out.println("fields are valid");
            errorLabel.setVisible(false);
            System.out.println("1");
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

    /**
     * This method logs the activity of the user
     * @param activity
     */
    @FXML
    void onPasswordInputChanged(KeyEvent event) {
        password = passwordTxt.getText();
        System.out.println(password);
        System.out.println("password input changed!");
    }

    /**
     * This method logs the activity of the user
     * @param activity
     */

    @FXML
    void onUserNameInputChanged(KeyEvent event) {
        username = userNameTxt.getText();
        System.out.println(username);

        System.out.println("Username input changed!");

    }

    /**
     * This method checks the fields for validity
     * @param activity
     */

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

    /**
     * This method looks through the users and checks if the username and password match
     * @param event
     * @throws SQLException
     * @throws IOException
     */

    void attemptLogin(ActionEvent event) throws SQLException, IOException {
        System.out.println("attempting login");
        ObservableList<User> allUsers = UserManager.getUserList();
        System.out.println(allUsers.size());
        //look through the users

        boolean didFindMatch = false;
        for(User user : allUsers){

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

            //log the activity
            logActivity("User: " + username + " logged in at: " + java.time.LocalDateTime.now() + "\n");

            // check for upcoming appointments
            checkForUpcomingAppointments();

            //go to the next screen

            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/customer.fxml")));
            Scene scene = new Scene(parent);
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();


        } else{
            // log the activity
            logActivity("User: " + username + " failed to log in at: " + java.time.LocalDateTime.now() + "\n");

            //show the error message
            errorLabel.setVisible(true);
        }


    }

    /**
     * This method initializes the login view
     * @param url
     * @param resourceBundle
     */


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //display users location in the label
        locationLabel.setText(ZoneId.systemDefault().toString());

        //setting the labels to english/french
        loginButton.setText(langBundle.getString("loginButtonText"));
        userNameTxt.setPromptText(langBundle.getString("loginUserNameText"));
        passwordTxt.setPromptText(langBundle.getString("loginPasswordText"));
        errorLabel.setText(langBundle.getString("loginErrorMessage"));


    }

    /**
     * This method logs the activity of the user to a file called login_activity.txt
     * @param entry
     */
    private void logActivity(String entry) {
        System.out.println(entry);

        try {
            // use PrintWriter
            FileWriter out = new FileWriter("login_activity.txt", true);

            // write to the file
            out.append(entry);

            // close the file
            out.close();


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    /**
     * This method checks for upcoming appointments and displays a message if there are any
     */

    private void checkForUpcomingAppointments() {
        // get all appointments
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        try {
            allAppointments = AppointmentManager.getAppointmentList();
        } catch (SQLException e) {
            System.out.println("Error occurred when getting all appointments");
        }

        // get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // check if there are any appointments within the next 15 minutes
        for (Appointment a : allAppointments) {
            if (a.getStartTime().isAfter(now) && a.getStartTime().isBefore(now.plusMinutes(15))) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Upcoming Appointment");
                alert.setHeaderText("You have an appointment within the next 15 minutes");
                alert.setContentText("Appointment ID: " + a.getId() + "\n" +
                        "Start Time: " + a.getStartTime().toString() + "\n" +
                        "End Time: " + a.getEndTime().toString() + "\n" +
                        "Customer ID: " + a.getId());
                alert.showAndWait();
            }
        }
    }

}
