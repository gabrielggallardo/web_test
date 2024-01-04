package Managers;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Country;
import model.Customer;
import model.Division;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager {


    public static ObservableList<Customer> getCustomerList() throws SQLException {
    ObservableList<Customer> customerList = FXCollections.observableArrayList();

    String sql = "SELECT * FROM customers";
    PreparedStatement ps = JDBC.connection.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        //create a customer instance
        Country country = new Country(1, "Country");
        Division division = new Division(rs.getInt("Division_ID"), "Division", country);
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
