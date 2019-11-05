package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {

    private static Database instance;
    private static Connection connection;
    private String url = String.format("jdbc:mysql://sql2.freemysqlhosting.net/sql2310355", 3306);
    private String username = "sql2310355";
    private String password = "gK5%qI5%";

    private Database() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Database.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Cannot connect to the database: " + ex.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        } else if (Database.getConnection().isClosed()) {
            instance = new Database();
        }

        return instance;
    }
}
