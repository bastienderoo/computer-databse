package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public enum ConnectionDatabase {

    INSTANCE;
    private Connection connection;
    private ResourceBundle bundle = ResourceBundle.getBundle("config");
    private String url = bundle.getString("database.url");
    private String user = bundle.getString("database.user");
    private String password = bundle.getString("database.password");

    ConnectionDatabase() {

        try {

            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

    }

    /**
     * 
     * 
     * @return fermeture connexion
     */
    public Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

}
