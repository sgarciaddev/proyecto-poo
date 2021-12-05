package aplicacion.controllers.exceptions;

import aplicacion.controllers.gui.UtilsGUI;

import java.sql.SQLException;

/**
 * Excepción de conexión con base de datos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 4.0
 */
public class DatabaseException extends SQLException {

    /**
     * Permite generar una excepción de conexión con BD
     *
     * @param message Mensaje de excepción
     */
    public DatabaseException(String message) {
        super(message);
    }

    /**
     * Permite generar una excepción de conexión con BD
     */
    public DatabaseException() {
        super("La conexión a la base de datos no pudo realizarse");
    }

    /**
     * Método que permite mostrar un mensaje de error al usuario.
     */
    public void mostrarMensajeError() {
        UtilsGUI.errorMsg("La conexión con la base de datos no pudo realizarse. Se utilizarán los datos locales.");
    }

}
