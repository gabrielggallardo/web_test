package controller.reports;

import Managers.CustomerManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import model.CustomerMonthStat;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ReportsGraphController {
    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<CustomerMonthStat, String> monthColumn;

    @FXML
    private TableColumn<CustomerMonthStat, Integer> newCustomersColumn;

    @FXML
    private TableView<CustomerMonthStat> statsTable;

    /**
     * This method switches the scene to the reportsFirstScreen.fxml view
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionBackButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsFirstScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("Switching back to the reportsFirstScreen.fxml view");
    }

    /**
     * Initializes ReportGraphController
     * @throws SQLException
     * contains a lambda expression 2 description: lambda expression 2 justification for lambda expression is that it is more concise and readable
     */
    // initialize
    public void initialize() throws SQLException {
        System.out.println("ReportsGraphController initialized");

        // setup table columns
//        monthColumn.setCellValueFactory(new PropertyValueFactory<CustomerMonthStat, String>("month"));
//        newCustomersColumn.setCellValueFactory(new PropertyValueFactory<CustomerMonthStat, Integer>("total"));

        //lambda expression 2 justification for lambda expression is that it is more concise and readable
        monthColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMonth()));
        newCustomersColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTotal()).asObject());

        // generate customer stats
        generateCustomerStats();
    }

    /**
     * This method generates the customer stats
     * @throws SQLException
     * contains a lambda expression 1 description: lambda expression 1 justification for lambda expression is that it provides more clarity and conciseness in the code
     */

    // generate customer stats
    private void generateCustomerStats() throws SQLException {

        // bet all of the customers
        ObservableList<Customer> allCustomers = CustomerManager.getCustomerList();

        // dictionary of all the months
        // key: month, value: number of new customers
        Map<String, Integer> customerStats = new HashMap<>(){{
            put("JANUARY", 0);
            put("FEBRUARY", 0);
            put("MARCH", 0);
            put("APRIL", 0);
            put("MAY", 0);
            put("JUNE", 0);
            put("JULY", 0);
            put("AUGUST", 0);
            put("SEPTEMBER", 0);
            put("OCTOBER", 0);
            put("NOVEMBER", 0);
            put("DECEMBER", 0);
        }};

        // loop through all customers
        for (Customer customer : allCustomers) {
            String month = customer.getCreateDate().toLocalDate().getMonth().toString();
            System.out.println("Month: " + month);
            customerStats.put(month, customerStats.get(month) + 1);
        }

        // create list of stats
        ObservableList<CustomerMonthStat> stats = FXCollections.observableArrayList();

        // loop through the months dictionary
//        for (Map.Entry<String, Integer> entry : customerStats.entrySet()) {
//            stats.add(new CustomerMonthStat(entry.getKey(), entry.getValue()));
//        }
        //lambda expression 1 justification for lambda expression is that it provides more clarity and conciseness in the code
        customerStats.forEach((key, value) -> stats.add(new CustomerMonthStat(key, value)));




        // update the table
        statsTable.setItems(stats);

    }
}
