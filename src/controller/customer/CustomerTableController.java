package controller.customer;

import Managers.CustomerManager;
import Managers.AppointmentManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Country;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * This class is the controller for the CustomerTable.fxml view
 */

public class CustomerTableController {
    Stage stage;
    Parent scene;
    @FXML
    private TableView<Customer> allCustomersTable;

    @FXML
    private TableColumn<Customer, String> addressColumn;

    @FXML
    private TableColumn<Country, String> countryColumn;

    @FXML
    private TableColumn<Customer, Integer> customerCustomerIDColumn;

    @FXML
    private TableColumn<Division, String> firstlevelDivColumn;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> postalColumn;


    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    /**
     * This method initializes the CustomerTableController
     * @throws SQLException
     */
    public void initialize() throws SQLException{
        try {
            customerList = CustomerManager.getCustomerList();
        } catch (SQLException e){
            System.out.println("Error occurred when getting CustomerList");
        }
        customerCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        postalColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Country, String>("country"));
        firstlevelDivColumn.setCellValueFactory(new PropertyValueFactory<Division, String>("division"));


        allCustomersTable.setItems(customerList);

    }
    /**
     * This method switches the scene to the customerUpdate.fxml view
     * @param event
     * @throws IOException
     */

    @FXML
    void onActionAddCustomersButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customerAdd.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }
    /**
     * This method switches the scene to the appointments.fxml view
     * @param event
     * @throws IOException
     */

    @FXML
    void onActionAppointmentsButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("Switching back to Appointments View");
    }
    /**
     * This method switches the scene to the customerUpdate.fxml view
     * @param event
     * @throws IOException
     */


    @FXML
    void onActionDeleteCustomersButton(ActionEvent event) {
        // show a message box to confirm the deletion
        // if the user clicks yes, delete the customer
        // if the user clicks no, do nothing

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Customer");
        alert.setHeaderText("Are you sure you want to delete this customer?");
        alert.setContentText("Click OK to delete the customer, or Cancel to return to the Customer Table");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                deleteCustomer();
            }
        });
    }

    private void deleteCustomer() {
        boolean didDeleteAppointments = false;
        // check if there are any appointments for the customer
        int appointmentCount = 0;
        try {
            appointmentCount = AppointmentManager.getAllAppointmentsForCustomer(allCustomersTable.getSelectionModel().getSelectedItem().getId()).size();
            didDeleteAppointments = true;
        } catch (SQLException e){
            System.out.println("Error occurred when getting appointments for customer");
        }

        if(appointmentCount > 0){
            // if there are appointments, delete them first
            try {
                AppointmentManager.deleteAllAppointmentsForCustomer(allCustomersTable.getSelectionModel().getSelectedItem().getId());

            } catch (SQLException e){
                System.out.println("Error occurred when deleting customer's appointments");
            }
        }

        // finally, delete the customer
        try {
            CustomerManager.deleteCustomer(allCustomersTable.getSelectionModel().getSelectedItem().getId());
            customerList = CustomerManager.getCustomerList();
            allCustomersTable.setItems(customerList);
        } catch (SQLException e){
            System.out.println("Error occurred when deleting customer");
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Customer Deleted");
        alert.setHeaderText("Customer has been deleted");
        if(didDeleteAppointments){
            alert.setContentText("Customer and associated appointments have been deleted");
        } else {
            alert.setContentText("Customer has been deleted");
        }
        alert.showAndWait();

    }
    /**
     * This method switches the scene to the login.fxml view
     * @param event
     */

    @FXML
    void onActionLogoutButton(ActionEvent event) {
        System.out.println("Shutting down from Customers");
        System.exit(0);

    }
    /**
     * This method switches the scene to the customerUpdate.fxml view
     * @param event
     * @throws IOException
     */

    @FXML
    void onActionModifyCustomersButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/customerUpdate.fxml"));
            loader.load();

            CustomerUpdateController CUC = loader.getController();
            CUC.sendCustomer(allCustomersTable.getSelectionModel().getSelectedItem());

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e){
            System.out.println("Error occurred when switching to customerUpdate.fxml");
        }

    }
    /**
     * This method switches the scene to the reportsFirstScreen.fxml view
     * @param event
     * @throws IOException
     */

    @FXML
    void onActionReportsButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsFirstScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

}
