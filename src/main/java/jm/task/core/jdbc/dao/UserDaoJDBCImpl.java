package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String usersTable = "CREATE TABLE IF NOT EXISTS users " +
                "(id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(45) NOT NULL, " +
                "lastName VARCHAR(45) NOT NULL, " +
                "age TINYINT(3) NOT NULL, " +
                "PRIMARY KEY (`id`))";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(usersTable)) {
            preparedStatement.executeUpdate(usersTable);

        } catch (SQLException e) {
            System.err.println("Ошибка при создании таблицы \"users\"");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        String usersTable = "DROP TABLE IF EXISTS users";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(usersTable)) {
            preparedStatement.executeUpdate(usersTable);

        } catch (SQLException e) {
            System.err.println("Ошибка при удаленииц таблицы \"users\"");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String usersTable = "INSERT INTO users (name, lastName, age)" +
                "VALUES (?, ?, ?)";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(usersTable)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Ошибка при сохранении нового пользователя в таблице \"users\"");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        String usersTable = "DELETE FROM users WHERE id = ?";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(usersTable)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Ошибка при удалении пользователя по id в таблице \"users\"");
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        String usersTable = "SELECT * FROM users";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(usersTable)) {
            ResultSet resultSet = preparedStatement.executeQuery(usersTable);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                userList.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при выборке списка пользователей в таблице \"users\"");
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {

        String usersTable = "TRUNCATE users";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(usersTable)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при чистке таблицы \"users\"");
            e.printStackTrace();
        }
    }
}
