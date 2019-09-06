package com.ftd.smartshare.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionFactory {

	private static String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private static String CONNECTION_URL = "jdbc:postgresql://localhost:5432/smartshare?currentSchema=smartshare";
    private static String USER = "postgres";
    private static String PASSWORD = "bondstone";

    private static JDBCConnectionFactory connectionFactory = null;

    private JDBCConnectionFactory() {
            try {
                Class.forName(DRIVER_CLASS_NAME);
            } catch (ClassNotFoundException e) {
                System.out.println("Driver class could not be found. :(");
            }
    }

    public static JDBCConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new JDBCConnectionFactory();
        }
        return connectionFactory;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
