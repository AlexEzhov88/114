package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS kata.user" +
                    "(id bigint not null auto_increment," +
                    "name VARCHAR(45)," +
                    "lastname VARCHAR(45)," +
                    "age int," +
                    "PRIMARY KEY (id))");
        } catch (SQLException e) {
            e.printStackTrace(); throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS kata.user");
        } catch (SQLException e) {
            e.printStackTrace(); throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        String sql = "INSERT INTO kata.user(name, lastname, age) VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
            stm.setLong(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        Connection connection = Util.getConnection();
        List<User> allUser = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM kata.user";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                allUser.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(); throw new RuntimeException(e);
        }
        return allUser;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        String sql = "TRUNCATE kata.user";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace(); throw new RuntimeException(e);
        }
    }
}
