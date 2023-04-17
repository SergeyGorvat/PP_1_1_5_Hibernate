package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        User user1 = new User("Sergey", "Ivanov", (byte) 23);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());

        User user2 = new User("Ivan", "Petrov", (byte) 24);
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());

        User user3 = new User("Michail", "Gudcov", (byte) 25);
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());

        User user4 = new User("Evgeniy", "Jakov", (byte) 26);
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
