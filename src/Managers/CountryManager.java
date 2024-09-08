package Managers;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.Customer;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is the manager for the first_level_divisions table
 */
public class CountryManager {

    /**
     * This method gets the division list from the first_level_divisions table
     * @return
     * @throws SQLException
     */
    public static ObservableList<Country> getCountryList() throws SQLException{
        ObservableList<Country> divisionList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            Country country = new Country(rs.getInt("Country_ID"),
                    rs.getString("Country"));

            divisionList.add(country);
        }
        return divisionList;
    }

    /**
     * This method Inserts a new customer into the customers table
     * @param customer
     * @param currentUser
     * @return
     * @throws SQLException
     */
    public static Customer addCustomer(Customer customer, User currentUser) throws SQLException {
        String sql = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, NOW(), ?, NOW(), ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customer.getName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setString(5, currentUser.getUsername());
        ps.setString(6, currentUser.getUsername());
        ps.setInt(7, customer.getDivision().getId());
        ps.executeUpdate();
        return customer;
    }

}
