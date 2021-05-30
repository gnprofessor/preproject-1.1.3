package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        for (int i = 1; i<=4; i++) {
            userService.saveUser("Имя " + i, " Фамилия" + i, (byte) i);
            System.out.println("User с именем  - Имя " + i + " добавлен в базу данных");
        }
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
