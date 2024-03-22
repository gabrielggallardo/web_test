package Managers;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.Customer;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * This class is the manager for the first_level_divisions table
 */
public class DivisionManager {

    /**
     * This method gets the division list from the first_level_divisions table
     * @return
     * @throws SQLException
     */
    public static ObservableList<Division> getDivisionList() throws SQLException{
        ObservableList<Division> divisionList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            Division division = new Division(rs.getInt("Division_ID"),
                    rs.getString("Division"),
                    new Country(rs.getInt("Country_ID"), rs.getString("Unsure")));//very unsure how to properly do this

            divisionList.add(division);
        }
        return divisionList;
    }


}
