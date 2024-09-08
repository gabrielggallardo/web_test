package Managers;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Contacts;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is the manager for the appointments table in the database
 */
public class AppointmentManager {

    /**
     * This method gets all the appointments for a specific customer
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointmentsForCustomer(int customerID) throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStartTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEndTime = rs.getTimestamp("End").toLocalDateTime();
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Appointment appointment = new Appointment(rs.getInt("Appointment_ID"),
                    rs.getString("Title"),
                    appointmentDescription,
                    appointmentLocation,
                    appointmentType,
                    appointmentStartTime,
                    appointmentEndTime,
                    customerID,
                    userID,
                    contactID);
            appointmentList.add(appointment);
        }
        return appointmentList;
    }

    /**
     * This method gets all appointments from the database
     * @return an ObservableList of all appointments
     * @throws SQLException
     */
    public static void deleteAllAppointmentsForCustomer(int customerID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        ps.executeUpdate();
    }

    // get all unique appointment types from the database
    public static ObservableList<String> getAllAppointmentTypes() throws SQLException {
        ObservableList<String> appointmentTypes = FXCollections.observableArrayList();

        String sql = "SELECT DISTINCT Type FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        // add an "all" option to the appointment types list
        appointmentTypes.add("All");

        while (rs.next()) {
            appointmentTypes.add(rs.getString("Type"));
        }

        return appointmentTypes;
    }
    /**
     * This method gets all appointments from the database
     * @return an ObservableList of all appointments
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointmentsForContact(Contacts contact) throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contact.getContactID());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStartTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEndTime = rs.getTimestamp("End").toLocalDateTime();
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Appointment appointment = new Appointment(rs.getInt("Appointment_ID"),
                    rs.getString("Title"),
                    appointmentDescription,
                    appointmentLocation,
                    appointmentType,
                    appointmentStartTime,
                    appointmentEndTime,
                    customerID,
                    userID,
                    contactID);
            appointmentList.add(appointment);
        }
        return appointmentList;
    }

    /**
     * This method gets all appointments from the database
     * @return an ObservableList of all appointments
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAppointmentsInDateRange(LocalDate start, LocalDate end ) throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Start BETWEEN ? AND ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setTimestamp(1, Timestamp.valueOf(start.atTime(0, 0)));
        ps.setTimestamp(2, Timestamp.valueOf(end.atTime(23, 59, 59)));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStartTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEndTime = rs.getTimestamp("End").toLocalDateTime();
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Appointment appointment = new Appointment(rs.getInt("Appointment_ID"),
                    rs.getString("Title"), appointmentDescription,
                    appointmentLocation,
                    appointmentType,
                    appointmentStartTime,
                    appointmentEndTime,
                    customerID,
                    userID,
                    contactID);
            appointmentList.add(appointment);
        }
        return appointmentList;
    }

    /**
     * This method gets all appointments from the database
     * @return an ObservableList of all appointments
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAppointmentList() throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStartTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEndTime = rs.getTimestamp("End").toLocalDateTime();
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            Appointment appointment = new Appointment(rs.getInt("Appointment_ID"),
                    rs.getString("Title"), appointmentDescription,
                    appointmentLocation,
                    appointmentType,
                    appointmentStartTime,
                    appointmentEndTime,
                    customerID,
                    userID,
                    contactID);
            appointmentList.add(appointment);
        }
        return appointmentList;
    }

    /**
     * This method updates an appointment to the database
     * @param appointmentTitle the title of the appointment
     * @param appointmentDescription the description of the appointment
     * @param appointmentLocation the location of the appointment
     * @param appointmentType the type of the appointment
     * @param appointmentStartTime the start time of the appointment
     * @param appointmentEndTime the end time of the appointment
     * @param customerID the customer ID of the appointment
     * @param userID the user ID of the appointment
     * @param contactID the contact ID of the appointment
     * @throws SQLException
     */

