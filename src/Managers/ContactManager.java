package Managers;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import model.Country;
import model.Customer;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
    /**
     * This class is the manager for the contacts table
     */
public class ContactManager {
    /**
     * This method gets the contact list from the contacts table
     * @return
     * @throws SQLException
     */
    public static ObservableList<Contacts> getContactList() throws SQLException{
        ObservableList<Contacts> contactList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Contacts contact = new Contacts(rs.getInt("Contact_ID"),
                    rs.getString("Contact_Name"),
                    rs.getString("Email"));

            contactList.add(contact);
        }
        return contactList;
    }
    /**
     * This method finds a contact in the contacts table
     * @param id
     * @param name
     * @param email
     * @throws SQLException
     */
    public static String findContactID(String contactID) throws SQLException{
        PreparedStatement ps = JDBC.connection.prepareStatement("SELECT * FROM contacts WHERE Contact_Name = ?");
        ps.setString(1, contactID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            contactID = rs.getString("Contact_ID");
        }
        return contactID;
    }




}
