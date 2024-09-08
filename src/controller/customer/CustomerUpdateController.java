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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
/**
 * This class is the controller for the CustomerUpdate.fxml view
 */

public class CustomerUpdateController {
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
        //will need to add the logic to save the strings
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    private ObservableList<Customer> customerOptions = FXCollections.observableArrayList();

    /**
     * This method initializes the CustomerUpdate.fxml view
     * @throws SQLException
     */
    public void initialize() throws SQLException {
        ObservableList<Country> customerCountries = CountryManager.getCountryList();
        ObservableList<Division> customerDivisions = DivisionManager.getDivisionList();

        customerCountryComboBox.setItems(customerCountries);
        customerDivisionComboBox.setItems(customerDivisions);
        //test to see if commit works

    }

    /**
     * This method sends the customer object to the CustomerUpdate.fxml view
     * @param customer
     */

    public void sendCustomer(Customer customer) {
        customerIDTxt.setText(String.valueOf(customer.getId()));
        customerNameTxt.setText(customer.getName());
        customerAddressTxt.setText(customer.getAddress());
        customerPostalTxt.setText(customer.getPostalCode());
        customerPhoneNumberTxt.setText(customer.getPhone());
        customerCountryComboBox.setValue(customer.getDivision().getCountry());
        customerDivisionComboBox.setValue(customer.getDivision());
    }

}
