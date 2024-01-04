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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerTableController {
    Stage stage;
    Parent scene;
    @FXML
    private TableView<Customer> allCustomersTable;

    @FXML
    private TableColumn<Customer, String> addressColumn;

    @FXML
    private TableColumn<Country, Country> countryColumn; //customerCountryColumn

    @FXML
    private TableColumn<Customer, Integer> customerCustomerIDColumn;

    @FXML
    private TableColumn<Customer, Division> firstlevelDivColumn;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> postalColumn;


    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

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
        firstlevelDivColumn.setCellValueFactory(new PropertyValueFactory<>("division"));
        firstlevelDivColumn.setCellFactory(column -> new TableCell<Customer,Division>(){
            @Override
            protected void updateItem(Division division, boolean empty){
                super.updateItem(division, empty);
                if(empty){
                    setText(null);
                } else {
                    setText(division.getName());
                }
            }
        });





        
        allCustomersTable.setItems(customerList);


    }

    @FXML
    void onActionAddCustomersButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customerUpdate.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    @FXML
    void onActionAppointmentsButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("Switching back to Appointments View");



    }

    @FXML
    void onActionDeleteCustomersButton(ActionEvent event) {


    }

    @FXML
    void onActionLogoutButton(ActionEvent event) {
        System.out.println("Shutting down from Customers");
        System.exit(0);

    }

    @FXML
    void onActionModifyCustomersButton(ActionEvent event) {

    }

    @FXML
    void onActionReportsButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsFirstScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }


}
