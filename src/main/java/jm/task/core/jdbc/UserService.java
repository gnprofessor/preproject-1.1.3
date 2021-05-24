package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserService implements jm.task.core.jdbc.service.UserService {

    private UserDao dao;


    public UserService() {
        this.dao = new UserDao();
    }

    @Override
    public void createUsersTable() {
        dao.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        dao.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        dao.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        dao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        dao.cleanUsersTable();
    }
}
