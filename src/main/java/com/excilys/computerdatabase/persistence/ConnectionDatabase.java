package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javax.sql.DataSource;

import com.excilys.computerdatabase.util.ComputerDatabaseDAOException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ConnectionDatabase {

    INSTANCE;

    private ResourceBundle bundle = ResourceBundle.getBundle("config");
    private String url = bundle.getString("database.url");
    private String user = bundle.getString("database.user");
    private String password = bundle.getString("database.password");
    private DataSource dataSource;
    private final Logger LOGGER = LoggerFactory.getLogger(ConnectionDatabase.class.getName());

    /**
     * connection to database
     */
    ConnectionDatabase() {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(password);
            config.setMaximumPoolSize(100);

            dataSource = new HikariDataSource(config);

            // To close the datasource when the server is closing
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    ((HikariDataSource) ConnectionDatabase.this.dataSource).close();
                }
            });

        } catch (ClassNotFoundException e) {
            LOGGER.error("Connection failure");
            e.printStackTrace();
        }

    }

    /**
     * @return Connection
     */
    public Connection getConnection() {

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Connection failure");
            throw new ComputerDatabaseDAOException();
        }

    }

}
