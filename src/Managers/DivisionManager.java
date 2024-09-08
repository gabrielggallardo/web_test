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
        // get the division list from the first_level_divisions table along with the country id and name

        String sql = "SELECT * FROM first_level_divisions JOIN countries ON first_level_divisions.COUNTRY_ID = countries.Country_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            Country country = new Country(rs.getInt("COUNTRY_ID"),
                    rs.getString("Country"));
            Division division = new Division(rs.getInt("Division_ID"),
                    rs.getString("Division"),
                    country);
            divisionList.add(division);
        }
        return divisionList;
    }


}
