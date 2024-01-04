package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Customer {
    //is this baby working 9pm


    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private Division division; //this will be the FK

    public Customer(int id, String name, String address, String postalCode, String phone, Division division){
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = division;
    }

    public static ObservableList<Customer> createCustomerListFromQuery(ResultSet rs) throws SQLException {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        while (rs.next()) {

                    //new is object #1
            customers.add(new Customer(
                    rs.getInt("Customer_ID"),
                    rs.getString("Customer_Name"),
                    rs.getString("Address"),
                    rs.getString("Postal_Code"),
                    rs.getString("Phone"),
                    //this is object #2                                                 this is object #3
                    new Division(rs.getInt("Division_ID"), "Division", new Country(1, "Country"))
            ));

        }
        return customers;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

// setters and getters for Customers.
}
