package laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import laptrinhjavaweb.dao.GenericDAO;

public class AbtractDAO implements GenericDAO {


    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url= "jdbc:mysql://localhost:3306/lcms";
            String user = "root";
            String password = "";
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}
