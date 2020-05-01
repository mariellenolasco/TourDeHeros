package com.revature.dao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    private static Connection connection = null;
    public static Connection getConnection(){
        try {
            InputStream input = new FileInputStream("src/main/resources/config.properties");
            Properties prop = new Properties();
            prop.load(input);
            connection = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
            System.out.println("got it!");
            return connection;
        } catch (SQLException ex) {
            throw new Error("Problem", ex);
        } catch (FileNotFoundException ex) {
            throw new Error("Problem", ex);
        } catch (IOException ex) {
            throw new Error("Problem", ex);
        }
    }
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
