package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Customer {
    //most recent changes seeing if commit works

    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private Division division; //this will be the FK
    private int divisionCountryId;
    private LocalDateTime createDate;


    /**
     * This method gets the customer list from the customers table
     * @return
     * @throws SQLException
     */
    public Customer(int id, String name, String address, String postalCode, String phone, Division division, LocalDateTime createDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = division;
        this.divisionCountryId = division.getCountry().getCountryID();
        this.createDate = createDate;
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
    public int getDivisionCountryId() {
        return divisionCountryId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }
// setters and getters for Customers.
}
