package aplicacion.data.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DBConnection {

    public static final Dotenv dotenv = Dotenv.load();
    public static final Connection connection = connect();

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s",
                            dotenv.get("MYSQL_HOST"), dotenv.get("MYSQL_PORT"),
                            dotenv.get("MYSQL_DATABASE")),
                    dotenv.get("MYSQL_ROOT"), dotenv.get("MYSQL_ROOT_PASSWORD"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static ResultSet getQuery(String sql) {
        try {
            return connection.createStatement().executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static int updateQuery(String sql) {
        try {
            return connection.createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

}

