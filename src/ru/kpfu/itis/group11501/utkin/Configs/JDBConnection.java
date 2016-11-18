package ru.kpfu.itis.group11501.utkin.Configs;

/**
 * Created by user on 21.10.2016.
 */
import java.sql.*;

public class JDBConnection {

    private static JDBConnection instance = new JDBConnection();

    private Connection connection;
    public static PreparedStatement statement;
    private String url = "jdbc:postgresql://localhost:5432/mafiasite";
    private String name = "postgres";
    private String password = "2548ZZ2548ZZz";

    private JDBConnection() {
        connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.print("Driver is ready");
            this.connection = DriverManager.getConnection(url, name, password);
            System.out.println("Connection installed");
            //this.statement = connection.
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static JDBConnection getInstance() {
        return instance;
    }

    public PreparedStatement  getStatement() {
        return statement;
    }

}
