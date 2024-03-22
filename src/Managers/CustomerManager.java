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

    String sql = "SELECT * FROM customers";
    PreparedStatement ps = JDBC.connection.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        //create a customer instance
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Country country = new Country(1, "Country");
        Division division = new Division(rs.getInt("Division_ID"), "Division", country);
        Customer customer = new Customer(rs.getInt("Customer_ID"),
                rs.getString("Customer_Name"),
                rs.getString("Address"),
                rs.getString("Postal_Code"),
                rs.getString("Phone"),
                division,
                LocalDateTime.parse(rs.getString("Create_Date"), formatter)
                );
        //add the new instance to the customerList
        customerList.add(customer);
    }

    return customerList;
}
    /**
     * This method updates a customer in the customers table
     * @param id
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param division
     * @throws SQLException
     */
    public static void updateCustomer(int id, String name, String address,
                                      String postalCode, String phone, Division division) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement updateCustomer = JDBC.connection.prepareStatement(sql);
        updateCustomer.setString(1, name);
        updateCustomer.setString(2, address);
        updateCustomer.setString(3, postalCode);
        updateCustomer.setString(4, phone);
        updateCustomer.setObject(5, division);

//        updateCustomer.setString(5, division);
    }



}
