package aplicacion.data.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Clase encargada de establecer la conexión con la base de datos MySQL.
 * Provee métodos estáticos para utilizar siempre la misma instancia de
 * conexión, evitando así conexiones duplicadas. También utiliza variables
 * de entorno, conectadas a su vez a la configuración lanzada con Docker.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 2.0
 */
public class DBConnection {

    public static final Dotenv dotenv = Dotenv.load();
    public static final Connection connection = connect();

    /**
     * Método estático que realiza la conexión a la base de datos.
     *
     * @return La conexión, o null en caso de error.
     */
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

    /**
     * Realiza una opearción de SELECT en la base de datos
     *
     * @param sql String con la sentencia SQL a realizar
     * @return ResultSet con los resultados de la query.
     */
    public static ResultSet getQuery(String sql) {
        try {
            return connection.createStatement().executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Realiza una opearción de INSERT, UPDATE o DELETE en la base de datos
     *
     * @param sql String con la sentencia SQL a realizar
     * @return ResultSet con los resultados de la query.
     */
    public static int updateQuery(String sql) {
        try {
            return connection.createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

}

