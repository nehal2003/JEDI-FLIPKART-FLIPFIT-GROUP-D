package com.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
    private static Connection connection = null;

    public static Connection getConnection() {
//        try {
//            if (isValid()) {
//                return connection;
//            } else {
//                try {
//                    Properties prop = new Properties(); // Properties is used to read files
//                    InputStream inputStream = DatabaseConnector.class.getClassLoader().getResourceAsStream("./config.properties");
//                    prop.load(inputStream);
//                    String driver = prop.getProperty("driver");
//                    String url = prop.getProperty("url");
//                    String user = prop.getProperty("user");
//                    String password = prop.getProperty("password");
//                    Class.forName(driver);
//                    connection = DriverManager.getConnection(url, user, password);
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flipfit", "root", "Nehal@813kavya");
                return con;
            }
            catch (Exception e) {
                System.out.println(e);
                return null;
            }
    }

    public static boolean isValid() throws SQLException {
        return connection != null && !connection.isClosed();
    }
}
