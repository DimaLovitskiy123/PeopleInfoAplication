package ru.mitya.dbutil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConnectorImpl implements DatabaseConnector{
    @Value("${databaseProperties.url}")
    private String url;
    @Value("${databaseProperties.user}")
    private String user;
    @Value("${databaseProperties.password}")
    private String password;

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
