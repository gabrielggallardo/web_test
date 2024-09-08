Title: C195 - Scheduling Application
Purpose: Access existing database and adding functionality for adding, updating, deleting appointments and customers.

Author: Gabriel Gallardo
Student ID# 000985578
Application Version 1.3
Date: 9/7/24

IDE: IntelliJ IDEA 2021.1.3 (Community Edition) x64
JDK version: Java 17.0.3
JavaFX version: JAVAFX.SDK.18.0.1

How to run program:
Username: test/admin
Password: test/admin

Launch program and enter username and password (username and password are case sensitive) on login screen and click the login button or cancel to exit the program.
User will receive an alert if username or password is incorrect. Once the user has been validated and logged in - an alert will be display letting the user know if there
are any appointments within 15 minutes or not.

MAIN MENU:
After the appointment notification, the user will be directed to the main menu where 4 buttons will appear on the screen.

CUSTOMER:
The customer button will lead to the customer list and provide a table with all the available customer's information. On the bottom of the screen under the table there are 6 buttons.
The modify button will allow you to update an existing contact, the add button will allow you to create a new customer, and delete will allow you to remove an existing customer from the system.
The appointments button will take you to the appointments screen. The reports button will take you to the reports screen.

APPOINTMENT:
The appointment button will lead the user to a screen that displays all existing appointments by default. At the top left side of the screen, the user can select from all appointments, the next rolling week and the
next rolling month appointments by selecting the appropriate radio button.  Underneath the table on the bottom, there are 6 buttons. The modify button will allow the user to modify an existing
appointment, the add button will allow a user to create a new appointment and the delete button will allow the user to delete the selected appointment from the system.
The customer button will take you back to the customer screen and the reports button will take you back to the reports screen.

REPORTS:
The report button will take you to the report list where there will be two buttons. One of each button will contain a report, one linking to the contact schedule and then linking also to the total appointments by type.
The contact schedules tab has a combobox that allows you to select a contact and will display appointments associated with the content in the tableview.

REPORT A3F: The total new customers for each and every month.

The exit button will take you back to the reports menu screen then exit will take you back to the appointments screen.

MySQL Connector Driver Version: mysql-connector-java-8.0.25