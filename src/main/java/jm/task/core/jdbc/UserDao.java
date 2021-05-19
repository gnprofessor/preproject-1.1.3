package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements jm.task.core.jdbc.dao.UserDao {

    public UserDao() {
    }

    @Override
    public void createUsersTable() {
        try {
            Connection conn = Util.getInstance().getConn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users(" +
                    "Id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "Name VARCHAR(255)," +
                    "LastName VARCHAR(255)," +
                    "Age TINYINT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Connection conn = Util.getInstance().getConn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Connection conn = Util.getInstance().getConn();
            PreparedStatement stmt = conn.prepareStatement("INSERT users (Name, LastName, Age) VALUES (?, ?, ?) ");
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Connection conn = Util.getInstance().getConn();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE Id = ?");
            stmt.setLong(1, id);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Connection conn = Util.getInstance().getConn();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM users");
            while (result.next()) {
                User user = new User(result.getString("Name"), result.getString("LastName"), result.getByte("Age"));
                user.setId(result.getLong("Id"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Connection conn = Util.getInstance().getConn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
