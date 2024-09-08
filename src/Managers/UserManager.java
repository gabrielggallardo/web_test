package Managers;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
/**
 * This class is the manager for the users table
 */
public class UserManager {
    /**
     * This method gets the user list from the users table
     * @return
     * @throws SQLException
     */
    public static ObservableList<User> getUserList() throws SQLException {
        ObservableList<User> userList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        System.out.println("test");
        ResultSet rs = ps.executeQuery();

        // loop through each returned row from the database
        while (rs.next()) {
            //create User instance
            User user = new User(rs.getInt("User_ID"),
                    rs.getString("User_Name"),
                    rs.getString("Password"));

            // store created instance inside the List
            userList.add(user);


        }

        return userList;
    }

    /**
     * This method gets the user
     * @param userID
     * @return
     */
    public static User getUser(int userID) {
        User user = null;
        try {
            String sql = "SELECT * FROM users WHERE User_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("User_ID"),
                        rs.getString("User_Name"),
                        rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
