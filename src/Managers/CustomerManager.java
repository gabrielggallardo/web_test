package Managers;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the manager for the customers table
 */
public class CustomerManager {
    /**
     * This method gets the customer list from the customers table
     * @return
     * @throws SQLException
     */
    public static ObservableList<Customer> getCustomerList() throws SQLException {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        // get all the customers as well as their divisions and countries
        String sql = "SELECT * FROM customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON first_level_divisions.COUNTRY_ID = countries.Country_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            //create a customer instance
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Division division = new Division(rs.getInt("Division_ID"),
                    rs.getString("Division"),
                    new Country(rs.getInt("Country_ID"),
                            rs.getString("Country")
                    )
            );
            Customer customer = new Customer(rs.getInt("Customer_ID"),
                    rs.getString("Customer_Name"),
                    rs.getString("Address"),
                    rs.getString("Postal_Code"),
                    rs.getString("Phone"),
                    division
                    );
            //add the new instance to the customerList
            customerList.add(customer);
        }

        return customerList;
    }

    /**
     * This method Inserts a new customer into the customers table
     * @param customer
     * @return
     * @throws SQLException
     */
    public static void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement addCustomer = JDBC.connection.prepareStatement(sql);
        addCustomer.setString(1, customer.getName());
        addCustomer.setString(2, customer.getAddress());
        addCustomer.setString(3, customer.getPostalCode());
        addCustomer.setString(4, customer.getPhone());
        addCustomer.setInt(5, customer.getDivision().getId());
        addCustomer.executeUpdate();

        return;
    }

    /**
     * This method updates a customer in the customers table
     * @param id
     * @param customer
     * @throws SQLException
     */
    public static void updateCustomer(int id, Customer customer) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement updateCustomer = JDBC.connection.prepareStatement(sql);
        updateCustomer.setString(1, customer.getName());
        updateCustomer.setString(2, customer.getAddress());
        updateCustomer.setString(3, customer.getPostalCode());
        updateCustomer.setString(4, customer.getPhone());
        updateCustomer.setInt(5, customer.getDivision().getId());

        updateCustomer.setInt(6, id);

        updateCustomer.executeUpdate();
    }

    /**
     * This method deletes a customer from the customers table
     * @param id
     * @throws SQLException
     */

    public static void deleteCustomer(int id) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement deleteCustomer = JDBC.connection.prepareStatement(sql);
        deleteCustomer.setInt(1, id);
        deleteCustomer.execute();

    }

    /**
     * This method gets the Customer ID as well as the division and country of the customer with the given ID
     * @param customerID
     * @return
     */
    public static Customer getCustomer(int customerID) {
        try {
            // get the customer as well as their division and country
            String sql = "SELECT * FROM customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON first_level_divisions.COUNTRY_ID = countries.Country_ID WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Division division = new Division(rs.getInt("Division_ID"),
                    rs.getString("Division"),
                    new Country(rs.getInt("Country_ID"),
                            rs.getString("Country")
                    )
            );
            Customer customer = new Customer(rs.getInt("Customer_ID"),
                    rs.getString("Customer_Name"),
                    rs.getString("Address"),
                    rs.getString("Postal_Code"),
                    rs.getString("Phone"),
                    division
            );
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
