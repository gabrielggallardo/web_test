package controller.customer;

import Managers.CountryManager;
import Managers.CustomerManager;
import Managers.DivisionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * This class is the controller for the CustomerUpdate.fxml view
 */

public class CustomerAddController {
    Stage stage;
    Parent scene;

    @FXML
    private TextField customerAddressTxt;

    @FXML
    private ComboBox<Country> customerCountryComboBox; //fix the line ups

    @FXML
    private TextField customerIDTxt;

    @FXML
    private ComboBox<Division> customerDivisionComboBox;

    @FXML
    private TextField customerNameTxt;

    @FXML
    private TextField customerPhoneNumberTxt;

    @FXML
    private TextField customerPostalTxt;

    /**
     * This method changes the scene to the Customer.fxml view
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionBackBtn(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("Switching to the Customer.fxml view");

    }
    /**
     * This method saves the customer updates and changes the scene to the Customer.fxml view
     * @param event
     * @throws IOException
     */

    @FXML
    void onActionSaveCustomerUpdates(ActionEvent event) throws IOException {
        // validate the fields
        if(customerNameTxt.getText().isEmpty() || customerAddressTxt.getText().isEmpty() || customerPostalTxt.getText().isEmpty() || customerPhoneNumberTxt.getText().isEmpty() || customerCountryComboBox.getValue() == null || customerDivisionComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please fill out all fields");
            return;
        }

        // get the values from the text fields
        String customerName = customerNameTxt.getText();
        String customerAddress = customerAddressTxt.getText();
        String customerPostal = customerPostalTxt.getText();
        String customerPhoneNumber = customerPhoneNumberTxt.getText();
        Country customerCountry = customerCountryComboBox.getValue();
        Division customerDivision = customerDivisionComboBox.getValue();

        // create a new customer object
        Customer newCustomer = new Customer(0, customerName, customerAddress, customerPostal, customerPhoneNumber, customerDivision);

        // add the new customer to the database
        try {
            CustomerManager.addCustomer(newCustomer);
        } catch (SQLException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("There was an error adding the customer to the database");
            alert.show();
            return;

        }

        // change the scene to the Customer.fxml view
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    private ObservableList<Customer> customerOptions = FXCollections.observableArrayList();

    /**
     * This method initializes the CustomerAddController
     * @throws SQLException
     */
    public void initialize() throws SQLException {
        ObservableList<Customer> customerObservableList = CustomerManager.getCustomerList();
        // get the id of the last customer in the list
        if(customerObservableList.size() == 0) {
            customerIDTxt.setText("1");
        } else {
            int lastCustomerId = customerObservableList.get(customerObservableList.size() - 1).getId();
            // set the id of the new customer to the last customer id + 1
            customerIDTxt.setText(String.valueOf(lastCustomerId + 1));
        }

//        ObservableList<String> allCustomerNames = FXCollections.observableArrayList();
//
//        customerObservableList.forEach(customer -> allCustomerNames.add(customer.getName()));
        // get the list of countries and divisions
        ObservableList<Country> customerCountries = CountryManager.getCountryList();
        ObservableList<Division> customerDivisions = DivisionManager.getDivisionList();

        customerCountryComboBox.setItems(customerCountries);
        customerDivisionComboBox.setItems(customerDivisions);


    }

}
