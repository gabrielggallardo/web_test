package main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is the main class for the application
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        stage.setTitle("Appointments CRM");
        stage.setScene(new Scene(root, 300, 250));
        stage.show();
    }
    /**
     * This method is the main method for the application
     * @param args
     */
    public static void main(String[] args){
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();


    }
    /*
    * so useful Wrapper Methods
    * valueOf() converts the argument into the data type of the thing that precedes it. I.e -> String.valueOf(customer) converts customer to String
    * other useful methods
    * parseInt() --- parseDouble() can covert a String into int or double primitive types
    * add() is an overloaded method that adds items to the end of an observableArrayList
    * clear() clears all items
    * set() replaces a specific item in an observableArrayList
    * remove() is an overloaded method that removes a specific item from an observableArrayList and returns the item removed or a boolean
    * get() returns an item from an observableArrayList
    *
    *  */
}
