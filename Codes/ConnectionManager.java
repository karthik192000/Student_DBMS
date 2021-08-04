/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student_database;
import java.sql.*;

/**
 *
 * @author karthik
 */
public class ConnectionManager {
        private static final String url = "jdbc:mysql://localhost:3306/your_database_name";
        private static final String driverName = "com.mysql.cj.jdbc.Driver";
        private static final String username = "your_mysql_username";
        private static final String password = "your_mysql_password";
        private static Connection con;

        public ConnectionManager(){}

        public static Connection getConnection() {
            try {
                Class.forName(driverName);
                try {
                    con = DriverManager.getConnection(url, username, password);
                } catch (SQLException ex) {
                    // log an exception. fro example:
                    System.out.println("Failed to create the database connection.");
                }
            } catch (ClassNotFoundException ex) {
                // log an exception. for example:
                System.out.println("Driver not found.");
            }
            return con;
        }
}
