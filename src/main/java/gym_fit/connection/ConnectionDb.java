package gym_fit.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDb {
    private static final Config config = new Config();

    public static Connection getConnection() {
        Connection connection = null;
        var dataBase = "gym_fit_db";
        var url = "jdbc:mysql://localhost:1504/" + dataBase;
        var user = "root";
        var password = config.getPassword();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("error to connection to database " + e.getMessage());
        }
        return connection;
    }
}