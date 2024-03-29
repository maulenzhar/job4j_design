package ru.job4j.jdbc;

import ru.job4j.io.readconfig.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String path = "./src/main/resources/app.properties";
        Config config = new Config(path);
        config.load();

        Class.forName(config.value("jdbc.driver_class"));
        String login = config.value("jdbc.connection.username");
        String password = config.value("jdbc.connection.password");
        String url = config.value("jdbc.url");

        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
