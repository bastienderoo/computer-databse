package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import com.excilys.computerdatabase.util.ComputerDatabaseDAOException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public enum ConnectionDatabase {

    INSTANCE;

    private ResourceBundle bundle = ResourceBundle.getBundle("config");
    private String url = bundle.getString("database.url");
    private String user = bundle.getString("database.user");
    private String password = bundle.getString("database.password");
    private DataSource dataSource;

    ConnectionDatabase() {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(password);
            config.setMaximumPoolSize(20);
            config.setMaxLifetime(60);

            dataSource = new HikariDataSource(config);

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
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new ComputerDatabaseDAOException("Connection impossible");
        }

    }

}
