package controller.customer;

import Managers.CustomerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerUpdateController {
    Stage stage;
    Parent scene;

    @FXML
    private TextField customerAddressTxt;

    @FXML
    private ComboBox<String> customerCountryComboBox; //fix the line ups

    @FXML
    private TextField customerIDTxt;

    @FXML
    private ComboBox<String> customerDivisionComboBox;

    @FXML
    private TextField customerNameTxt;

    @FXML
    private TextField customerPhoneNumberTxt;

    @FXML
    private TextField customerPostalTxt;

    @FXML
    void onActionBackBtn(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("Switching to the Customer.fxml view");

    }

    @FXML
    void onActionSaveCustomerUpdates(ActionEvent event) throws IOException {
        //will need to add the logic to save the strings
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    private ObservableList<Customer> customerOptions = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
    ObservableList<Customer> customerObservableList = CustomerManager.getCustomerList();
    ObservableList<String> allCustomerNames = FXCollections.observableArrayList();

    customerObservableList.forEach(customer -> allCustomerNames.add(customer.getName()));
    ObservableList<String> customerCountries = FXCollections.observableArrayList();
    ObservableList<String> customerDivisions = FXCollections.observableArrayList();

    customerCountryComboBox.setItems(customerCountries);
    customerDivisionComboBox.setItems(customerDivisions);
    customerIDTxt.setId(String.valueOf(customerObservableList));
    customerNameTxt.setText(customerObservableList.get(0).getName());
    //test to see if commit works




    }









}
