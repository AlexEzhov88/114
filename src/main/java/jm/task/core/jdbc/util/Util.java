package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost:3306/kata?useSSL=false&allowMultiQueries=true&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static Connection connection = Util.getConnection(); // null

    // Вынеси в поле класса Connection connection = Util.getConnection(); +
    // В методе подключения отключи у коннекшн автокоммиты +
    // В каждом методе дао делай коммит и ролбэк + rollback +
    // Где модификаторы доступа у полей? +
    // Почему не интерфейс UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();?  --
    // Убери System.out.println +

    public static Connection getConnection() {

        Connection localConnection = connection;

        try {
            if (localConnection == null || localConnection.isClosed()) {

                synchronized (Util.class) {
                    localConnection = connection;

                    try {
                        Class.forName(DRIVER);
                        connection = localConnection = DriverManager.getConnection(HOST, LOGIN, PASSWORD);
                        connection.setAutoCommit(false);

                    } catch (ClassNotFoundException | SQLException e) {
                        connection.rollback();
                        System.out.println("Connection Error!!!");
                    }

                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return localConnection;

    }

}

