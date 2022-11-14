package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    //private static final
    private static final UserService userService = new UserServiceImpl();
    private static final User user1 = new User("Sasha", "Ezhov", (byte) 34);
    private static final User user2 = new User("Victor", "Victorov", (byte) 99);
    private static final User user3 = new User("Паша", "М", (byte) 55);
    private static final User user4 = new User("Aleksandr", "Ezhov", (byte) 0);

    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());

        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());

        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());

        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}

