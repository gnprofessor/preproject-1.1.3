package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private String url = "jdbc:mysql://localhost/preproject?serverTimezone=Europe/Moscow&useSSL=false";
    private String username = "root";
    private String password = "root";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private Connection conn;
    private static Util instance;

    private Util() {
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            this.conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Connection failed");
            System.out.println(e.getMessage());
        }
    }

    public Connection getConn() {
        return this.conn;
    }

    public static Util getInstance() throws SQLException {
        if (instance == null) {
            instance = new Util();
        } else if (instance.getConn().isClosed()) {
            instance = new Util();
        }
        return instance;
    }
}