    public static void updateAppointment(int appointmentID, String appointmentTitle, String appointmentDescription, String appointmentLocation,
                                         String appointmentType, LocalDateTime appointmentStartTime, LocalDateTime appointmentEndTime,
                                         int customerID, int userID, int contactID) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement updateAppointment = JDBC.connection.prepareStatement(sql);
        updateAppointment.setString(1, appointmentTitle);
        updateAppointment.setString(2, appointmentDescription);
        updateAppointment.setString(3, appointmentLocation);
        updateAppointment.setString(4, appointmentType);
        updateAppointment.setTimestamp(5, Timestamp.valueOf(appointmentStartTime));
        updateAppointment.setTimestamp(6, Timestamp.valueOf(appointmentEndTime));
        updateAppointment.setInt(7, customerID);
        updateAppointment.setInt(8, userID);
        updateAppointment.setInt(9, contactID);
        updateAppointment.setInt(10, appointmentID);

        updateAppointment.execute();

    }
    /**
     * This method adds an appointment to the database
     * @param appointmentTitle the title of the appointment
     * @param appointmentDescription the description of the appointment
     * @param appointmentLocation the location of the appointment
     * @param appointmentType the type of the appointment
     * @param appointmentStartTime the start time of the appointment
     * @param appointmentEndTime the end time of the appointment
     * @param customerID the customer ID of the appointment
     * @param userID the user ID of the appointment
     * @param contactID the contact ID of the appointment
     * @throws SQLException
     */

    public static void addAppointment(String appointmentTitle, String appointmentDescription, String appointmentLocation,
                                      String appointmentType, LocalDateTime appointmentStartTime, LocalDateTime appointmentEndTime,
                                      int customerID, int userID, int contactID) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement insertAppointment = JDBC.connection.prepareStatement(sql);
        insertAppointment.setString(1, appointmentTitle);
        insertAppointment.setString(2, appointmentDescription);
        insertAppointment.setString(3, appointmentLocation);
        insertAppointment.setString(4, appointmentType);
        insertAppointment.setTimestamp(5, Timestamp.valueOf(appointmentStartTime));
        insertAppointment.setTimestamp(6, Timestamp.valueOf(appointmentEndTime));
        insertAppointment.setInt(7, customerID);
        insertAppointment.setInt(8, userID);
        insertAppointment.setInt(9, contactID);
        insertAppointment.executeUpdate();
    }

    /**
     * This method deletes an appointment from the database
     * @param id the appointment ID to be deleted
     * @throws SQLException
     */
    public static void deleteAppointment(int id) throws SQLException {
        String sqldelete = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement deleteAppointment = JDBC.connection.prepareStatement(sqldelete);
        deleteAppointment.setInt(1, id);
        deleteAppointment.execute();
    }
//    public static ObservableList<Appointment> getMonthAppointments() throws SQLException {
//        ObservableList<Appointment> monthList = FXCollections.observableArrayList();
//        String sql = "SELECT * FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE MONTH(START) = MONTH(NOW()) ORDER BY appointments.Appointment_ID";
//        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()){
//            String appointmentDescription = rs.getString("Description");
//            int appointmentContact = rs.getInt("Contact_ID");
//            String appointmentContactName = rs.getString("Contact_Name");
//            String appointmentType = rs.getString("Type");
//            LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
//            LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
//            int appointmentCustomerId = rs.getInt("Customer_ID");
//            int contactID = rs.getInt("User_ID");
//            String appointmentLocation = rs.getString("Location");
//
//            Appointment monthly = new Appointment(rs.getInt("Appointment_ID"),
//                    rs.getString("Title"),
//                    appointmentDescription,
//                    appointmentContact,
//                    appointmentContactName,
//                    appointmentType,
//                    appointmentStart,
//                    appointmentEnd,
//                    appointmentCustomerId,
//                    contactID,
//                    appointmentLocation);
//            monthList.add(monthly);
//
//        }
//        return monthList;

    }








